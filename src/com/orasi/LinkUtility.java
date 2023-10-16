package com.orasi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author allen
 */
public class LinkUtility {

  private final String[] matchingUrls;
  private By linkLocator = By.xpath("//a[@href]");

  private Map<String, Boolean> linkMap = new HashMap<>(10);

  public LinkUtility( String[] matchingUrls, By linkLocator ) {
    this.matchingUrls = matchingUrls;
    this.linkLocator = linkLocator;
  }
  
  public void analyzePage( String url, WebDriver webDriver ) {
    //
    // If we processed this already, then exit out
    //
    Boolean processed = linkMap.get(url);
    if (processed != null && processed == true) {
      return;
    }
    
    boolean processUrl = false;
    for ( String mU : matchingUrls ) {
      if ( url.startsWith( mU ) ) {
        processUrl = true;
        break;
      }
    }
    if ( !processUrl ) {
      return;
    }

    linkMap.put(url, true);
    webDriver.navigate().to(url);
    List<WebElement> elementList = webDriver.findElements(linkLocator);
    List<String> urlList = new ArrayList<>( 10 );
    for (WebElement wE : elementList) {
      String hRef = wE.getAttribute("href");

      if (!"#".equals(hRef) && !hRef.contains("javascript")) {
        urlList.add( hRef );
      }
    }
    
    for ( String u : urlList ) {
      analyzePage( u, webDriver);
    }
  }
  
  public String[] getURLs() {
    return linkMap.keySet().toArray( new String[ 0 ] );
  }
}
