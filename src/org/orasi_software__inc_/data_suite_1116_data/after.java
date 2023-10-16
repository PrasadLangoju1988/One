/**
  * @author m.prasad@orasi.com
  * @author Orasi Software, Inc.
  * @version 4
  */
 package org.orasi_software__inc_.data_suite_1116_data;
  
 import java.util.*;
 import java.util.function.*;
 import java.io.*;
 import java.lang.reflect.*;
 import org.apache.commons.logging.*;
 import org.openqa.selenium.*;
 import org.openqa.selenium.interactions.*;
 import org.openqa.selenium.remote.*;
 import org.openqa.selenium.support.ui.*;
 import com.google.gson.*;
 import static com.orasi.shared_library.*;
 import com.orasi.event.spi.*;
 import com.orasi.event.configuration.*;
 import com.orasi.*;
 import com.orasi.model.*;
 import com.orasi.datasource.*;
 import com.orasi.alchemy.mediation.execution.*;
 import java.time.*;
 
 public class after extends AbstractTestWrapper
 {
   public after() {
     super(6059, "11652.2118", "After", "", 0, "", "".split(","), "{\"symphonyId\":0,\"id\":6059,\"name\":\"After\",\"description\":\"\",\"userId\":15,\"userName\":\"m.prasad@orasi.com\",\"userConfidence\":0,\"organizationId\":1,\"organizationName\":\"Orasi Software, Inc.\",\"organizationConfidence\":0,\"status\":1,\"orgPermission\":1,\"publicPermission\":0,\"createDate\":\"Aug 29, 2023, 12:22:55 PM\",\"modifyDate\":\"Aug 29, 2023, 12:25:26 PM\",\"version\":4,\"lockUserId\":0,\"level\":2,\"pre\":\"11652.1988\",\"post\":\"11652.2118\",\"deviceTagNames\":\"\",\"changeCount\":0,\"uniqueContributors\":0,\"stepCount\":0,\"testUserConfidence\":0.0,\"errorHandler\":\"\",\"classificationId\":0,\"synchronizationId\":0,\"storageVersion\":2,\"userTouch\":[],\"alchemyId\":2118,\"alchemySeed\":11652,\"referenceSuiteID\":0,\"changed\":false}");
   }

   private void sleep( long sleepTime ) {
    try {
      Thread.sleep( sleepTime );
    } catch( InterruptedException ignoreMe) {
      
    }
  }
 
 	@Override
   public void _executeTest( int executionId, int testExecutionId, final WebDriver webDriver, final Map<String,Object> contextMap, String contextName, final Stack<String> callStack, final Stack<Integer> stepStack )
   {
    String testName = "after";

    if ( contextMap.get( "__callStyle" ) == null ) { contextMap.put( "__callStyle", 1 ); }
    callStack.push( getClass().getName() );
    if ( stepStack.isEmpty() ) stepStack.push( 0 );
    int callStyle = (Integer) contextMap.get( "__callStyle" );
    final Deque<StepEvent> eventList = new ArrayDeque<>( 10 );

    DataSourceProvider dS = DataSourceProviderFactory.instance().getDataSourceProvider();
    if ( dS == null ) {
      throw new IllegalArgumentException( "No Data Source Provider was specified" );
    }

    //
    // Initialize the Action Mediator for this test
    //
    ActionMediator aM = new ActionMediator(executionId, testExecutionId, testName, webDriver, contextMap, callStack, stepStack, callStyle);
    VariableMediator vM = new VariableMediator();

    try
    {
      

      
      vM.clear();

      vM.addVariable( new VariableWrapper( "url", dS.replaceValues( "https://www.instagram.com", contextMap ) + "", dS.replaceValues( "https://www.instagram.com", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "timeout", parseNumber( dS.replaceValues( "30000", contextMap ), Long.class ), dS.replaceValues( "30000", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "navigate_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":82,\"checkpointId\":1,\"alchemyId\":2241,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"https://www.instagram.com\",\"inputId\":7,\"templateId\":0,\"inputName\":\"url\",\"inputDescription\":\"The Application Locator\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"30000\",\"inputId\":7,\"templateId\":0,\"inputName\":\"timeout\",\"inputDescription\":\"The time in milliseconds to wait for a page to load\",\"inputData\":\"null\",\"inputTypeId\":2,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":5,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Navigate to {var:url}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "value", dS.replaceValues( "${11652.1603-11652.1604-Email id}", contextMap ) + "", dS.replaceValues( "${11652.1603-11652.1604-Email id}", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "clearFirst", dS.replaceValues( "true", contextMap ), dS.replaceValues( "true", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "11652.2243", contextMap ), contextMap, dS ), dS.replaceValues( "11652.2243", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "type_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":85,\"checkpointId\":1,\"alchemyId\":2278,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"${11652.1603-11652.1604-Email id}\",\"inputId\":31,\"templateId\":0,\"inputName\":\"value\",\"inputDescription\":\"The text to type\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"true\",\"inputId\":32,\"templateId\":0,\"inputName\":\"clearFirst\",\"inputDescription\":\"A flag indicating if the value should first be cleared\",\"inputData\":\"null\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"11652.2243\",\"inputId\":33,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The element that will receive the text\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":17,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Type {var:value} into {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      

      
    } catch( BreakException e ) {
      throw e;
    } catch (EOTException e) {
      if (e.getStepPayload() == null) {
        e.setStepPayload(new StepPayload());
      }
      e.getStepPayload().setMessage(e.getMessage());
      if (!e.isTestPassed()) {
        try {
          e.getStepPayload().setScreenShot(screenShot(webDriver));
          e.getStepPayload().setSource(webDriver.getPageSource());
          
        } catch (Exception _e) {
          e.getStepPayload().setMessage("Could not acquire details from connected endpoint");
        }
      }
      throw e;
    } catch (StepException e) {
      log.error( e.getMessage(), e );
      if (e.getStepPayload() == null) {
        e.setStepPayload( new StepPayload() );
      }
      try {
        e.getStepPayload().setScreenShot(screenShot(webDriver));
        e.getStepPayload().setSource(webDriver.getPageSource());
        e.getStepPayload().setMessage(e.getMessage());
      } catch (Exception _e) {
        e.getStepPayload().setMessage("Could not acquire details from connected endpoint");
      }
      notifyListeners(new StepEvent(e.getStepPayload(), testName, 3));
      throw e;
    } finally {
      if ( eventList.peek() != null ) {
        notifyListeners(new StepEvent(eventList.peek().getPayload(), testName, 2));
      } else {
        notifyListeners(new StepEvent(new StepPayload(), testName, 2));
      }
      callStack.pop();
    }
  }

  
  private byte[] screenShot( WebDriver webDriver ) {
    if ( webDriver instanceof TakesScreenshot ) {
      return ( (TakesScreenshot) webDriver ).getScreenshotAs( OutputType.BYTES );
    } else {
      return null;
    }
  }
 }