package com.orasi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Router {
  private final Map<String,String> propertyMap = new HashMap<>( 10 );
  private final String name;
  private final String alchemyIdentifier;
  private final String routerDetail;
  
  public Router( String largeName, String alchemyIdentifier, String routerDetail ) {
    this.name = largeName;
    this.alchemyIdentifier = alchemyIdentifier;
    this.routerDetail = routerDetail;
  }
  
  public void addProperty( String name, String value ) {
    
    for ( String keyName : propertyMap.keySet() ) {
      String pValue = propertyMap.get( keyName );
      if ( pValue != null ) {
        value = value.replace( "${" + keyName + "}", pValue );
      }
    }
    
    propertyMap.put( name, value );
  }
  
  public String getProperty( String name ) {
    return propertyMap.get( name );
  }

  /**
   * @return the propertyMap
   */
  public Map<String,String> getPropertyMap() {
    return Collections.unmodifiableMap(propertyMap);
  }

  /**
   * @return the maximumAvailable
   */
  public String getAlchemyIdentifier() {
    return alchemyIdentifier;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  public String getRouterDetail() {
    return routerDetail;
  }
  
}
