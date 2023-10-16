/**
 * @author m.prasad@orasi.com
 * @author Orasi Software, Inc.
 * @version 12
 */
  
package com.orasi;
  
import java.util.*;
import java.util.concurrent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.orasi.event.spi.*;
import static com.orasi.shared_library.*;
import com.orasi.integration.*;
import com.orasi.event.Event;
import com.orasi.event.action.EventAction;
import com.orasi.event.handler.AbstractEventHandler;
import com.orasi.event.hook.EventHook;
import com.orasi.model.*;
import org.apache.commons.cli.*;
import com.orasi.secrets.*;
import com.orasi.datasource.*;
import java.io.InputStream;
import java.util.logging.LogManager;

  
  
public class SuiteExecutionWrapper {

  static {
    try ( InputStream iS = SuiteExecutionWrapper.class.getClassLoader().getResourceAsStream( "logging.properties") ) {
      LogManager.getLogManager().readConfiguration(iS);
    } catch( Exception e ) {
      e.printStackTrace();
    }
  }

  private static int failureCount = 0;

  private static class TestFailureHandler extends AbstractEventHandler<TestPayload> {

      public TestFailureHandler() {
        super( EventAction.ALL, EventHook.FAILURE.getId(), TestEvent.EVENT_TYPE );
      }
    
      @Override
      protected void _handleEvent(Event<TestPayload> e) {
        failureCount++;
      }

    }

    private class TestNotificationHandler extends AbstractEventHandler<StepPayload> {

      public TestNotificationHandler() {
        super( EventAction.ALL, EventHook.AFTER.getId(), StepEvent.EVENT_TYPE );
      }
    
      @Override
      protected void _handleEvent(Event<StepPayload> e) {
        activeExecutions.forEach((t) -> {
          if ( e.getPayload().getTestExecutionId() == t.getId() ) {
            t.setLastAccess();
          }
        });
      }

    }
  
    private final Options cliOptions = new Options();
    private static CommandLine cli;
    private int timeout = 180 * 1000;
    private int threadCount = 10;
    private static final Logger log = LoggerFactory.getLogger(SuiteExecutionWrapper.class);
    private static final SuiteExecutionWrapper singleton = new SuiteExecutionWrapper();
    private int executionId;
  
    private String name;
    private String description;
    private int id;
    private String userName;
    private final List<ExecutionWrapper> activeExecutions = new ArrayList<>( 12 );
    private List<String> testList;
    private List<String> targetList;

    private static final Map<String,Map<String,String>> environmentMap = new HashMap<>( 10 );
  
    private SuiteExecutionWrapper() {
  
    }

    public CommandLine getCommandLine() {
      return cli;
    }

    public Options getOptions() {
      return cliOptions;
    }

    public void addOptions(Option[] options) {
      for (Option option : options) {
        cliOptions.addOption(option);
      }
    }

    public String getOption( String name, String defaultValue ) {
      if ( cli.hasOption(name) ) {
        if ( SuiteExecutionWrapper.instance().getOptions().getOption(name).hasArg() ) {
          return cli.getOptionValue(name);
        } else {
          return "true";
        }
      } else {
        return defaultValue;
      }
    }
  
    public String getOption( String key, String name, String defaultValue ) {
      String keyName = key + "_" + name;
      if ( cli.hasOption(keyName) ) {
        if ( SuiteExecutionWrapper.instance().getOptions().getOption(keyName).hasArg() ) {
          return cli.getOptionValue(keyName);
        } else {
          return "true";
        }
      } else {
        return defaultValue;
      }
    }

