/**
  * @author m.prasad@orasi.com
  * @author Orasi Software, Inc.
  * @version 1
  */
 package org.orasi_software__inc_.suite_for_testing_;
  
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
 
 public class test_one_test extends AbstractTestWrapper
 {
   public test_one_test() {
     super(6133, "13409.3959", "Test One test", "", 0, "", "".split(","), "{\"symphonyId\":0,\"id\":6133,\"name\":\"Test One test\",\"description\":\"\",\"userId\":15,\"userName\":\"m.prasad@orasi.com\",\"userConfidence\":0,\"organizationId\":1,\"organizationName\":\"Orasi Software, Inc.\",\"organizationConfidence\":0,\"status\":1,\"orgPermission\":1,\"publicPermission\":0,\"createDate\":\"Oct 18, 2023, 5:37:48 AM\",\"modifyDate\":\"Oct 18, 2023, 7:08:13 AM\",\"version\":1,\"lockUserId\":0,\"level\":1,\"pre\":\"\",\"post\":\"\",\"deviceTagNames\":\"\",\"changeCount\":0,\"uniqueContributors\":0,\"stepCount\":0,\"testUserConfidence\":0.0,\"errorHandler\":\"\",\"classificationId\":0,\"synchronizationId\":0,\"storageVersion\":2,\"userTouch\":[],\"alchemyId\":3959,\"alchemySeed\":13409,\"referenceSuiteID\":0,\"changed\":false}");
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
    String testName = "test_one_test";

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

      vM.addVariable( new VariableWrapper( "url", dS.replaceValues( "http://demoqa.com", contextMap ) + "", dS.replaceValues( "http://demoqa.com", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "timeout", parseNumber( dS.replaceValues( "30000", contextMap ), Long.class ), dS.replaceValues( "30000", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "navigate_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":82,\"checkpointId\":1,\"alchemyId\":4112,\"alchemySeed\":13409,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"http://demoqa.com\",\"inputId\":7,\"templateId\":0,\"inputName\":\"url\",\"inputDescription\":\"The Application Locator\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"30000\",\"inputId\":7,\"templateId\":0,\"inputName\":\"timeout\",\"inputDescription\":\"The time in milliseconds to wait for a page to load\",\"inputData\":\"null\",\"inputTypeId\":2,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":5,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Navigate to {var:url}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "13409.3966", contextMap ), contextMap, dS ), dS.replaceValues( "13409.3966", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "method", dS.replaceValues( "Default", contextMap ), dS.replaceValues( "Default", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "click_v7", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":189,\"checkpointId\":1,\"alchemyId\":4113,\"alchemySeed\":13409,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"13409.3966\",\"inputId\":1,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The name of the locator retrieved from the elements\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"Default\",\"inputId\":59,\"templateId\":0,\"inputName\":\"method\",\"inputDescription\":\"The method used to click.  If omitted, default is used\",\"inputData\":\"Default,No Wait,Simple,Double,Right\",\"inputTypeId\":10,\"inputRequired\":0,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":1,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":7,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Click on {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "13409.3977", contextMap ), contextMap, dS ), dS.replaceValues( "13409.3977", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "method", dS.replaceValues( "Default", contextMap ), dS.replaceValues( "Default", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "click_v7", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":189,\"checkpointId\":1,\"alchemyId\":4114,\"alchemySeed\":13409,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"13409.3977\",\"inputId\":1,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The name of the locator retrieved from the elements\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"Default\",\"inputId\":59,\"templateId\":0,\"inputName\":\"method\",\"inputDescription\":\"The method used to click.  If omitted, default is used\",\"inputData\":\"Default,No Wait,Simple,Double,Right\",\"inputTypeId\":10,\"inputRequired\":0,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":1,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":7,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Click on {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "13409.3988", contextMap ), contextMap, dS ), dS.replaceValues( "13409.3988", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "method", dS.replaceValues( "Default", contextMap ), dS.replaceValues( "Default", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "click_v7", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":189,\"checkpointId\":1,\"alchemyId\":4115,\"alchemySeed\":13409,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"13409.3988\",\"inputId\":1,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The name of the locator retrieved from the elements\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"Default\",\"inputId\":59,\"templateId\":0,\"inputName\":\"method\",\"inputDescription\":\"The method used to click.  If omitted, default is used\",\"inputData\":\"Default,No Wait,Simple,Double,Right\",\"inputTypeId\":10,\"inputRequired\":0,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":1,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":7,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Click on {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "value", dS.replaceValues( "Prasad Langoju", contextMap ) + "", dS.replaceValues( "Prasad Langoju", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "clearFirst", dS.replaceValues( "true", contextMap ), dS.replaceValues( "true", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "13409.3988", contextMap ), contextMap, dS ), dS.replaceValues( "13409.3988", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "type_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":85,\"checkpointId\":1,\"alchemyId\":4116,\"alchemySeed\":13409,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"Prasad Langoju\",\"inputId\":31,\"templateId\":0,\"inputName\":\"value\",\"inputDescription\":\"The text to type\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"true\",\"inputId\":32,\"templateId\":0,\"inputName\":\"clearFirst\",\"inputDescription\":\"A flag indicating if the value should first be cleared\",\"inputData\":\"null\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"13409.3988\",\"inputId\":33,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The element that will receive the text\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":17,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Type {var:value} into {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "Control", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Shift", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Alt", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Key", dS.replaceValues( "TAB", contextMap ) + "", dS.replaceValues( "TAB", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "keys_v1", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":80,\"checkpointId\":1,\"alchemyId\":4117,\"alchemySeed\":13409,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Control\",\"inputDescription\":\"Is the Control key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Shift\",\"inputDescription\":\"Is the Shift key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Alt\",\"inputDescription\":\"Was the Alt key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"TAB\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Key\",\"inputDescription\":\"An attempt will be made first to find a constart mapping to this string.  If not found, it will send the keys supplied as individual characters\",\"inputData\":\"\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":35,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":1,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Send the keys {var:Key} to your application\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "value", dS.replaceValues( "srimanilangoju@gmail.com", contextMap ) + "", dS.replaceValues( "srimanilangoju@gmail.com", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "clearFirst", dS.replaceValues( "true", contextMap ), dS.replaceValues( "true", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "13409.4017", contextMap ), contextMap, dS ), dS.replaceValues( "13409.4017", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "type_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":85,\"checkpointId\":1,\"alchemyId\":4118,\"alchemySeed\":13409,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"srimanilangoju@gmail.com\",\"inputId\":31,\"templateId\":0,\"inputName\":\"value\",\"inputDescription\":\"The text to type\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"true\",\"inputId\":32,\"templateId\":0,\"inputName\":\"clearFirst\",\"inputDescription\":\"A flag indicating if the value should first be cleared\",\"inputData\":\"null\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"13409.4017\",\"inputId\":33,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The element that will receive the text\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":17,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Type {var:value} into {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "Control", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Shift", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Alt", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Key", dS.replaceValues( "TAB", contextMap ) + "", dS.replaceValues( "TAB", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "keys_v1", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":80,\"checkpointId\":1,\"alchemyId\":4119,\"alchemySeed\":13409,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Control\",\"inputDescription\":\"Is the Control key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Shift\",\"inputDescription\":\"Is the Shift key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Alt\",\"inputDescription\":\"Was the Alt key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"TAB\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Key\",\"inputDescription\":\"An attempt will be made first to find a constart mapping to this string.  If not found, it will send the keys supplied as individual characters\",\"inputData\":\"\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":35,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":1,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Send the keys {var:Key} to your application\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "value", dS.replaceValues( "Hyderabad", contextMap ) + "", dS.replaceValues( "Hyderabad", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "clearFirst", dS.replaceValues( "true", contextMap ), dS.replaceValues( "true", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "13409.4045", contextMap ), contextMap, dS ), dS.replaceValues( "13409.4045", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "type_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":85,\"checkpointId\":1,\"alchemyId\":4120,\"alchemySeed\":13409,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"Hyderabad\",\"inputId\":31,\"templateId\":0,\"inputName\":\"value\",\"inputDescription\":\"The text to type\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"true\",\"inputId\":32,\"templateId\":0,\"inputName\":\"clearFirst\",\"inputDescription\":\"A flag indicating if the value should first be cleared\",\"inputData\":\"null\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"13409.4045\",\"inputId\":33,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The element that will receive the text\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":17,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Type {var:value} into {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "Control", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Shift", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Alt", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Key", dS.replaceValues( "TAB", contextMap ) + "", dS.replaceValues( "TAB", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "keys_v1", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":80,\"checkpointId\":1,\"alchemyId\":4121,\"alchemySeed\":13409,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Control\",\"inputDescription\":\"Is the Control key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Shift\",\"inputDescription\":\"Is the Shift key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Alt\",\"inputDescription\":\"Was the Alt key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"TAB\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Key\",\"inputDescription\":\"An attempt will be made first to find a constart mapping to this string.  If not found, it will send the keys supplied as individual characters\",\"inputData\":\"\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":35,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":1,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Send the keys {var:Key} to your application\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "value", dS.replaceValues( "Hyderbad", contextMap ) + "", dS.replaceValues( "Hyderbad", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "clearFirst", dS.replaceValues( "true", contextMap ), dS.replaceValues( "true", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "13409.4069", contextMap ), contextMap, dS ), dS.replaceValues( "13409.4069", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "type_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":85,\"checkpointId\":1,\"alchemyId\":4122,\"alchemySeed\":13409,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"Hyderbad\",\"inputId\":31,\"templateId\":0,\"inputName\":\"value\",\"inputDescription\":\"The text to type\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"true\",\"inputId\":32,\"templateId\":0,\"inputName\":\"clearFirst\",\"inputDescription\":\"A flag indicating if the value should first be cleared\",\"inputData\":\"null\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"13409.4069\",\"inputId\":33,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The element that will receive the text\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":17,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Type {var:value} into {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "Control", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Shift", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Alt", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Key", dS.replaceValues( "TAB", contextMap ) + "", dS.replaceValues( "TAB", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "keys_v1", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":80,\"checkpointId\":1,\"alchemyId\":4123,\"alchemySeed\":13409,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Control\",\"inputDescription\":\"Is the Control key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Shift\",\"inputDescription\":\"Is the Shift key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Alt\",\"inputDescription\":\"Was the Alt key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"TAB\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Key\",\"inputDescription\":\"An attempt will be made first to find a constart mapping to this string.  If not found, it will send the keys supplied as individual characters\",\"inputData\":\"\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":35,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":1,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Send the keys {var:Key} to your application\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "Control", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Shift", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Alt", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Key", dS.replaceValues( "ENTER", contextMap ) + "", dS.replaceValues( "ENTER", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "keys_v1", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":80,\"checkpointId\":1,\"alchemyId\":4124,\"alchemySeed\":13409,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Control\",\"inputDescription\":\"Is the Control key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Shift\",\"inputDescription\":\"Is the Shift key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Alt\",\"inputDescription\":\"Was the Alt key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"ENTER\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Key\",\"inputDescription\":\"An attempt will be made first to find a constart mapping to this string.  If not found, it will send the keys supplied as individual characters\",\"inputData\":\"\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":35,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":1,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Send the keys {var:Key} to your application\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      

      
      
      
      
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