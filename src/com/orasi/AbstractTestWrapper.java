package com.orasi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;

public abstract class AbstractTestWrapper implements TestWrapper {

  protected static Logger log = LoggerFactory.getLogger(TestWrapper.class);
  protected GsonBuilder gsonBuilder = new GsonBuilder();
  protected Gson gson = gsonBuilder.create();
  private final int id;
  private final String name;
  private final String description;
  private final String dataName;
  private final int synchronizationPoint;
  private final String[] tags;
  private final String alchemyIdentifier;
  private final String testDetail;

  protected abstract void _executeTest(int executionId, int testExecutionId, WebDriver webDriver, Map<String, Object> contextMap, String contextName, Stack<String> callStack, Stack<Integer> stepStack);

  public AbstractTestWrapper(int id, String alchemyIdentifier, String name, String description, int synchronizationPoint, String dataName, String[] tags, String testDetail ) {
    this.id = id;
    this.name = name;
    this.dataName = dataName;
    this.description = description;
    this.synchronizationPoint = synchronizationPoint;
    this.tags = tags;
    this.alchemyIdentifier = alchemyIdentifier;
    this.testDetail = testDetail;
  }

  protected <T extends Number> T parseNumber(Object value, Class<T> numberType) {
    if (value == null) {
      return null;
    }

    if (numberType.isAssignableFrom(value.getClass())) {
      return (T) value;
    }

    if (value instanceof String) {

      if ( value.equals( "" ) ) {
        return null;
      }

      switch (numberType.getSimpleName()) {
        case "Long":

          return (T) (Long) Double.valueOf( (String) value ).longValue();
        case "Short":
          return (T) (Short) Double.valueOf((String) value ).shortValue();
        case "Byte":
          return (T) new Byte( (String) value);
        case "Double":
          return (T) Double.valueOf((String) value );
        case "Float":
          return (T) (Float) Double.valueOf((String) value ).floatValue();
      }
    } else {
      switch (numberType.getSimpleName()) {
        case "Long":
          return (T) (Long) Double.valueOf( value + "" ).longValue();
        case "Short":
          return (T) (Short) Double.valueOf(value + "" ).shortValue();
        case "Byte":
          return (T) new Byte( value + "");
        case "Double":
          return (T) Double.valueOf(value + "" );
        case "Float":
          return (T) (Float) Double.valueOf(value + "" ).floatValue();
      }
    }
    
    throw new IllegalArgumentException( "Failed to parse number as " + numberType.getName() + " using " + value );
  }

  @Override
  public void executeTest(int executionId, int testExecutionId, WebDriver webDriver, Map<String, Object> contextMap, String contextName, Stack<String> callStack, Stack<Integer> stepStack) {
    this._executeTest(executionId, testExecutionId, webDriver, contextMap, contextName, callStack, stepStack);
  }

  @Override
  public void executeTest(int executionId, int testExecutionId, WebDriver webDriver) {
    log.info("Starting Test " + getName());
    _executeTest(executionId, testExecutionId, webDriver, new HashMap<>(10), "", new Stack<>(), new Stack<>());
  }

  @Override
  public String[] getTags() {
    return tags;
  }

  @Override
  public String getTestDetail() {
    return testDetail;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getDataName() {
    return dataName;
  }

  @Override
  public String getAlchemyIdentifier() {
    return alchemyIdentifier;
  }

  @Override
  public int getSynchronizationPoint() {
    return synchronizationPoint;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Map<String, Object> getData() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int getDataIndex() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
