package com.orasi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import com.orasi.datasource.*;

public class ByFactoryCollection extends ByFactory {

  private final List<ByFactory> byList = new ArrayList<>( 10 );
  public ByFactoryCollection( ByFactory... byFactories ) {
    super( ByCollection.class, null, null );
    byList.addAll(Arrays.asList( byFactories ));
  }
  
  public ByFactoryCollection( String name ) {
    super( ByCollection.class, null, name );
  }
  
  public ByFactoryCollection() {
    super( ByCollection.class, null, "Unknown" );
  }
  
  public final void add( ByFactory bF ) {
    byList.add( bF );
  }

  @Override
  public By create(Map<String, Object> contextMap, DataSourceProvider dM) {
    
    By[] bA = new By[ byList.size() ];
    
    for ( int i=0; i< byList.size(); i++ ) {
      bA[ i ] = byList.get( i ).create(contextMap, dM);
    }
    
    return new ByCollection( bA );
    
  }
}