  private static boolean hasValue(String option, String value) {
    String[] oV = cli.getOptionValues(option);
    if (oV != null) {
      for (String v : oV) {
        if (v.equals(value)) {
          return true;
        }
      }
      return false;
    } else {
      return true;
    }
    
  }

   
    public static void main(String[] args) {


      DataSourceProviderFactory.instance().setDataSourceProvider( DataManager.instance() );

      SuiteExecutionWrapper.instance().getOptions().addOption(new Option("h", "help", false, "Print this message"));
      Option o = new Option("t", "tags", true, "A comma separated list of test tags");
      o.setArgs(Option.UNLIMITED_VALUES);
      SuiteExecutionWrapper.instance().getOptions().addOption(o);

      o = new Option("it", "includetests", true, "A comma separated list of test identifiers that will be executed");
      o.setArgs(Option.UNLIMITED_VALUES);
      SuiteExecutionWrapper.instance().getOptions().addOption(o);

      SuiteExecutionWrapper.instance().getOptions().addOption(new Option("e", "environment", true, "The environment variable set to use during execution"));
      SuiteExecutionWrapper.instance().getOptions().addOption(new Option("p", "threadcount", true, "The maximum amount of threads to run in parellel"));
      SuiteExecutionWrapper.instance().getOptions().addOption(new Option("o", "timeout", true, "The amount of time (seconds) to wait for a test step to return before failing"));
 
      o = new Option("ir", "includerouters", true, "A comma seperated list of router identifiers.  Only targets belonging to these routers will run");
      o.setArgs(Option.UNLIMITED_VALUES);
      SuiteExecutionWrapper.instance().getOptions().addOption(o);

      o = new Option("iw", "includeintegrations", true, "A comma seperated list of integration identifiers to use");
      o.setArgs(Option.UNLIMITED_VALUES);
      SuiteExecutionWrapper.instance().getOptions().addOption(o);

      o = new Option("ie", "includetargets", true, "A comma seperated list of execution target identifiers.  Only the names targets will run");
      o.setArgs(Option.UNLIMITED_VALUES);
      SuiteExecutionWrapper.instance().getOptions().addOption(o);
      SuiteExecutionWrapper.instance().getOptions().addOption(new Option("n", "integration", true, "A property file defining one or more external integrations to add to this execution"));
      SuiteExecutionWrapper.instance().getOptions().addOption(new Option("a", "name", true, "Override the default name of this test"));
      SuiteExecutionWrapper.instance().getOptions().addOption(new Option("u", "user", true, "Override the name of the executing user"));
      SuiteExecutionWrapper.instance().getOptions().addOption(new Option("d", "description", true, "Override the description of this test"));
      SuiteExecutionWrapper.instance().getOptions().addOption(new Option("q", "queryids", false, "List the ID's of all elements that make up this suite"));
      SuiteExecutionWrapper.instance().getOptions().addOption(new Option("rd", "rundevelopment", false, "Indicates that tests in the development state should run"));
      SuiteExecutionWrapper.instance().getOptions().addOption(new Option("rq", "runquarantine", false, "Indicates that tests in the quarantine state should run"));

      SuiteExecutionWrapper.instance().addOptions( new com.orasi.integration.html.HTMLSerializer().getOptions() );
      SuiteExecutionWrapper.instance().addOptions( new com.orasi.integration.console.ExecutionConsole().getOptions() );
      

      CommandLineParser cP = new DefaultParser();
      try {
        cli = cP.parse(SuiteExecutionWrapper.instance().getOptions(), args);
      } catch( ParseException pE ) {
        System.err.println("Failed to parse command line: " + pE.getMessage());
      }

      // Default Environment Properties 
      DataManager.instance().addDefaultEnvironmentProperty( "url", DataManager.instance().replaceValues( "https://demoqa.com" ) + "" );



      Map<String,String> envMap;
      //* A production configuration containing a Production URL */
      envMap = new HashMap<>( 10 );
      
//* A staging configuration containing a Staging URL */
      envMap = new HashMap<>( 10 );
      

      

      if ( cli.hasOption( "e" ) ) {
        envMap = environmentMap.get( cli.getOptionValue( "e" ) );
        if ( envMap == null ) {
          System.err.println( "Specified Environment " + cli.getOptionValue( "e" ) + " does not exist" );
        } else {
          DataManager.instance().addEnvironmentProperties( envMap );
        }
      }

      //
      // Configuration Integrations
      //

      // Secrets Provider
      SecretsProvider sP = null;
      Integration cI = null;
      
      if ( hasValue( "iw", "11652.1331" ) ) { 
        cI = new com.orasi.integration.html.HTMLSerializer();
        log.warn( "Enabling Integration: " + cI.getTitle() );
        cI.setProperty( "outputFolder", SuiteExecutionWrapper.instance().getOption( cI.getKey(), "outputFolder", DataManager.instance().replaceValues( "." ) + "" ) );
      
        cI.initialize();
        cI.getHandlers().forEach((t) -> {
          addEventHandler(t);
        });
      }
      
      if ( hasValue( "iw", "11652.1332" ) ) { 
        cI = new com.orasi.integration.console.ExecutionConsole();
        log.warn( "Enabling Integration: " + cI.getTitle() );
        cI.setProperty( "color", SuiteExecutionWrapper.instance().getOption( cI.getKey(), "color", DataManager.instance().replaceValues( "false" ) + "" ) );
      
        cI.initialize();
        cI.getHandlers().forEach((t) -> {
          addEventHandler(t);
        });
      }
      
      
      



      if ( cli.hasOption( "h" ) ) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar Data_suite_1116_Data", SuiteExecutionWrapper.instance().getOptions() );
        System.exit( 0 );
      }

