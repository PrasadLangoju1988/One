/**
 * @author m.prasad@orasi.com
 * @author Orasi Software, Inc.
 * @version 2
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
      DataManager.instance().addDefaultEnvironmentProperty( "url", DataManager.instance().replaceValues( "http://demoqa.com" ) + "" );



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
      
      if ( hasValue( "iw", "13334.1603" ) ) { 
        cI = new com.orasi.integration.html.HTMLSerializer();
        log.warn( "Enabling Integration: " + cI.getTitle() );
        cI.setProperty( "outputFolder", SuiteExecutionWrapper.instance().getOption( cI.getKey(), "outputFolder", DataManager.instance().replaceValues( "c:/1116" ) + "" ) );
      
        cI.initialize();
        cI.getHandlers().forEach((t) -> {
          addEventHandler(t);
        });
      }
      
      if ( hasValue( "iw", "13334.1604" ) ) { 
        cI = new com.orasi.integration.console.ExecutionConsole();
        log.warn( "Enabling Integration: " + cI.getTitle() );
        cI.setProperty( "color", SuiteExecutionWrapper.instance().getOption( cI.getKey(), "color", DataManager.instance().replaceValues( "true" ) + "" ) );
      
        cI.initialize();
        cI.getHandlers().forEach((t) -> {
          addEventHandler(t);
        });
      }
      
      
      



      if ( cli.hasOption( "h" ) ) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar Testing_compilation", SuiteExecutionWrapper.instance().getOptions() );
        System.exit( 0 );
      }

      if ( cli.hasOption( "q" ) ) {
        System.out.println( "Testing compilation (Suite): 13334.1602" ); 
        System.out.println( "Test One compilation (Test): 13334.1606" );
        
        
        
	System.out.println( "Testing compilation Router (Router): 13334.1600" );
	System.out.println( "Testing compilation Browser (Execution Target): 13334.1601" );
	
        System.out.println( "demoqa.com (Site): 13334.1608" );
        System.out.println( "DEMOQA (Page): 13334.1610" );
        
        
        System.out.println( "HTML Generator (Integration): 13334.1603" );
        System.out.println( "Alchemy Execution Console (Integration): 13334.1604" );
        
        
        System.exit( 0 );
      }

      DataSource<String,DataTable> _dS = null;
      DataTable<String,DataField,DataRow> _dT = null;
      DataField<String> _dF = null; 
      

      if ( cli.hasOption( "a" ) ) {
        SuiteExecutionWrapper.instance().setName(cli.getOptionValue( "a" ));
      } else {
        SuiteExecutionWrapper.instance().setName("Testing compilation");
      }
      if ( cli.hasOption( "u" ) ) {
        SuiteExecutionWrapper.instance().setUserName(cli.getOptionValue( "u" ));
      } else {
        SuiteExecutionWrapper.instance().setUserName("m.prasad@orasi.com");
      }
      if ( cli.hasOption( "d" ) ) {
        SuiteExecutionWrapper.instance().setDescription(cli.getOptionValue( "d" ));
      } else {
        SuiteExecutionWrapper.instance().setDescription("Write some text defining the functionality of Testing compilation");
      }

      //
      // Configure the test level information
      //
      List<String> testList = new ArrayList(5);
      if ( hasValue( "it", "13334.1606" ) ) {
        if ( 0 == 0 || ( 0 == 1 && cli.hasOption( "rd" ) ) || ( 0 == 2 && cli.hasOption( "rq" ) ) ) {
          TestManager.instance().registerTest( new org.orasi_software__inc_.testing_compilation.test_one_compilation() );
          testList.add("Test One compilation");
        }
      }
      
      

      
      //
      // Register the execution routers here
      //
      Router r;
      /*
      Routers from Testing compilation
      Testing compilation
      */
      if ( hasValue( "ir", "13334.1600" ) ) { 
        r = new Router( "Testing compilation Router", "13334.1600" ,"{\"name\":\"Testing compilation Router\",\"description\":\"Testing compilation Router\",\"alchemyId\":1600,\"alchemySeed\":13334,\"organizationId\":0,\"changed\":false,\"routerId\":1,\"status\":0,\"userId\":0,\"propertyList\":[{\"name\":\"URL\",\"value\":\"http://grid.alchemytesting.com:4444/wd/hub\"}]}" );
      
        r.addProperty( "URL", DataManager.instance().replaceValues( "http://grid.alchemytesting.com:4444/wd/hub" ) + "" );
        EndpointDeviceManager.instance().addRouter( r );
      }
      
 
      //
      // Configure the endpoint details here
      //
      List<String> targetList = new ArrayList<>(10);
      ExecutionTarget eT;
      /*
      Targets from Testing compilation
      Testing compilation
      */
      if ( hasValue( "ie", "13334.1601" ) ) {
        eT = new ExecutionTarget( "Testing compilation Browser", "13334.1601", "13334.1600", 1 ,"{\"name\":\"Testing compilation Browser\",\"description\":\"Testing compilation Browser\",\"alchemyId\":1601,\"alchemySeed\":13334,\"organizationId\":0,\"changed\":false,\"maximumAvailable\":1,\"executionRouterID\":{\"alchemyId\":1600,\"alchemySeed\":13334},\"propertyList\":[{\"name\":\"browserName\",\"value\":\"firefox\"},{\"name\":\"browserVersion\"},{\"name\":\"platformName\",\"value\":\"ANY\"}],\"status\":0,\"userId\":0,\"referenceSuiteID\":0}" );
      	targetList.add("Testing compilation Browser");
        eT.addProperty( "browserName", DataManager.instance().replaceValues( "firefox" ) + "" );
        eT.addProperty( "browserVersion", DataManager.instance().replaceValues( "" ) + "" );
        eT.addProperty( "platformName", DataManager.instance().replaceValues( "ANY" ) + "" );
        EndpointDeviceManager.instance().addTarget( eT );
        EndpointDeviceManager.instance().registerEndpoint( "13334.1601" );
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
      suitePayload.setSuiteDetail(  "{\"id\":1715,\"name\":\"Testing compilation\",\"description\":\"Write some text defining the functionality of Testing compilation\",\"userId\":15,\"userName\":\"m.prasad@orasi.com\",\"userConfidence\":0,\"organizationId\":1,\"organizationConfidence\":0,\"status\":1,\"endpointId\":1,\"endpointStyleId\":1,\"targetId\":1772,\"targetConfigurationId\":0,\"targetVersionId\":0,\"importTests\":0,\"importFunctions\":0,\"importSites\":0,\"importTargets\":0,\"importData\":0,\"orgPermission\":1,\"publicPermission\":0,\"version\":2,\"lockUserId\":0,\"testDisplay\":0,\"alchemyId\":1602,\"alchemySeed\":13334,\"referenceSuiteID\":0,\"changed\":false}" );
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
  