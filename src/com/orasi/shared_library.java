/**
 * @author m.prasad@orasi.com
 * @author Orasi Software, Inc.
 * @version 12
 *
 */
 package com.orasi;
 
 import java.util.*;
 import java.util.function.*;
 import java.io.*;
 import java.lang.reflect.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 import org.openqa.selenium.*;
 import org.openqa.selenium.interactions.*;
 import org.openqa.selenium.remote.*;
 import org.openqa.selenium.support.ui.*;
 import java.util.regex.*;
 import com.orasi.datasource.*;
 import com.orasi.alchemy.mediation.execution.*;
 import java.util.concurrent.atomic.AtomicInteger;
 import com.orasi.event.*;
 import com.orasi.event.chain.*;
 import com.orasi.event.handler.EventHandler;
 import com.orasi.model.*;
  
 
 public class shared_library
 {
    private static Logger log = LoggerFactory.getLogger(shared_library.class );
 
    private static final Pattern CONTEXT_REGEX = Pattern.compile( "\\$\\{([^}]*)\\}" );
 

    private static final AtomicInteger testCounter = new AtomicInteger();
    private static final AtomicInteger stepCounter = new AtomicInteger();

    public static final int getTestCounter() {
      return testCounter.incrementAndGet();
    }

    public static final int getStepCounter() {
      return stepCounter.incrementAndGet();
    }
 

 private static EventChain eventChain = new EventChain();

 public static void addEventHandler( EventHandler eventHandler ) {
   eventChain.addEventHandler(eventHandler);
 }

 public static void notifyListeners( Event e ) {
   
   eventChain.handle( e );
 }

  private static List<Throwable> getThrowableList(Throwable throwable) {
    final List<Throwable> list = new ArrayList<>();
    while (throwable != null && !list.contains(throwable)) {
      list.add(throwable);
      throwable = throwable.getCause();
    }
    return list;
  }

  private static Throwable getRootCause(final Throwable throwable) {
    final List<Throwable> list = getThrowableList(throwable);
    return list.isEmpty() ? throwable : list.get(list.size() - 1);
  }

 
 
 /**
 * {{description}}
 */
 public static void keys_v1( int executionId, int stepIdentifier, int testExecutionId, WebDriver webDriver, Map<String,Object> variableMap, Map<String,Object> contextMap, String contextName, Stack<String> callStack, Stack<Integer> stepStack )
 {
 
   Boolean Control = Boolean.parseBoolean( variableMap.get( "Control" ) + "" );
 
   Boolean Shift = Boolean.parseBoolean( variableMap.get( "Shift" ) + "" );
 
   Boolean Alt = Boolean.parseBoolean( variableMap.get( "Alt" ) + "" );
 
   String Key = (String) variableMap.get( "Key" );
 

 Actions keyActions = new Actions( webDriver );

if ( Control != null && Control.booleanValue() ) {
  keyActions.keyDown( Keys.CONTROL );
}

if ( Shift != null && Shift.booleanValue() ) {
  keyActions.keyDown( Keys.SHIFT );
}

if ( Alt != null && Control.booleanValue() ) {
  keyActions.keyDown( Keys.ALT );
}

try {
  Keys useKey = Keys.valueOf( Key );
  keyActions.sendKeys( useKey );
} catch( Exception e ) {
  keyActions.sendKeys( Key );
}

keyActions.build().perform();


boolean runAgain = false;
if ( Control != null && Control.booleanValue() ) {
  keyActions.keyUp( Keys.CONTROL );
  runAgain = true;
}

if ( Shift != null && Shift.booleanValue() ) {
  keyActions.keyUp( Keys.SHIFT );
  runAgain = true;
}

if ( Alt != null && Control.booleanValue() ) {
  keyActions.keyUp( Keys.ALT );
  runAgain = true;
}

if ( runAgain ) {
  keyActions.build().perform();
}
 if ( variableMap != null ) {
   variableMap.clear();
 }
}

 
 public static void repeat_v3( int executionId, int stepIdentifier, int testExecutionId, WebDriver webDriver, Map<String,Object> variableMap, Map<String,Object> contextMap, String contextName, Stack<String> callStack, Stack<Integer> stepStack )
 {
 
   String loopMode = (String) variableMap.get( "loopMode" );
 
   Long count = null;
   Number _count = (Number) variableMap.get( "count" );
   if ( _count != null ) count = _count.longValue();
 
   DataTable dataTable = (DataTable) variableMap.get( "dataTable" );
 
   By targetLocator = (By) variableMap.get( "targetLocator" );
 
   String functionName = (String) variableMap.get( "functionName" );
 
   String range = (String) variableMap.get( "range" );
 
   String list = (String) variableMap.get( "list" );
 

     int startValue = 0;
     int endValue = -1;
     if ( range != null && !range.trim().isEmpty() ) {
       String[] rangeValues = range.split( "-" );
       if ( rangeValues.length != 2 ) {
         throw new IllegalArgumentException( "Expected format is #-# - value found was [" + range + "]" );
       }
 
       startValue = Integer.parseInt( rangeValues[ 0 ].trim() );
       if ( !"*".equals( rangeValues[ 1 ].trim() ) ) {
         endValue = Integer.parseInt( rangeValues[ 1 ].trim() );
       }
 
     }
 
     switch ( loopMode ) {
       case "List":
         if ( list == null ) {
           throw new IllegalArgumentException( "variable list is required when the List loopMode is specified" );
         }
 
         String[] valueList = list.split( "," );
 
         for ( int fixedCount = startValue; fixedCount < (endValue == -1 ? valueList.length : endValue); fixedCount++ ) {
 
           if ( contextName != null ) {
             contextMap.put( contextName + "_index", fixedCount );
             contextMap.put( contextName + "_item", valueList[ fixedCount ] );
           } else {
             contextMap.put( "index", fixedCount );
             contextMap.put( "item", valueList[ fixedCount ] );
           }
 
           try {
             FunctionExecutionMediator.instance().getFunctionExecutor().executeFunction( functionName, executionId, testExecutionId, webDriver, variableMap, contextMap, contextName, callStack, stepStack ); 
 
 	    if ( contextName != null ) {
               contextMap.remove( contextName + "_index" );
             } else {
               contextMap.remove( "index" );
             }
 
           } catch ( Throwable e ) {
             if (getRootCause(e) instanceof BreakException) {
               String breakReason = ((BreakException) getRootCause(e)).getBreakCode();
               if ("Continue".equals(breakReason)) {
                 continue;
               } else {
                 throw (BreakException) getRootCause(e);
               }
             } else if (getRootCause(e) instanceof StepException) {
               throw (StepException) getRootCause(e);
             } else {
               throw new IllegalArgumentException("COULD NOT EXECUTE" + functionName, e);
             }
           }
 
         }
         break;
       case "Fixed Count":
         if ( count == null ) {
           throw new IllegalArgumentException( "variable count is required when the Fixed Count loopMode is specified" );
         }
 
         for ( long fixedCount = startValue; fixedCount < (endValue == -1 ? count : endValue); fixedCount++ ) {
 
           if ( contextName != null ) {
             contextMap.put( contextName + "_index", fixedCount );
           } else {
             contextMap.put( "index", fixedCount );
           }
 
           try {
             FunctionExecutionMediator.instance().getFunctionExecutor().executeFunction( functionName, executionId, testExecutionId, webDriver, variableMap, contextMap, contextName, callStack, stepStack ); 
 
 	    if ( contextName != null ) {
               contextMap.remove( contextName + "_index" );
             } else {
               contextMap.remove( "index" );
             }
 
           } catch ( Throwable e ) {
             if (getRootCause(e) instanceof BreakException) {
               String breakReason = ((BreakException) getRootCause(e)).getBreakCode();
               if ("Continue".equals(breakReason)) {
                 continue;
               } else {
                 throw (BreakException) getRootCause(e);
               }
             } else if (getRootCause(e) instanceof StepException) {
               throw (StepException) getRootCause(e);
             } else {
               throw new IllegalArgumentException("COULD NOT EXECUTE" + functionName, e);
             }
           }
 
         }
         break;
 
       case "Data":
         if (dataTable == null) {
           throw new IllegalArgumentException("variable dataTable is required when the Data Source loopMode is specified");
         }
 
         if (dataTable != null) {
           for (int i = startValue; i < (endValue == -1 ? dataTable.getRows().size() : endValue); i++) {
 
             DataSourceProviderFactory.instance().getDataSourceProvider().setRow( dataTable.getKey(), (DataRow) dataTable.getRows().get( i ) );
 
             if (contextName != null) {
               contextMap.put(contextName + "_index", i);
             } else {
               contextMap.put("index", i);
             }
 
             try {
               FunctionExecutionMediator.instance().getFunctionExecutor().executeFunction(functionName, executionId, testExecutionId, webDriver, variableMap, contextMap, contextName, callStack, stepStack);
 
               if (contextName != null) {
                 contextMap.remove(contextName + "_index");
               } else {
                 contextMap.remove("index");
               }
             } catch (Throwable e) {
               if (getRootCause(e) instanceof BreakException) {
               String breakReason = ((BreakException) getRootCause(e)).getBreakCode();
               if ("Continue".equals(breakReason)) {
                 continue;
               } else {
                 throw (BreakException) getRootCause(e);
               }
             } else if (getRootCause(e) instanceof StepException) {
               throw (StepException) getRootCause(e);
             } else {
               throw new IllegalArgumentException("COULD NOT EXECUTE" + functionName, e);
             }
             } finally {
               DataSourceProviderFactory.instance().getDataSourceProvider().setRow( dataTable.getKey(), null );
             }
           }
         }
         break;
 
       case "Page Elements":
         if ( targetLocator == null ) {
           throw new IllegalArgumentException( "variable targetLocator is required when the Page Elements loopMode is specified" );
         }
         List<WebElement> elementList = webDriver.findElements( targetLocator );
         
         if ( contextName != null ) {
           contextMap.put( contextName + "_elementList", elementList );
         } else {
           contextMap.put( "_elementList", elementList );
         }
         for ( int i=startValue; i< (endValue == -1 ? elementList.size() : endValue); i++ ) {
           WebElement wE = elementList.get( i );
           if ( contextName != null ) {
             contextMap.put( contextName + "_element", new By() { 
               @Override
               public WebElement findElement(SearchContext context) {
                 return wE;
               }
 
               @Override
               public List<WebElement> findElements(SearchContext sc) {
                 List<WebElement> wL = new ArrayList<>( 1 );
                 wL.add( wE );
                 return wL;
               }
             } );
             contextMap.put( contextName + "_index", i );
           } else {
             contextMap.put( "element", new By() { 
               @Override
               public WebElement findElement(SearchContext context) {
                 return wE;
               }
 
               @Override
               public List<WebElement> findElements(SearchContext sc) {
                 List<WebElement> wL = new ArrayList<>( 1 );
                 wL.add( wE );
                 return wL;
               }
             }  );
             contextMap.put( "index", i );
           }
           
           try {
             FunctionExecutionMediator.instance().getFunctionExecutor().executeFunction( functionName, executionId, testExecutionId, webDriver, variableMap, contextMap, contextName, callStack, stepStack ); 
 
             if ( contextName != null ) {
               contextMap.remove( contextName + "_element" );
               contextMap.remove( contextName + "_index" );
             } else {
               contextMap.remove( "element" );
               contextMap.remove( "index" );
             }
           } catch ( Throwable e ) {
             if (getRootCause(e) instanceof BreakException) {
               String breakReason = ((BreakException) getRootCause(e)).getBreakCode();
               if ("Continue".equals(breakReason)) {
                 continue;
               } else {
                 throw (BreakException) getRootCause(e);
               }
             } else if (getRootCause(e) instanceof StepException) {
               throw (StepException) getRootCause(e);
             } else {
               throw new IllegalArgumentException("COULD NOT EXECUTE" + functionName, e);
             }
           }
           
         }
         break;
       default:
         throw new IllegalArgumentException( "Unknown Loop Mode provided: " + loopMode );
     }
 if ( variableMap != null ) {
   variableMap.clear();
 }
}

 
 public static void navigate_v2( int executionId, int stepIdentifier, int testExecutionId, WebDriver webDriver, Map<String,Object> variableMap, Map<String,Object> contextMap, String contextName, Stack<String> callStack, Stack<Integer> stepStack )
 {
 
   String url = (String) variableMap.get( "url" );
 
   Long timeout = null;
   Number _timeout = (Number) variableMap.get( "timeout" );
   if ( _timeout != null ) timeout = _timeout.longValue();
 

 webDriver.manage().timeouts().pageLoadTimeout( timeout.longValue(), java.util.concurrent.TimeUnit.MILLISECONDS ); webDriver.get( url ); 
 if ( variableMap != null ) {
   variableMap.clear();
 }
}

 
 public static void click_v7( int executionId, int stepIdentifier, int testExecutionId, WebDriver webDriver, Map<String,Object> variableMap, Map<String,Object> contextMap, String contextName, Stack<String> callStack, Stack<Integer> stepStack )
 {
 
   By targetLocator = (By) variableMap.get( "targetLocator" );
 
   String method = (String) variableMap.get( "method" );
 

   if ( method != null && !method.isEmpty() ) {    

try {
  switch (method) {
        case "Default":
          try {
            WebDriverWait wait = new WebDriverWait( webDriver, 8, 250 );
            wait.until( (_wD) -> ExpectedConditions.elementToBeClickable( targetLocator ).apply( _wD )).click();
          } catch (Exception e) {
            try {
              
              if (webDriver instanceof JavascriptExecutor) {
                WebElement wE = webDriver.findElement(targetLocator);
                ((JavascriptExecutor) webDriver).executeScript("arguments[ 0 ].click();", wE);
              }
            } catch (Exception e2) {
              throw e;
            }
          }
          break;
        case "No Wait":
          try {
            webDriver.findElement(targetLocator).click();
          } catch (Exception e) {
            try {
              if (webDriver instanceof JavascriptExecutor) {
                WebElement wE = webDriver.findElement(targetLocator);
                ((JavascriptExecutor) webDriver).executeScript("arguments[ 0 ].click();", wE);
              }
            } catch (Exception e2) {
              throw e;
            }
          }
          break;
        case "Simple":
          webDriver.findElement(targetLocator).click();
          break;
        case "Right":
          Actions rC = new Actions(webDriver);
          rC.contextClick(webDriver.findElement(targetLocator)).perform();
          break;
        case "Double": 
          Actions dC = new Actions(webDriver);
          dC.doubleClick(webDriver.findElement(targetLocator)).perform();
          break;
        default:
          throw new IllegalArgumentException("Invalid method specified as " + method );
      }
    } catch( Exception e ) {
      throw new IllegalArgumentException( "Could not locate element defined by targetLocator" );
    }

  } else {
    throw new IllegalArgumentException("The METHOD parameter must be specified");
  }
 if ( variableMap != null ) {
   variableMap.clear();
 }
}

 /**
 * {{description}}
 */
 public static void type_v2( int executionId, int stepIdentifier, int testExecutionId, WebDriver webDriver, Map<String,Object> variableMap, Map<String,Object> contextMap, String contextName, Stack<String> callStack, Stack<Integer> stepStack )
 {
 
   String value = (String) variableMap.get( "value" );
 
   Boolean clearFirst = Boolean.parseBoolean( variableMap.get( "clearFirst" ) + "" );
 
   By targetLocator = (By) variableMap.get( "targetLocator" );
 

 WebElement targetElement;
try {
      targetElement = webDriver.findElement(targetLocator);

    } catch (Exception e) {
      throw new IllegalArgumentException( "Unable to locate element", e );
    }

if ( clearFirst ) {   
  targetElement.clear(); 
  String text = targetElement.getAttribute( "value" );
  if ( text != null ) {
    for ( int i=0; i<text.length(); i++ ) {
      targetElement.sendKeys(Keys.BACK_SPACE);
    } 
  }
}  

targetElement.sendKeys( value );
 if ( variableMap != null ) {
   variableMap.clear();
 }
}


 }