      if ( cli.hasOption( "q" ) ) {
        System.out.println( "Data suite 1116 Data (Suite): 11652.1330" ); 
        System.out.println( "Test 2 (Test): 11652.1478" );
        
        
        
        
        
        System.out.println( "F1 (Function): 11652.1972" );
        System.out.println( "Before  (Function): 11652.1988" );
        System.out.println( "After (Function): 11652.2118" );
        
	System.out.println( "Data suite 1116 Data Router (Router): 11652.1328" );
	System.out.println( "New Router (Router): 11652.2291" );
	System.out.println( "Data suite 1116 Data Browser (Execution Target): 11652.1329" );
	System.out.println( "google (Execution Target): 11652.2290" );
	
        System.out.println( "www.instagram.com (Site): 11652.2238" );
        System.out.println( "Instagram (Page): 11652.2240" );
        System.out.println( "twitter.com (Site): 11652.2120" );
        System.out.println( "X (Page): 11652.2123" );
        System.out.println( "X. Its whats happening / X (Page): 11652.2125" );
        System.out.println( "Sign up for Twitter / X (Page): 11652.2127" );
        System.out.println( "www.google.com (Site): 11652.1990" );
        System.out.println( "Google (Page): 11652.1992" );
        System.out.println( "Helloworld - Google Search (Page): 11652.2117" );
        System.out.println( "www.facebook.com (Site): 11652.1615" );
        System.out.println( "Facebook  log in or sign up (Page): 11652.1617" );
        System.out.println( "Log in to Facebook (Page): 11652.1706" );
        
        System.out.println( "D1 (Data Source): 11652.2281" );
        System.out.println( "D3 (Data Source): 11652.2283" );
        System.out.println( "D1 (Data Source): 11652.1603" );
        
        System.out.println( "HTML Generator (Integration): 11652.1331" );
        System.out.println( "Alchemy Execution Console (Integration): 11652.1332" );
        
        
        System.exit( 0 );
      }

      DataSource<String,DataTable> _dS = null;
      DataTable<String,DataField,DataRow> _dT = null;
      DataField<String> _dF = null; 
      // D1
      /* D1 */
      _dS = (DataSource) new com.orasi.datasource.spi.SyntheticDataSource();
      _dS.setProperty( "Row Count", DataManager.instance().replaceValues( "10" ) + "" );
      _dS.setProperty( "Field Type", DataManager.instance().replaceValues( "Integer" ) + "" );
      _dS.setProperty( "Format", DataManager.instance().replaceValues( "" ) + "" );
      
      _dS.initialize();
      _dT = _dS.createTable( "11652.2282" );
      _dT.setProperty( "Row Count", DataManager.instance().replaceValues( "10" ) + "" );
      _dT.initialize();
      _dS.addTable( "11652.2282", _dT );
      _dF = _dT.createField( "F1" );
      _dF.setProperty( "Field Type", DataManager.instance().replaceValues( "State" ) + "" );
      _dF.setProperty( "Format", DataManager.instance().replaceValues( "" ) + "" );
      _dF.initialize();
      _dT.addField( "F1", _dF );
      _dF.populate();
      _dF = _dT.createField( "F2" );
      _dF.setProperty( "Field Type", DataManager.instance().replaceValues( "Integer" ) + "" );
      _dF.setProperty( "Format", DataManager.instance().replaceValues( "" ) + "" );
      _dF.initialize();
      _dT.addField( "F2", _dF );
      _dF.populate();
      _dT.populate();
      _dS.populate();
      DataManager.instance().registerDataSource( "11652.2281", _dS );
      // D3
      /* D3 */
      _dS = (DataSource) new com.orasi.datasource.spi.SyntheticDataSource();
      _dS.setProperty( "Row Count", DataManager.instance().replaceValues( "10" ) + "" );
      _dS.setProperty( "Field Type", DataManager.instance().replaceValues( "Integer" ) + "" );
      _dS.setProperty( "Format", DataManager.instance().replaceValues( "" ) + "" );
      
