package com.orasi;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.openqa.selenium.By;
import com.orasi.datasource.*;

public class ByFactory {

  private final Class byClass;
  private final String descriptor;
  private final String name;

  public ByFactory(Class byClass, String descriptor, String name) {
    this.byClass = byClass;
    this.descriptor = descriptor;
    this.name = name;
  }

  public By create(Map<String, Object> contextMap, DataSourceProvider dM) {
    String useDescriptor = dM.replaceValues(descriptor, contextMap) + "";
    try {
      return (By) byClass.getConstructor(String.class).newInstance(useDescriptor);
    } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
      throw new IllegalArgumentException( "Could not create locator for " + useDescriptor + " as " + byClass.getName() );
    }
  }

  public String getDescriptor() {
    return descriptor;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
}
