package com.orasi;

import com.orasi.datasource.DataField;
import com.orasi.datasource.DataRow;
import com.orasi.datasource.DataTable;
import com.orasi.datasource.DataSource;
import com.orasi.datasource.DataSourceProvider;
import com.orasi.secrets.SecretsProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataManager implements DataSourceProvider {

  private static final DataManager singleton = new DataManager();

  public static final DataManager instance() {
    return singleton;
  }

  private DataManager() {

  }

  private final ThreadLocal<Map<String, DataRow>> dataSet = new ThreadLocal<>();

  private static final Logger log = LoggerFactory.getLogger(DataSource.class);
  private static final Pattern DATA_REGEX = Pattern.compile("\\$\\{([^}]*)\\}");
  private static final Pattern SECRET_REGEX = Pattern.compile("\\!\\{([^}]*)\\}");
  private static final Pattern ENVIRONMENT_REGEX = Pattern.compile("\\%\\{([^}]*)\\}");
  private static final Pattern CONTEXT_REGEX = Pattern.compile("\\#\\{([^}]*)\\}");
  private final Map<String, DataSource> providerMap = new HashMap<>(10);
  private final Map<String, SecretsProvider> secretsProviderMap = new HashMap<>(10);
  private final Map<String, String> defaultEnvironmentMap = new HashMap<>(10);
  private final Map<String, String> environmentMap = new HashMap<>(10);

  public void registerDataSource(String name, DataSource h) {
    this.providerMap.put(name, h);
  }

  public void registerSecretsProvider(String name, SecretsProvider h) {
    this.secretsProviderMap.put(name, h);
  }

  public void addDefaultEnvironmentProperty(String name, String value) {
    this.defaultEnvironmentMap.put(name, value);
  }

  public void addEnvironmentProperties(Map<String, String> envMap) {
    this.environmentMap.putAll(envMap);
  }

  public DataManager getPrivateInstance() {
    return new DataManager();
  }

  /**
   * Clears the thread local data storage
   */
  public void initializeTest() {
    dataSet.set(new HashMap<>(5));
  }

  /**
   * Used to manually put a run into the local data set during execution
   *
   * @param fieldName
   * @param row
   */
  @Override
  public void setRow(String fieldName, DataRow row) {
    dataSet.get().put(fieldName, row);
  }

  @Override
  public DataRow getRow( String fieldName ) {
    return dataSet.get().get( fieldName );
  }

  public void afterTest() {
    Map<String, DataRow> dataMap = dataSet.get();

    if (dataMap != null) {
      //
      // We need to release any values first
      //
      dataMap.forEach((k, v) -> {

        log.warn("Need to release " + k);
      });
    }
  }

  /**
   * Returns a list of field values represented by the field name formatted as
   * [collection seed].[collection id]-[source seed].[source id]
   *
   * @param fieldName
   * @param contextMap
   * @return
   */
  public List<Map<String, Object>> getSource(String fieldName, Map<String, Object> contextMap) {

    if ( fieldName == null || fieldName.trim().isEmpty() ) {
      return null;
    }

    if (log.isDebugEnabled()) {
      log.debug("Attempting to find a values for [" + fieldName + "]");
    }

    List<Map<String, Object>> lM = (List<Map<String, Object>>) getContext(fieldName, contextMap);

    if (lM == null) {

      String[] lookupMap = fieldName.split("-");

      if (lookupMap.length != 2) {
        throw new IllegalArgumentException("A data source lookup must be formatted as [source seed].[source id]-[table seed].[table id]");
      }

      DataSource<String, DataTable> dS = providerMap.get(lookupMap[0]);
      if (dS == null) {
        throw new IllegalArgumentException("No data source was registered for [" + lookupMap[0] + "]");
      }

      DataTable h = dS.getTable(lookupMap[1]);

      if (h == null) {
        throw new IllegalArgumentException("No table was found for [" + lookupMap[1] + "] in [" + lookupMap[0] + "]");
      }
      
      return h.getRawData();
    } else {
      return lM;
    }

  }

  public Object replaceValues(String initialValue) {
    return replaceValues(initialValue, null);
  }

  @Override
  public Object replaceValues(String initialValue, Map<String, Object> contextMap) {
    String returnValue = initialValue;
    Matcher m = CONTEXT_REGEX.matcher(initialValue);
    while (m.find()) {
      Object newValue = getContext(m.group(1), contextMap);
      if (newValue != null && !newValue.equals(m.group(1))) {
        if (m.group(0).equals(initialValue)) {
          return newValue;
        } else {
          returnValue = returnValue.replace(m.group(0), newValue.toString());
        }
      }
    }

    m = DATA_REGEX.matcher(initialValue);
    while (m.find()) {
      Object newValue = getValue(m.group(1), contextMap);
      if (newValue != null && !newValue.equals(m.group(1))) {
        if (m.group(0).equals(initialValue)) {
          return newValue;
        } else {
          returnValue = returnValue.replace(m.group(0), newValue.toString());
        }
      }
    }

    m = SECRET_REGEX.matcher(initialValue);
    while (m.find()) {
      Object newValue = getSecret(m.group(1), contextMap);
      if (newValue != null && !newValue.equals(m.group(1))) {
        if (m.group(0).equals(initialValue)) {
          return newValue;
        } else {
          returnValue = returnValue.replace(m.group(0), newValue.toString());
        }
      }
    }

    m = ENVIRONMENT_REGEX.matcher(initialValue);
    while (m.find()) {
      Object newValue = getEnvironment(m.group(1), contextMap);
      if (newValue != null && !newValue.equals(m.group(1))) {
        if (m.group(0).equals(initialValue)) {
          return newValue;
        } else {
          returnValue = returnValue.replace(m.group(0), newValue.toString());
        }
      }
    }

    return returnValue;
  }

  public Object getContext(String key, Map<String, Object> contextMap) {

    Object rV = getSystemProperty(key);

    if (rV != null) {
      return rV;
    }

    if (contextMap == null) {
      return null;
    }

    Object returnValue = contextMap.get(key);
    if (returnValue != null) {
      if (log.isDebugEnabled()) {
        log.debug("Value found as [" + returnValue + "] from the context map");
      }
      return returnValue;
    }

    return null;
  }

  private Object getSystemProperty(String key) {
    Object returnValue = System.getProperty(key);
    if (returnValue != null) {
      if (log.isDebugEnabled()) {
        log.debug("Value found as [" + returnValue + "] from the system/command line properties");
      }
      return returnValue;
    }

    return null;
  }

  public Object getValue(String fieldName) {
    return getValue(fieldName, null);
  }

  /**
   * Returns a value in STRING form. This value is mapped by 3 parts separated
   * by -. ${[collection seed].[collection id]-[source seed].[source id]-[field
   * name]}
   *
   * @param fieldName
   * @param contextMap
   * @return
   */
  public Object getValue(String fieldName, Map<String, Object> contextMap) {
    String useValue = fieldName;

    Matcher m = DATA_REGEX.matcher(fieldName);
    if (m.find()) {
      //
      // Strip the ${} if found
      //
      useValue = m.group(1);
    }

    //
    // Before we use secrets, we check if a context variable or system property override this
    //
    Object returnValue = getContext(useValue, contextMap);
    if (returnValue != null) {
      return returnValue;
    }

    String[] lookupMap = useValue.split("-");

    if (lookupMap.length != 3) {
      log.warn("A lookup field must be formatted as ${[source seed].[source id]-[table seed].[table id]-[field name]}");
      return fieldName;
    }

    if (log.isDebugEnabled()) {
      log.debug("Attempting to find a values for [" + useValue + "]");
    }

    //
    // We check the context map first to make sure this row was not already loaded
    //
    String rowIdentifier = useValue.substring(0, useValue.lastIndexOf("-"));
    DataRow r = null;

    if (dataSet.get() != null) {
      r = dataSet.get().get(rowIdentifier);
    }
    if (r == null) {
      DataSource<String, DataTable> dS = providerMap.get(lookupMap[0]);
      if (dS == null) {
        log.warn("No data source was registered for [" + lookupMap[0] + "]");
        return fieldName;
      }

      DataTable<String, DataField, DataRow> h = dS.getTable(lookupMap[1]);
      if (h == null) {
        log.warn("No table was found for [" + lookupMap[1] + "] in [" + lookupMap[0] + "]");
        return fieldName;
      }

      if (log.isDebugEnabled()) {
        log.debug("The value was not found in the local map.  Loading a new row");
      }
      r = h.getRow(true, 120);
    }

    if (r == null) {
      log.warn("No data source was found for [" + lookupMap[1] + "] in [" + lookupMap[0] + "]");
      return fieldName;
    }

    //
    // Store a reference to the current row
    //
    dataSet.get().put(rowIdentifier, r);

    return r.getField(lookupMap[2]) + "";
  }

  public String getSecret(String fieldName) {
    return getSecret(fieldName, null);
  }

  /**
   * Returns a value in STRING form. This value is mapped by 2 parts separated
   * by -. *{[secrets provider seed].[secrets provider id]-[field name]}
   *
   * @param fieldName
   * @param contextMap
   * @return
   */
  public String getSecret(String fieldName, Map<String, Object> contextMap) {
    String useValue = fieldName;

    Matcher m = SECRET_REGEX.matcher(fieldName);
    if (m.find()) {
      //
      // Strip the *{} if found
      //
      useValue = m.group(1);
    }

    //
    // Before we use secrets, we check if a context variable or system property override this
    //
    Object returnValue = getContext(useValue, contextMap);
    if (returnValue != null) {
      return returnValue + "";
    }

    String[] lookupMap = useValue.split("-");

    if (lookupMap.length != 2) {
      log.warn("A lookup field must be formatted as *{[secrets provider seed].[secrets provider id]-[field name]}");
      return fieldName;
    }

    if (log.isDebugEnabled()) {
      log.debug("Attempting to find a values for [" + useValue + "]");
    }

    SecretsProvider sP = secretsProviderMap.get(lookupMap[0]);

    return sP.getSecret(lookupMap[1]);
  }

  /**
   * Returns a value in STRING form. This value is mapped by 2 parts separated
   * by -. *{[secrets provider seed].[secrets provider id]-[field name]}
   *
   * @param fieldName
   * @param contextMap
   * @return
   */
  public String getEnvironment(String fieldName, Map<String, Object> contextMap) {
    String useValue = fieldName;

    Matcher m = ENVIRONMENT_REGEX.matcher(fieldName);
    if (m.find()) {
      //
      // Strip the %{} if found
      //
      useValue = m.group(1);
    }

    //
    // Before we use secrets, we check if a context variable or system property override this
    //
    Object returnValue = getContext(useValue, contextMap);
    if (returnValue != null) {
      return returnValue + "";
    }

    returnValue = environmentMap.get(useValue);

    if (returnValue == null) {
      return defaultEnvironmentMap.get(useValue);
    } else {
      return returnValue + "";
    }
  }

  @Override
  public DataTable getTable(String fieldName) {

    if ( fieldName == null || fieldName.trim().isEmpty() ) {
      return null;
    }

    if (log.isDebugEnabled()) {
      log.debug("Attempting to find a values for [" + fieldName + "]");
    }

    String[] lookupMap = fieldName.split("-");

    if (lookupMap.length != 2) {
      throw new IllegalArgumentException("A data source lookup must be formatted as [source seed].[source id]-[table seed].[table id]");
    }

    DataSource<String, com.orasi.datasource.DataTable> dS = providerMap.get(lookupMap[0]);
    if (dS == null) {
      throw new IllegalArgumentException("No data source was registered for [" + lookupMap[0] + "]");
    }

    com.orasi.datasource.DataTable h = dS.getTable(lookupMap[1]);

    if (h == null) {
      throw new IllegalArgumentException("No table was found for [" + lookupMap[1] + "] in [" + lookupMap[0] + "]");
    }

    h.setKey( fieldName );
    
    return h;
  }
}