      _dS.initialize();
      _dT = _dS.createTable( "11652.2284" );
      _dT.setProperty( "Row Count", DataManager.instance().replaceValues( "10" ) + "" );
      _dT.initialize();
      _dS.addTable( "11652.2284", _dT );
      _dF = _dT.createField( "Jj" );
      _dF.setProperty( "Field Type", DataManager.instance().replaceValues( "Letter" ) + "" );
      _dF.setProperty( "Format", DataManager.instance().replaceValues( "" ) + "" );
      _dF.initialize();
      _dT.addField( "Jj", _dF );
      _dF.populate();
      _dT.populate();
      _dT = _dS.createTable( "11652.2285" );
      _dT.setProperty( "Row Count", DataManager.instance().replaceValues( "10" ) + "" );
      _dT.initialize();
      _dS.addTable( "11652.2285", _dT );
      _dF = _dT.createField( "DF" );
      _dF.setProperty( "Field Type", DataManager.instance().replaceValues( "Domain" ) + "" );
      _dF.setProperty( "Format", DataManager.instance().replaceValues( "" ) + "" );
      _dF.initialize();
      _dT.addField( "DF", _dF );
      _dF.populate();
      _dT.populate();
      _dS.populate();
      DataManager.instance().registerDataSource( "11652.2283", _dS );
      // D1
      /* D1 */
      _dS = (DataSource) new com.orasi.datasource.spi.CSVDataSource();
      _dS.setProperty( "File Name", DataManager.instance().replaceValues( "C:\\Users\\m.prasad\\Desktop\\People.csv" ) + "" );
      _dS.setProperty( "Field Index", DataManager.instance().replaceValues( "0" ) + "" );
      _dS.setProperty( "Ignore First Row", DataManager.instance().replaceValues( "true" ) + "" );
      
      _dS.initialize();
      _dT = _dS.createTable( "11652.1604" );
      _dT.initialize();
      _dS.addTable( "11652.1604", _dT );
      _dF = _dT.createField( "Name" );
      _dF.setProperty( "Field Index", DataManager.instance().replaceValues( "0" ) + "" );
      _dF.initialize();
      _dT.addField( "Name", _dF );
      _dF.populate();
      _dF = _dT.createField( "Email id" );
      _dF.setProperty( "Field Index", DataManager.instance().replaceValues( "1" ) + "" );
      _dF.initialize();
      _dT.addField( "Email id", _dF );
      _dF.populate();
      _dF = _dT.createField( "Age" );
      _dF.setProperty( "Field Index", DataManager.instance().replaceValues( "2" ) + "" );
      _dF.initialize();
      _dT.addField( "Age", _dF );
      _dF.populate();
      _dF = _dT.createField( "Gender" );
      _dF.setProperty( "Field Index", DataManager.instance().replaceValues( "3" ) + "" );
      _dF.initialize();
      _dT.addField( "Gender", _dF );
      _dF.populate();
      _dF = _dT.createField( "Location" );
      _dF.setProperty( "Field Index", DataManager.instance().replaceValues( "4" ) + "" );
      _dF.initialize();
      _dT.addField( "Location", _dF );
      _dF.populate();
      _dF = _dT.createField( "Religion" );
      _dF.setProperty( "Field Index", DataManager.instance().replaceValues( "6" ) + "" );
      _dF.initialize();
      _dT.addField( "Religion", _dF );
      _dF.populate();
      _dF = _dT.createField( "Qualification" );
      _dF.setProperty( "Field Index", DataManager.instance().replaceValues( "5" ) + "" );
      _dF.initialize();
      _dT.addField( "Qualification", _dF );
      _dF.populate();
      _dT.populate();
      _dS.populate();
      DataManager.instance().registerDataSource( "11652.1603", _dS );
      

