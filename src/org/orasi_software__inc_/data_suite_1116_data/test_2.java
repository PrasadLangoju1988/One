/**
  * @author m.prasad@orasi.com
  * @author Orasi Software, Inc.
  * @version 2
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
 
 public class test_2 extends AbstractTestWrapper
 {
   public test_2() {
     super(6056, "11652.1478", "Test 2", "", 0, "", "".split(","), "{\"symphonyId\":0,\"id\":6056,\"name\":\"Test 2\",\"description\":\"\",\"userId\":15,\"userName\":\"m.prasad@orasi.com\",\"userConfidence\":0,\"organizationId\":1,\"organizationName\":\"Orasi Software, Inc.\",\"organizationConfidence\":0,\"status\":1,\"orgPermission\":1,\"publicPermission\":0,\"createDate\":\"Aug 29, 2023, 9:46:42 AM\",\"modifyDate\":\"Aug 29, 2023, 12:16:42 PM\",\"version\":2,\"lockUserId\":0,\"level\":1,\"pre\":\"11652.1988\",\"post\":\"11652.2118\",\"deviceTagNames\":\"\",\"changeCount\":0,\"uniqueContributors\":0,\"stepCount\":0,\"testUserConfidence\":0.0,\"errorHandler\":\"\",\"classificationId\":0,\"synchronizationId\":0,\"storageVersion\":2,\"userTouch\":[],\"alchemyId\":1478,\"alchemySeed\":11652,\"referenceSuiteID\":0,\"changed\":false}");
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
    String testName = "test_2";

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
      
      
      FunctionExecutionMediator.instance().getFunctionExecutor().executeFunction( "11652.1988", executionId, testExecutionId, webDriver, new HashMap<>(5), contextMap, contextName, callStack, stepStack );
      

      
      vM.clear();

      vM.addVariable( new VariableWrapper( "url", dS.replaceValues( "https://www.facebook.com", contextMap ) + "", dS.replaceValues( "https://www.facebook.com", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "timeout", parseNumber( dS.replaceValues( "30000", contextMap ), Long.class ), dS.replaceValues( "30000", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "navigate_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":82,\"checkpointId\":1,\"alchemyId\":1709,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"https://www.facebook.com\",\"inputId\":7,\"templateId\":0,\"inputName\":\"url\",\"inputDescription\":\"The Application Locator\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"30000\",\"inputId\":7,\"templateId\":0,\"inputName\":\"timeout\",\"inputDescription\":\"The time in milliseconds to wait for a page to load\",\"inputData\":\"null\",\"inputTypeId\":2,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":5,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Navigate to {var:url}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "11652.1711", contextMap ), contextMap, dS ), dS.replaceValues( "11652.1711", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "method", dS.replaceValues( "Default", contextMap ), dS.replaceValues( "Default", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "click_v7", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":189,\"checkpointId\":1,\"alchemyId\":1752,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"11652.1711\",\"inputId\":1,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The name of the locator retrieved from the elements\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"Default\",\"inputId\":59,\"templateId\":0,\"inputName\":\"method\",\"inputDescription\":\"The method used to click.  If omitted, default is used\",\"inputData\":\"Default,No Wait,Simple,Double,Right\",\"inputTypeId\":10,\"inputRequired\":0,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":1,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":7,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Click on {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "value", dS.replaceValues( "${11652.1603-11652.1604-Email id}", contextMap ) + "", dS.replaceValues( "${11652.1603-11652.1604-Email id}", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "clearFirst", dS.replaceValues( "true", contextMap ), dS.replaceValues( "true", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "11652.1711", contextMap ), contextMap, dS ), dS.replaceValues( "11652.1711", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "type_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":85,\"checkpointId\":1,\"alchemyId\":1753,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"${11652.1603-11652.1604-Email id}\",\"inputId\":31,\"templateId\":0,\"inputName\":\"value\",\"inputDescription\":\"The text to type\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"true\",\"inputId\":32,\"templateId\":0,\"inputName\":\"clearFirst\",\"inputDescription\":\"A flag indicating if the value should first be cleared\",\"inputData\":\"null\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"11652.1711\",\"inputId\":33,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The element that will receive the text\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":17,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Type {var:value} into {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "Control", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Shift", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Alt", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Key", dS.replaceValues( "TAB", contextMap ) + "", dS.replaceValues( "TAB", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "keys_v1", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":80,\"checkpointId\":1,\"alchemyId\":1754,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Control\",\"inputDescription\":\"Is the Control key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Shift\",\"inputDescription\":\"Is the Shift key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Alt\",\"inputDescription\":\"Was the Alt key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"TAB\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Key\",\"inputDescription\":\"An attempt will be made first to find a constart mapping to this string.  If not found, it will send the keys supplied as individual characters\",\"inputData\":\"\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":35,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":1,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Send the keys {var:Key} to your application\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "value", dS.replaceValues( "${11652.1603-11652.1604-Name}", contextMap ) + "", dS.replaceValues( "${11652.1603-11652.1604-Name}", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "clearFirst", dS.replaceValues( "true", contextMap ), dS.replaceValues( "true", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "11652.1620", contextMap ), contextMap, dS ), dS.replaceValues( "11652.1620", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "type_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":85,\"checkpointId\":1,\"alchemyId\":1755,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"${11652.1603-11652.1604-Name}\",\"inputId\":31,\"templateId\":0,\"inputName\":\"value\",\"inputDescription\":\"The text to type\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"true\",\"inputId\":32,\"templateId\":0,\"inputName\":\"clearFirst\",\"inputDescription\":\"A flag indicating if the value should first be cleared\",\"inputData\":\"null\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"11652.1620\",\"inputId\":33,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The element that will receive the text\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":17,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Type {var:value} into {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "11652.1757", contextMap ), contextMap, dS ), dS.replaceValues( "11652.1757", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "method", dS.replaceValues( "Default", contextMap ), dS.replaceValues( "Default", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "click_v7", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":189,\"checkpointId\":1,\"alchemyId\":1784,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"11652.1757\",\"inputId\":1,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The name of the locator retrieved from the elements\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"Default\",\"inputId\":59,\"templateId\":0,\"inputName\":\"method\",\"inputDescription\":\"The method used to click.  If omitted, default is used\",\"inputData\":\"Default,No Wait,Simple,Double,Right\",\"inputTypeId\":10,\"inputRequired\":0,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":1,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":7,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Click on {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "11652.1786", contextMap ), contextMap, dS ), dS.replaceValues( "11652.1786", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "method", dS.replaceValues( "Default", contextMap ), dS.replaceValues( "Default", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "click_v7", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":189,\"checkpointId\":1,\"alchemyId\":1819,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"11652.1786\",\"inputId\":1,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The name of the locator retrieved from the elements\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"Default\",\"inputId\":59,\"templateId\":0,\"inputName\":\"method\",\"inputDescription\":\"The method used to click.  If omitted, default is used\",\"inputData\":\"Default,No Wait,Simple,Double,Right\",\"inputTypeId\":10,\"inputRequired\":0,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":1,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":7,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Click on {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "value", dS.replaceValues( "${11652.1603-11652.1604-Age}", contextMap ) + "", dS.replaceValues( "${11652.1603-11652.1604-Age}", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "clearFirst", dS.replaceValues( "true", contextMap ), dS.replaceValues( "true", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "11652.1821", contextMap ), contextMap, dS ), dS.replaceValues( "11652.1821", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "type_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":85,\"checkpointId\":1,\"alchemyId\":1860,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"${11652.1603-11652.1604-Age}\",\"inputId\":31,\"templateId\":0,\"inputName\":\"value\",\"inputDescription\":\"The text to type\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"true\",\"inputId\":32,\"templateId\":0,\"inputName\":\"clearFirst\",\"inputDescription\":\"A flag indicating if the value should first be cleared\",\"inputData\":\"null\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"11652.1821\",\"inputId\":33,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The element that will receive the text\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":17,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Type {var:value} into {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "Control", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Shift", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Alt", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Key", dS.replaceValues( "TAB", contextMap ) + "", dS.replaceValues( "TAB", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "keys_v1", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":80,\"checkpointId\":1,\"alchemyId\":1861,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Control\",\"inputDescription\":\"Is the Control key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Shift\",\"inputDescription\":\"Is the Shift key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Alt\",\"inputDescription\":\"Was the Alt key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"TAB\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Key\",\"inputDescription\":\"An attempt will be made first to find a constart mapping to this string.  If not found, it will send the keys supplied as individual characters\",\"inputData\":\"\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":35,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":1,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Send the keys {var:Key} to your application\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "value", dS.replaceValues( "${11652.1603-11652.1604-Gender}", contextMap ) + "", dS.replaceValues( "${11652.1603-11652.1604-Gender}", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "clearFirst", dS.replaceValues( "true", contextMap ), dS.replaceValues( "true", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "11652.1863", contextMap ), contextMap, dS ), dS.replaceValues( "11652.1863", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "type_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":85,\"checkpointId\":1,\"alchemyId\":1894,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"${11652.1603-11652.1604-Gender}\",\"inputId\":31,\"templateId\":0,\"inputName\":\"value\",\"inputDescription\":\"The text to type\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"true\",\"inputId\":32,\"templateId\":0,\"inputName\":\"clearFirst\",\"inputDescription\":\"A flag indicating if the value should first be cleared\",\"inputData\":\"null\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"11652.1863\",\"inputId\":33,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The element that will receive the text\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":17,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Type {var:value} into {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "Control", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Shift", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Alt", dS.replaceValues( "false", contextMap ), dS.replaceValues( "false", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "Key", dS.replaceValues( "TAB", contextMap ) + "", dS.replaceValues( "TAB", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "keys_v1", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":80,\"checkpointId\":1,\"alchemyId\":1895,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Control\",\"inputDescription\":\"Is the Control key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Shift\",\"inputDescription\":\"Is the Shift key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"false\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Alt\",\"inputDescription\":\"Was the Alt key pressed as part of this key sequence\",\"inputData\":\"\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"TAB\",\"inputId\":0,\"templateId\":0,\"inputName\":\"Key\",\"inputDescription\":\"An attempt will be made first to find a constart mapping to this string.  If not found, it will send the keys supplied as individual characters\",\"inputData\":\"\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":35,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":1,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Send the keys {var:Key} to your application\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "value", dS.replaceValues( "${11652.1603-11652.1604-Location}", contextMap ) + "", dS.replaceValues( "${11652.1603-11652.1604-Location}", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "clearFirst", dS.replaceValues( "true", contextMap ), dS.replaceValues( "true", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "11652.1897", contextMap ), contextMap, dS ), dS.replaceValues( "11652.1897", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "type_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":85,\"checkpointId\":1,\"alchemyId\":1928,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"${11652.1603-11652.1604-Location}\",\"inputId\":31,\"templateId\":0,\"inputName\":\"value\",\"inputDescription\":\"The text to type\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"true\",\"inputId\":32,\"templateId\":0,\"inputName\":\"clearFirst\",\"inputDescription\":\"A flag indicating if the value should first be cleared\",\"inputData\":\"null\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"11652.1897\",\"inputId\":33,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The element that will receive the text\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":17,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Type {var:value} into {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "11652.1930", contextMap ), contextMap, dS ), dS.replaceValues( "11652.1930", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "method", dS.replaceValues( "Default", contextMap ), dS.replaceValues( "Default", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "click_v7", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":189,\"checkpointId\":1,\"alchemyId\":1969,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"11652.1930\",\"inputId\":1,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The name of the locator retrieved from the elements\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"Default\",\"inputId\":59,\"templateId\":0,\"inputName\":\"method\",\"inputDescription\":\"The method used to click.  If omitted, default is used\",\"inputData\":\"Default,No Wait,Simple,Double,Right\",\"inputTypeId\":10,\"inputRequired\":0,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":1,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":7,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Click on {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "value", dS.replaceValues( "${11652.1603-11652.1604-Religion}", contextMap ) + "", dS.replaceValues( "${11652.1603-11652.1604-Religion}", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "clearFirst", dS.replaceValues( "true", contextMap ), dS.replaceValues( "true", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "11652.1930", contextMap ), contextMap, dS ), dS.replaceValues( "11652.1930", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "type_v2", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":85,\"checkpointId\":1,\"alchemyId\":1970,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"${11652.1603-11652.1604-Religion}\",\"inputId\":31,\"templateId\":0,\"inputName\":\"value\",\"inputDescription\":\"The text to type\",\"inputData\":\"null\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"true\",\"inputId\":32,\"templateId\":0,\"inputName\":\"clearFirst\",\"inputDescription\":\"A flag indicating if the value should first be cleared\",\"inputData\":\"null\",\"inputTypeId\":4,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"11652.1930\",\"inputId\":33,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"The element that will receive the text\",\"inputTypeId\":5,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":17,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":2,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"comment\":\"\",\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"Type {var:value} into {var:targetLocator}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      
      vM.clear();

      vM.addVariable( new VariableWrapper( "loopMode", dS.replaceValues( "Data", contextMap ), dS.replaceValues( "Data", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "count", parseNumber( dS.replaceValues( "3", contextMap ), Long.class ), dS.replaceValues( "3", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "dataTable", dS.getTable( dS.replaceValues( "11652.1603-11652.1604", contextMap ) + "" ), dS.replaceValues( "11652.1603-11652.1604", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "targetLocator", ObjectManager.instance().getObject( dS.replaceValues( "", contextMap ), contextMap, dS ), dS.replaceValues( "", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "functionName", dS.replaceValues( "11652.1972", contextMap ) + "", dS.replaceValues( "11652.1972", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "range", dS.replaceValues( "0-2", contextMap ) + "", dS.replaceValues( "0-2", contextMap ) + "" ) );
      vM.addVariable( new VariableWrapper( "list", dS.replaceValues( "", contextMap ) + "", dS.replaceValues( "", contextMap ) + "" ) );
      
      eventList.add( aM.executeAction( "repeat_v3", null, 1, vM.generateVariables(), vM.generateVariableDefinitions(), "{\"templateImplId\":163,\"checkpointId\":1,\"alchemyId\":1971,\"alchemySeed\":11652,\"pauseBefore\":0,\"waitFor\":5000,\"pauseAfter\":0,\"variableList\":[{\"value\":\"Data\",\"inputId\":38,\"templateId\":0,\"inputName\":\"loopMode\",\"inputDescription\":\"Controls what you are looping over\",\"inputData\":\"Fixed Count,Data,Page Elements,List\",\"inputTypeId\":10,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"3\",\"inputId\":39,\"templateId\":0,\"inputName\":\"count\",\"inputDescription\":\"The amount of repetitions - Required for Fixed Count\",\"inputData\":\"null\",\"inputTypeId\":2,\"inputRequired\":0,\"status\":1,\"changed\":false},{\"value\":\"11652.1603-11652.1604\",\"inputId\":40,\"templateId\":0,\"inputName\":\"dataTable\",\"inputDescription\":\"The data table that you want to select your row from\",\"inputData\":\"null\",\"inputTypeId\":9,\"inputRequired\":0,\"status\":1,\"changed\":false},{\"value\":\"\",\"inputId\":41,\"templateId\":0,\"inputName\":\"targetLocator\",\"inputDescription\":\"A locator referencing a list of elements - Required for Page Elements\",\"inputData\":\"null\",\"inputTypeId\":5,\"inputRequired\":0,\"status\":1,\"changed\":false},{\"value\":\"11652.1972\",\"inputId\":42,\"templateId\":0,\"inputName\":\"functionName\",\"inputDescription\":\"The name of the function to call\",\"inputData\":\"null\",\"inputTypeId\":6,\"inputRequired\":1,\"status\":1,\"changed\":false},{\"value\":\"0-2\",\"inputId\":0,\"templateId\":0,\"inputName\":\"range\",\"inputDescription\":\"An optional, zero-based, range to use when iterating over the data.  A range is specifed using a begin (inclusive) and end (exclusive) value seperated by a dash such as 2-4.  When specifying the end value, you may use * to go to the end of the list.  2-* will start at the 3rd record and go to the end of the list\",\"inputData\":\"\",\"inputTypeId\":1,\"inputRequired\":0,\"status\":1,\"changed\":false},{\"value\":\"\",\"inputId\":33,\"templateId\":0,\"inputName\":\"list\",\"inputDescription\":\"A comma seperated list of data to iterate over\",\"inputTypeId\":1,\"inputRequired\":1,\"status\":1,\"changed\":false}],\"status\":1,\"actionId\":21,\"parentId\":0,\"endpointId\":1,\"templateId\":0,\"tversion\":3,\"tstyleId\":0,\"tstatus\":0,\"verifiedBy\":0,\"onFailure\":1,\"breakpoint\":false,\"invertResult\":false,\"actionDisplay\":\"For {ifvar:loopMode\\u003dList;text:each item in}{ifvar:loopMode\\u003dList;var:list}{ifvar:loopMode\\u003dData;text:each record in}{ifvar:loopMode\\u003dData;var:dataTable}{ifvar:loopMode\\u003dPage Elements;text:each element found with} {ifvar:loopMode\\u003dPage Elements;var:targetLocator}{ifvar:loopMode\\u003dFixed Count;var:count}{ifvar:loopMode\\u003dFixed Count;text:Iterations }execute{var:functionName}\",\"changed\":false}", stepStack.peek(), (t0) -> {  return null; }, false, 0, 5000, 0 ) );
      

      
      
      FunctionExecutionMediator.instance().getFunctionExecutor().executeFunction( "11652.2118", executionId, testExecutionId, webDriver, new HashMap<>(5), contextMap, contextName, callStack, stepStack );
      
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