package com.orasi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orasi.alchemy.mediation.execution.FunctionExecutionMediator;
import com.orasi.model.StepException;
import com.orasi.event.spi.StepEvent;
import com.orasi.model.*;
import com.orasi.model.StepException.FailureType;
import static com.orasi.shared_library.getStepCounter;
import static com.orasi.shared_library.notifyListeners;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionMediator {

  private static final Logger log = LoggerFactory.getLogger(ActionMediator.class);
  private final GsonBuilder gsonBuilder = new GsonBuilder();
  private final Gson gson = gsonBuilder.create();
  private final int executionId;
  private final int testExecutionId;
  private final WebDriver webDriver;
  private final Map<String, Object> contextMap;
  private final Stack<String> callStack;
  private final Stack<Integer> stepStack;
  private final String testName;
  private final int callStyle;
  private String functionName;

  public void setErrorHandler(String functionName) {
    this.functionName = functionName;
  }

  public ActionMediator(int executionId, int testExecutionId, String testName, WebDriver webDriver, Map<String, Object> contextMap, Stack<String> callStack, Stack<Integer> stepStack, int callStyle) {
    this.executionId = executionId;
    this.testExecutionId = testExecutionId;
    this.webDriver = webDriver;
    this.contextMap = contextMap;
    this.callStack = callStack;
    this.stepStack = stepStack;
    this.testName = testName;
    this.callStyle = callStyle;
  }

  public StepEvent executeAction(String actionName, String contextName, int checkpointId, Map<String, Object> variableMap, Map<String, String> variableTextMap, String stepDetail, int parentStep, Function<StepEvent, Integer> onSuccess, boolean inverse, int pauseBefore, int waitFor, int pauseAfter) {

    long startTime = System.currentTimeMillis();

    StepEvent sE = null;
    StepPayload stepPayload = new StepPayload();
    int stepIdentifier = getStepCounter();
    try {

      stepPayload.setActionName(actionName);
      stepPayload.setExecutionId(executionId);
      stepPayload.setStepId(stepIdentifier);
      stepPayload.setTestExecutionId(testExecutionId);
      stepPayload.setParentStep(parentStep);
      stepPayload.setStepDetail(stepDetail);
      stepStack.push(stepIdentifier);
      stepPayload.setVariableList(gson.toJson(variableTextMap));
      variableTextMap.clear();

      notifyListeners(new StepEvent(stepPayload, getTestName(), 1));

      if (pauseBefore > 0) {
        try {
          Thread.sleep(pauseBefore);
        } catch (Exception e) {
        }
      }
      sE = _executeAction(stepIdentifier, actionName, contextName, checkpointId, variableMap, variableTextMap, stepDetail, parentStep, inverse, pauseAfter, waitFor);

      if (onSuccess != null) {
        onSuccess.apply(sE);
      }
      sE.getPayload().setParentStep(parentStep);

      return sE;
    } catch (BreakException | EOTException t) {
      throw t;
    } catch (StepException t) {
      //
      // If we did not have a FATAL error and an errorHandler was registered, run it and try to execute the step again
      //
      if (t.getThreshold() != StepException.Threshold.FATAL) {
        if (functionName != null) {
          contextMap.put("__callStyle", 2);
          FunctionExecutionMediator.instance().getFunctionExecutor().executeFunction(functionName, executionId, testExecutionId, webDriver, variableMap, contextMap, contextName, callStack, stepStack);
          sE = _executeAction(stepIdentifier, actionName, contextName, checkpointId, variableMap, variableTextMap, stepDetail, parentStep, inverse, pauseAfter, waitFor);
          if (onSuccess != null) {
            onSuccess.apply(sE);
          }
          sE.getPayload().setParentStep(parentStep);
        }
      }

      throw t;
    } finally {
      if (sE != null) {
        sE.getPayload().setParentStep(parentStep);
      }
    }
  }

  private StepEvent _executeAction(int stepIdentifier, String actionName, String contextName, int checkpointId, Map<String, Object> variableMap, Map<String, String> variableTextMap, String stepDetail, int parentId, boolean inverted, int pauseAfter, int waitFor) throws StepException, EOTException {
    int stepStatus = 1;
    StepPayload stepPayload = new StepPayload();
    try {

      stepPayload.setExecutionId(executionId);
      stepPayload.setTestExecutionId(testExecutionId);
      stepPayload.setParentStep(parentId);
      stepPayload.setStepId(stepIdentifier);

      Method actionMethod = shared_library.class.getMethod(actionName, new Class[]{int.class, int.class, int.class, WebDriver.class, Map.class, Map.class, String.class, Stack.class, Stack.class});

      long startTime = 0;
      boolean keepRunning = false;
      int iterationCount = 1;

      if (waitFor > 0) {
        keepRunning = true;
      }
      startTime = System.currentTimeMillis();

      try {

        do {
          try {
            actionMethod.invoke(shared_library.class, new Object[]{executionId, stepIdentifier, testExecutionId, webDriver, variableMap, contextMap, contextName, callStack, stepStack});
            if (inverted) {
              inverted = false;
              throw new IllegalStateException("This step was meant to fail, but it succeeded");
            }
            keepRunning = false;
          } catch (Throwable e) {
            if (e.getCause()instanceof BreakException || e.getCause() instanceof EOTException) {
              throw e.getCause();
            }
            
            if (inverted) {
              keepRunning = false;
            } else {
              if ((System.currentTimeMillis() - startTime) > waitFor) {
                throw new IllegalStateException(actionName + " failed: " + e.getMessage(), e);
              }
              iterationCount++;
              Thread.sleep(500);
            }
          }
        } while (keepRunning);

      } catch (EOTException e) {
        e.setStepPayload(stepPayload);
        stepPayload.setMessage(e.getMessage());
        throw e;
      } catch (BreakException e) {
        e.setStepPayload(stepPayload);
        stepPayload.setMessage(e.getMessage());
        throw e;
      } catch (Throwable t) {
        throw t;
      }

      if (getCallStyle() == 2) {
        stepStatus = 5;
      }

      if (getCallStyle() == 1) {
        switch (checkpointId) {
          case 2:
            stepPayload.setScreenShot(screenShot(webDriver));
            break;
          case 3:
            stepPayload.setSource(webDriver.getPageSource());
            break;
          case 4:
            stepPayload.setScreenShot(screenShot(webDriver));
            stepPayload.setSource(webDriver.getPageSource());
            break;
        }
      }

      //
      // Pause After if we were successful
      //
      if (pauseAfter > 0) {
        try {
          Thread.sleep(pauseAfter);
        } catch (Exception e) {
        }
      }

    } catch (Throwable e) {
      handleException(e, stepPayload);
    } finally {
      stepIdentifier = stepStack.pop();
      if (log.isDebugEnabled()) {
        log.debug("Removed " + stepIdentifier + " from step stack");
      }

      if (getCallStyle() == 1 || getCallStyle() == 2) {
        //
        // Private Functions are ignored
        //
        notifyListeners(new StepEvent(stepPayload, testName, stepStatus == 1 ? 4 : 3));
      }
    }

    return new StepEvent(stepPayload, testName, stepStatus == 1 ? 4 : 3);
  }

  private void handleException(Throwable t, StepPayload stepPayload) throws StepException, EOTException {

    if (t instanceof EOTException) {
      throw (EOTException) t;
    }

    if (t instanceof BreakException) {
      throw (BreakException) t;
    }

    if (t instanceof NoSuchMethodException || t instanceof SecurityException) {
      throw new StepException(stepPayload.getStepId(), StepException.Threshold.FATAL, t, "The action " + stepPayload.getActionName() + " could not be located.  Either you dont have access to that implementation or you specified an invalid action.  This is NOT a test failure but an error with the generated code", stepPayload, FailureType.Infrastructure);
    }

    if (t instanceof IllegalAccessException || t instanceof InvocationTargetException) {
      if (t.getCause() != null) {
        if (t.getCause() instanceof StepException) {
          throw (StepException) t.getCause();
        } else if (t.getCause() instanceof EOTException) {
          stepPayload.setMessage(t.getCause().getMessage());
          throw (EOTException) t.getCause();
        } else {
          throw new StepException(stepPayload.getStepId(), StepException.Threshold.MAJOR, t, t.getMessage(), stepPayload, FailureType.Application);
        }
      } else {
        throw new StepException(stepPayload.getStepId(), StepException.Threshold.FATAL, t, "The action " + stepPayload.getActionName() + " could not be located.  Most likely, this implementation was not generated properly.  This is NOT a test failure but an error with the generated code", stepPayload, FailureType.Infrastructure);
      }
    }

    if (t instanceof StepException) {
      if (((StepException) t).getThreshold().getId() >= 1) {
        throw (StepException) t;
      } else {
        log.error(t.getMessage());
      }
    } else {
      throw new StepException(stepPayload.getStepId(), StepException.Threshold.MAJOR, t, null, stepPayload);
    }
  }

  /**
   * @return the executionId
   */
  public int getExecutionId() {
    return executionId;
  }

  /**
   * @return the testExecutionId
   */
  public int getTestExecutionId() {
    return testExecutionId;
  }

  /**
   * @return the webDriver
   */
  public WebDriver getWebDriver() {
    return webDriver;
  }

  /**
   * @return the contextMap
   */
  public Map<String, Object> getContextMap() {
    return contextMap;
  }

  /**
   * @return the callStack
   */
  public Stack<String> getCallStack() {
    return callStack;
  }

  /**
   * @return the stepStack
   */
  public Stack<Integer> getStepStack() {
    return stepStack;
  }

  /**
   * @return the testName
   */
  public String getTestName() {
    return testName;
  }

  /**
   * @return the callStyle
   */
  public int getCallStyle() {
    return callStyle;
  }

  private byte[] screenShot(WebDriver webDriver) {
    if (webDriver instanceof TakesScreenshot) {
      return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    } else {
      return null;
    }
  }

}
