package com.orasi;

import java.util.Map;
import java.util.Stack;
import org.openqa.selenium.WebDriver;

public class DataTestWrapper implements TestWrapper {

  private final TestWrapper baseTest;
  private final int dataIndex;
  private final Map<String, Object> data;

  @Override
  public String getAlchemyIdentifier() {
    return baseTest.getAlchemyIdentifier();
  }

  public DataTestWrapper(TestWrapper baseTest, int dataIndex, Map<String, Object> data) {
    this.baseTest = baseTest;
    this.dataIndex = dataIndex;
    this.data = data;
  }

  @Override
  public String getTestDetail() {
    return baseTest.getTestDetail();
  }

  @Override
  public String[] getTags() {
    return baseTest.getTags();
  }

  @Override
  public void executeTest(int executionId, int testExecutionId, WebDriver webDriver) {
    baseTest.executeTest(executionId, testExecutionId, webDriver);
  }

  @Override
  public void executeTest(int executionId, int testExecutionId, WebDriver webDriver, Map<String, Object> contextMap, String contextName, Stack<String> callStack, Stack<Integer> stepStack) {
    baseTest.executeTest(executionId, testExecutionId, webDriver, contextMap, contextName, callStack, stepStack);
  }

  @Override
  public int getId() {
    return baseTest.getId();
  }

  @Override
  public String getDescription() {
    return baseTest.getDescription();
  }

  @Override
  public String getDataName() {
    return baseTest.getDataName();
  }

  @Override
  public int getSynchronizationPoint() {
    return baseTest.getSynchronizationPoint();
  }

  @Override
  public String getName() {
    return baseTest.getName() + "_" + dataIndex;
  }

  @Override
  public Map<String, Object> getData() {
    return data;
  }

  @Override
  public int getDataIndex() {
    return dataIndex;
  }

}
