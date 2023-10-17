
package com.orasi;

import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.By.*;
import org.openqa.selenium.*;
import com.orasi.datasource.*;


public class ObjectManager {
  private static final ObjectManager singleton = new ObjectManager();
  
  public static final ObjectManager instance() {
    return singleton;
  }
  
  private final Map<String,ByFactory> objectMap = new HashMap<>( 10 );
  
  private ObjectManager() {
    ByFactoryCollection bC = null;
    /*
    Site: demoqa.com
    
    */
    /* Page: DEMOQA 
    
    */

    

bC = new ByFactoryCollection("Elements");
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='card mt-4 top-card'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@class='category-cards']/DIV[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='category-cards']/DIV[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]", null ) );

objectMap.put( "13334.1613", bC );


bC = new ByFactoryCollection("item-0");
bC.add( new ByFactory( ByXPath.class, "(//LI[@class='btn btn-light '])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//UL[@class='menu-list'])[1]/LI[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//LI[./SPAN[text()=\"Text Box\"]]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/UL[1]/LI[1]", null ) );

objectMap.put( "13334.1624", bC );


bC = new ByFactoryCollection("userName");
bC.add( new ByFactory( ByXPath.class, "//INPUT[@id=\"userName\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "userName", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"userName\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@placeholder='Full Name']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@placeholder='Full Name']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@type='text']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@type='text']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@class=' mr-sm-2 form-control']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class=' mr-sm-2 form-control']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@autocomplete='off'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='col-md-9 col-sm-12'])[1]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[2]/DIV[2]/FORM[1]/DIV[1]/DIV[2]/INPUT[1]", null ) );

objectMap.put( "13334.1635", bC );


bC = new ByFactoryCollection("userEmail");
bC.add( new ByFactory( ByXPath.class, "//INPUT[@id=\"userEmail\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "userEmail", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"userEmail\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@placeholder='name@example.com']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@placeholder='name@example.com']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@type='email']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@type='email']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@class='mr-sm-2 form-control']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='mr-sm-2 form-control']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@autocomplete='off'])[2]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='col-md-9 col-sm-12'])[2]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[2]/DIV[2]/FORM[1]/DIV[2]/DIV[2]/INPUT[1]", null ) );

objectMap.put( "13334.1664", bC );


bC = new ByFactoryCollection("currentAddress");
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@id=\"currentAddress\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "currentAddress", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"currentAddress\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@placeholder='Current Address']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@placeholder='Current Address']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//TEXTAREA[@class='form-control'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//TEXTAREA[@rows='5'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//TEXTAREA[@cols='20'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='col-md-9 col-sm-12'])[3]/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[2]/DIV[2]/FORM[1]/DIV[3]/DIV[2]/TEXTAREA[1]", null ) );

objectMap.put( "13334.1692", bC );


bC = new ByFactoryCollection("permanentAddress");
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@id=\"permanentAddress\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "permanentAddress", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"permanentAddress\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//TEXTAREA[@class='form-control'])[2]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//TEXTAREA[@rows='5'])[2]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//TEXTAREA[@cols='20'])[2]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='col-md-9 col-sm-12'])[4]/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[2]/DIV[2]/FORM[1]/DIV[4]/DIV[2]/TEXTAREA[1]", null ) );

objectMap.put( "13334.1716", bC );


  }
  
  public By getObject( Object alchemyIdentifier, Map<String,Object> contextMap, DataSourceProvider dM ) {
    
    if ( alchemyIdentifier instanceof By ) {
      return (By) alchemyIdentifier;
    }
    
    ByFactory by = objectMap.get( alchemyIdentifier + "" );
    if ( by == null ) {
      return new By() {
        @Override
        public List<WebElement> findElements(SearchContext sc) {
          throw new RuntimeException( "Could not find and object using [" + alchemyIdentifier + "]" );
        }
      };
    }
    return by.create(contextMap, dM);
  }

  public ByFactory getObject( String alchemyIdentifier ) {
   
    return objectMap.get( alchemyIdentifier );
  }
}
