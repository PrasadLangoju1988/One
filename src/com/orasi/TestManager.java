package com.orasi;

import com.orasi.alchemy.mediation.execution.FunctionExecutionMediator;
import com.orasi.alchemy.mediation.execution.FunctionExecutor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;

public class TestManager implements FunctionExecutor {

  private static final Logger log = LoggerFactory.getLogger(TestManager.class);
  private static final TestManager singleton = new TestManager();

  private TestManager() {
    FunctionExecutionMediator.instance().setFunctionExecutor( this );
  }

  public static TestManager instance() {
    return singleton;
  }

  private final Map<Integer, List<Integer>> synchronizationMap = new HashMap<>(10);

  private final Map<EndpointDevice, List<TestWrapper>> endpointMap = new HashMap<>(10);

  private final List<TestWrapper> testList = new ArrayList<>(10);

  private final Map<String, TestWrapper> testMap = new HashMap<>(10);

  public int getSize() {
    return testList.size();
  }

  private boolean hasTag(String[] globalTagList, String[] testTagList) {
    //
    // Do we have any tags defined?
    //
    if (globalTagList == null || globalTagList.length == 0) {
      return true;
    }

    //
    // We do, now does our test have any?
    //
    if (testTagList == null || testTagList.length == 0) {
      return false;
    }

    for (String g : globalTagList) {
      for (String t : testTagList) {
        if (g.equalsIgnoreCase(t)) {
          return true;
        }
      }
    }

    return false;

  }

  public void setTags(String[] tagList) {

    log.info("Tags were supplied - reconfiguring final test list");
    List<TestWrapper> finalList = new ArrayList<>(10);

    testList.stream().filter(tW -> (hasTag(tagList, tW.getTags()))).forEachOrdered(tW -> {
      log.info("Adding Tagged Test " + tW.getName());
      finalList.add(tW);
    });

    testList.clear();
    testList.addAll(finalList);
  }

  public void registerSynchronizationPoint(int pointId, List<Integer> testIds) {
    synchronizationMap.put(pointId, testIds);
  }

  public void registerFunction(TestWrapper tW) {
    //
    // Functions dont get added to the test list as they are executed on demand
    //
    testMap.put(tW.getAlchemyIdentifier(), tW);
  }

  @Override
  public void executeFunction(String alchemyIdentifier, int executionId, int testExecutionId, Object webDriver, Map<String, Object> variableMap, Map<String, Object> contextMap, String contextName, Stack<String> callStack, Stack<Integer> stepStack) {
    TestWrapper tW = testMap.get(alchemyIdentifier);
    if (tW == null) {
      throw new IllegalArgumentException("Could not locate test for " + alchemyIdentifier);
    }

    tW.executeTest(executionId, testExecutionId, (WebDriver) webDriver, contextMap, contextName, callStack, stepStack);
  }

  public void registerTest(TestWrapper tW) {

    testMap.put(tW.getAlchemyIdentifier(), tW);

    if (tW.getDataName() != null && !tW.getDataName().trim().isEmpty() ) {
      List<Map<String, Object>> dataList = DataManager.instance().getSource(tW.getDataName(), null);

      if (dataList != null) {
        for (int i = 0; i < dataList.size(); i++) {
          DataTestWrapper dW = new DataTestWrapper(tW, i, dataList.get(i));
          log.info("Registering DATA wrapped test for " + tW.getName());
          testList.add(dW);
        }
      } else {
        log.info("Registering test as " + tW.getName());
        testList.add(tW);
      }
    } else {
      log.info("Registering test as " + tW.getName());
      testList.add(tW);
    }
  }

  public TestWrapper getTest(String alchemyIdentifier) {
    return testMap.get(alchemyIdentifier);
  }

  public synchronized TestWrapper getTest(EndpointDevice forEndpoint) {

    List<TestWrapper> completedTests = endpointMap.get(forEndpoint);

    if (completedTests == null) {
      completedTests = new ArrayList<>(10);
      endpointMap.put(forEndpoint, completedTests);
    }

    if (completedTests.size() == testList.size()) {
      log.warn("No more tests for endpoint [" + forEndpoint.getName() + "]");
      EndpointDeviceManager.instance().releaseEndpoint(forEndpoint, true);
      return null;
    }

    for (TestWrapper tW : testList) {
      if (!completedTests.contains(tW)) {
        if (log.isDebugEnabled()) {
          log.debug(tW.getName() + " still needs to run on " + forEndpoint.getName());
        }

        if (tW.getSynchronizationPoint() > 0) {
          List<Integer> testIds = synchronizationMap.get(tW.getSynchronizationPoint());
          boolean canRun = true;
          if (testIds != null) {
            for (Integer testId : testIds) {
              boolean testFound = false;
              for (TestWrapper _tW : completedTests) {
                if (_tW.getId() == testId) {
                  testFound = true;
                  break;
                }
              }

              if (!testFound) {
                if (log.isDebugEnabled()) {
                  log.debug("Test [" + testId + "] has not completed so " + tW.getName() + " cant run yet");
                }
                canRun = false;
                break;
              }
            }
          }

          if (canRun) {
            log.info("Executing " + tW.getName() + " on " + forEndpoint.getName());
            completedTests.add(tW);
            return tW;
          }
        } else {
          log.info("Executing " + tW.getName() + " on " + forEndpoint.getName());
          completedTests.add(tW);
          return tW;
        }
      }
    }

    return null;
  }

}