      if ( cli.hasOption( "a" ) ) {
        SuiteExecutionWrapper.instance().setName(cli.getOptionValue( "a" ));
      } else {
        SuiteExecutionWrapper.instance().setName("Data suite 1116 Data");
      }
      if ( cli.hasOption( "u" ) ) {
        SuiteExecutionWrapper.instance().setUserName(cli.getOptionValue( "u" ));
      } else {
        SuiteExecutionWrapper.instance().setUserName("m.prasad@orasi.com");
      }
      if ( cli.hasOption( "d" ) ) {
        SuiteExecutionWrapper.instance().setDescription(cli.getOptionValue( "d" ));
      } else {
        SuiteExecutionWrapper.instance().setDescription("Write some text defining the functionality of Data suite 1116 Data");
      }

      //
      // Configure the test level information
      //
      List<String> testList = new ArrayList(5);
      if ( hasValue( "it", "11652.1478" ) ) {
        if ( 0 == 0 || ( 0 == 1 && cli.hasOption( "rd" ) ) || ( 0 == 2 && cli.hasOption( "rq" ) ) ) {
          TestManager.instance().registerTest( new org.orasi_software__inc_.data_suite_1116_data.test_2() );
          testList.add("Test 2");
        }
      }
      
      TestManager.instance().registerFunction( new org.orasi_software__inc_.data_suite_1116_data.f1() );
      TestManager.instance().registerFunction( new org.orasi_software__inc_.data_suite_1116_data.before_() );
      TestManager.instance().registerFunction( new org.orasi_software__inc_.data_suite_1116_data.after() );
      

      
      //
      // Register the execution routers here
      //
      Router r;
      /*
      Routers from Data suite 1116 Data
      Data suite 1116 Data
      */
      if ( hasValue( "ir", "11652.1328" ) ) { 
        r = new Router( "Data suite 1116 Data Router", "11652.1328" ,"{\"name\":\"Data suite 1116 Data Router\",\"description\":\"Data suite 1116 Data Router\",\"alchemyId\":1328,\"alchemySeed\":11652,\"organizationId\":0,\"changed\":false,\"routerId\":1,\"status\":0,\"userId\":0,\"propertyList\":[{\"name\":\"URL\",\"value\":\"http://grid.alchemytesting.com:4444/wd/hub\"}]}" );
      
        r.addProperty( "URL", DataManager.instance().replaceValues( "http://grid.alchemytesting.com:4444/wd/hub" ) + "" );
        EndpointDeviceManager.instance().addRouter( r );
      }
      if ( hasValue( "ir", "11652.2291" ) ) { 
        r = new Router( "New Router", "11652.2291" ,"{\"name\":\"New Router\",\"description\":\"New Router\",\"alchemyId\":2291,\"alchemySeed\":11652,\"organizationId\":0,\"changed\":false,\"routerId\":1,\"status\":0,\"userId\":0,\"propertyList\":[{\"name\":\"URL\",\"value\":\"http://grid.alchemytesting.com:4444/wd/hub\"}]}" );
      
        r.addProperty( "URL", DataManager.instance().replaceValues( "http://grid.alchemytesting.com:4444/wd/hub" ) + "" );
        EndpointDeviceManager.instance().addRouter( r );
      }
      
 
      //
      // Configure the endpoint details here
      //
      List<String> targetList = new ArrayList<>(10);
      ExecutionTarget eT;
      /*
      Targets from Data suite 1116 Data
      Data suite 1116 Data
      */
      if ( hasValue( "ie", "11652.1329" ) ) {
        eT = new ExecutionTarget( "Data suite 1116 Data Browser", "11652.1329", "11652.1328", 1 ,"{\"name\":\"Data suite 1116 Data Browser\",\"description\":\"Data suite 1116 Data Browser\",\"alchemyId\":1329,\"alchemySeed\":11652,\"organizationId\":0,\"changed\":false,\"maximumAvailable\":1,\"executionRouterID\":{\"alchemyId\":1328,\"alchemySeed\":11652},\"propertyList\":[{\"name\":\"browserName\",\"value\":\"firefox\"},{\"name\":\"browserVersion\"},{\"name\":\"platformName\",\"value\":\"ANY\"}],\"status\":0,\"userId\":0,\"referenceSuiteID\":0}" );
      	targetList.add("Data suite 1116 Data Browser");
        eT.addProperty( "browserName", DataManager.instance().replaceValues( "firefox" ) + "" );
        eT.addProperty( "browserVersion", DataManager.instance().replaceValues( "" ) + "" );
        eT.addProperty( "platformName", DataManager.instance().replaceValues( "ANY" ) + "" );
        EndpointDeviceManager.instance().addTarget( eT );
        EndpointDeviceManager.instance().registerEndpoint( "11652.1329" );
      }
      if ( hasValue( "ie", "11652.2290" ) ) {
        eT = new ExecutionTarget( "google", "11652.2290", "11652.1328", 1 ,"{\"name\":\"google\",\"description\":\"google\",\"alchemyId\":2290,\"alchemySeed\":11652,\"organizationId\":0,\"changed\":false,\"maximumAvailable\":1,\"executionRouterID\":{\"alchemyId\":1328,\"alchemySeed\":11652},\"propertyList\":[{\"name\":\"browserName\",\"value\":\"firefox\"},{\"name\":\"browserVersion\"},{\"name\":\"platformName\",\"value\":\"ANY\"}],\"status\":0,\"userId\":0,\"referenceSuiteID\":0}" );
      	targetList.add("google");
        eT.addProperty( "browserName", DataManager.instance().replaceValues( "firefox" ) + "" );
        eT.addProperty( "browserVersion", DataManager.instance().replaceValues( "" ) + "" );
        eT.addProperty( "platformName", DataManager.instance().replaceValues( "ANY" ) + "" );
        EndpointDeviceManager.instance().addTarget( eT );
        EndpointDeviceManager.instance().registerEndpoint( "11652.2290" );
      }
      

      if ( cli.hasOption( "t" ) ) {
        TestManager.instance().setTags( cli.getOptionValues( "t" ));
      }

      if (testList.isEmpty() || targetList.isEmpty()) {
        if (testList.isEmpty()) {
          throw new IllegalArgumentException("No tests were added - nothing to do");
        }

        if (targetList.isEmpty()) {
          throw new IllegalArgumentException("No Execution Targets were defined - nowhere to run your tests");
        }
      } else {

        SuiteExecutionWrapper.instance().targetList = targetList;
        SuiteExecutionWrapper.instance().testList = testList;
        SuiteExecutionWrapper.instance().start();
      }  
    }
    
    public int getExecutionId() {
      return executionId;
    }
  
    public static SuiteExecutionWrapper instance() {
      return singleton;
    }
  
    private ExecutorService eS;
  
    public void start() {
      
      if ( cli.hasOption( "p" ) ) {
        try { threadCount = Integer.parseInt( cli.getOptionValue( "p" ) ); } catch( Exception e ) {}
      }

      if ( cli.hasOption( "o" ) ) {
        try { timeout = Integer.parseInt( cli.getOptionValue( "o" ) ); } catch( Exception e ) {}
      }

      executionId = 1;
      int totalTasks = TestManager.instance().getSize() * EndpointDeviceManager.instance().getSize();
      SuitePayload suitePayload = new SuitePayload();
      suitePayload.setExecutionIdentifier( executionId );
      suitePayload.setName(name);
      suitePayload.setDescription(description);
      suitePayload.setUserName(userName);
      suitePayload.setSuiteDetail(  "{\"id\":1676,\"name\":\"Data suite 1116 Data\",\"description\":\"Write some text defining the functionality of Data suite 1116 Data\",\"userId\":15,\"userName\":\"m.prasad@orasi.com\",\"userConfidence\":0,\"organizationId\":1,\"organizationConfidence\":0,\"status\":1,\"endpointId\":1,\"endpointStyleId\":1,\"targetId\":1731,\"targetConfigurationId\":0,\"targetVersionId\":0,\"importTests\":0,\"importFunctions\":0,\"importSites\":0,\"importTargets\":0,\"importData\":0,\"orgPermission\":1,\"publicPermission\":0,\"version\":12,\"lockUserId\":0,\"testDisplay\":0,\"alchemyId\":1330,\"alchemySeed\":11652,\"referenceSuiteID\":0,\"changed\":false}" );
      suitePayload.setTestList(testList);
      suitePayload.setTargetList(targetList);
      suitePayload.setTotalTests(totalTasks);

      notifyListeners( new SuiteEvent( suitePayload, name, 1 ) );

      
      log.warn(TestManager.instance().getSize() + " Tests executing across " + EndpointDeviceManager.instance().getSize() + " devices ");
      log.warn("Submitting " + totalTasks + " tasks for execution");
  
      eS = Executors.newFixedThreadPool( totalTasks > threadCount ? threadCount : totalTasks );
      addEventHandler( new TestFailureHandler() );
      addEventHandler( new TestNotificationHandler() );
      for (int i = 0; i < totalTasks; i++) {
        eS.submit(() -> {
          while (EndpointDeviceManager.instance().isValid()) {
  
            EndpointDevice eD = getEndpoint();
            if (eD != null) {
              try {
                TestWrapper tW = TestManager.instance().getTest(eD);
                if (tW != null) {
                  ExecutionWrapper eW = new ExecutionWrapper(eD, tW);
                  try {
                    activeExecutions.add( eW );
                    eW.run();
                  } catch (Throwable e) {
                    log.warn("Failed to execute " + tW.getName(), e);
                  } finally {
                    activeExecutions.remove( eW );
                  }
                }
              } catch (Throwable t) {
                log.warn("Error connecting to " + eD.getName(), t);
              } finally {
                EndpointDeviceManager.instance().releaseEndpoint(eD);
              }
            } else {
              try {
                Thread.sleep( 5000 );
              } catch( Exception e ) {}
            }
          }
        });
      }
  
      log.info("***** Initiating Monitor Thread *****");
      while (EndpointDeviceManager.instance().isValid() || !activeExecutions.isEmpty()) {
        try {
          Thread.sleep(5000);
          
          boolean stillRunning = false;
          for ( ExecutionWrapper eW : activeExecutions ) {
            
            if ( log.isDebugEnabled() ) { 
              log.debug( "Running " + eW.getTestWrapper().getName() + " on " + eW.getEndpointDevice().getName() );
            }
            
            long lastAccess = System.currentTimeMillis() - eW.getLastAccess();
            if ( ( System.currentTimeMillis() - eW.getLastAccess() ) > timeout ) {
              log.warn( "There has been no activity on " + eW.getEndpointDevice().getName() + " running " + eW.getTestWrapper().getName() + " for " + (lastAccess / 1000 ) + " seconds" );
            } else {
              stillRunning = true;
            }
          }
          
          if ( !stillRunning && !activeExecutions.isEmpty() ) {
            log.error( "The only threads remaining had not responded in over " + (timeout / 1000) + "seconds.  Attempting to shutdown the execution wrapper..." );
            break;
          }
          
          
        } catch (Exception e) {
  	  log.error( "Error waiting", e );
        }
      }

    if ( failureCount > 0 ) {
      notifyListeners(new SuiteEvent(suitePayload, name, EventHook.FAILURE.getId()));
    } else {
      notifyListeners(new SuiteEvent(suitePayload, name, EventHook.SUCCESS.getId()));
    }

      notifyListeners( new SuiteEvent( suitePayload, name, EventHook.AFTER.getId() ) );
      
      
      log.warn( "Shutting Down Execution Wrapper" );
      
      this.eS.shutdownNow();
  
    }
  
    private EndpointDevice getEndpoint() {
      EndpointDevice eD = EndpointDeviceManager.instance().acquireEndpoint();
  
      if (eD != null) {
        return eD;
      } else {
        if (log.isDebugEnabled()) {
          log.debug("There are no devices free");
        }
      }
  
      return null;
    }
  
    /**
     * @return the name
     */
    public String getName() {
      return name;
    }
  
    /**
     * @param name the name to set
     */
    private void setName(String name) {
      this.name = name;
    }
  
    /**
     * @return the description
     */
    public String getDescription() {
      return description;
    }
  
    /**
     * @param description the description to set
     */
    private void setDescription(String description) {
      this.description = description;
    }
  
    /**
     * @return the id
     */
    public int getId() {
      return id;
    }
  
    /**
     * @param id the id to set
     */
    private void setId(int id) {
      this.id = id;
    }
  
    /**
     * @return the userName
     */
    public String getUserName() {
      return userName;
    }
  
    /**
     * @param userName the userName to set
     */
    private void setUserName(String userName) {
      this.userName = userName;
    }
  
  }
  