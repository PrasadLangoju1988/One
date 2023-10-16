package com.orasi;

import java.net.URL;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class EndpointDevice {

  protected static Logger log = LoggerFactory.getLogger(EndpointDevice.class);
  private static final String URL = "URL";
  private static final String[] KNOWN_PROPERTIES = new String[]{"URL"};

  private final Router router;
  private final ExecutionTarget executionTarget;

  private int queueIndex;

  public EndpointDevice(Router router, ExecutionTarget executionTarget) {
    this.router = router;
    this.executionTarget = executionTarget;
  }

  public int getMaximumAvailable() {
    return executionTarget.getMaximumAvailable();
  }

  public String getName() {
    return executionTarget.getName() + " at " + router.getName();
  }

  public int getQueueIndex() {
    return queueIndex;
  }

  public void setQueueIndex(int queueIndex) {
    this.queueIndex = queueIndex;
  }

  private boolean isKnown(String p) {
    for (String s : KNOWN_PROPERTIES) {
      if (s.equals(p)) {
        return true;
      }
    }

    return false;
  }

  public String getProperty(String keyName) {
    String value = router.getProperty(keyName);
    if (value == null) {
      return executionTarget.getProperty(keyName);
    } else {
      return value;
    }
  }

  public WebDriver connect() {
    DesiredCapabilities dC = new DesiredCapabilities();
    //
    // Add the router properties first
    //
    router.getPropertyMap().keySet().stream().filter(key -> (!isKnown(key))).map(key -> {
      log.info("Adding ROUTER Capability [" + key + "] as [" + router.getPropertyMap().get(key) + "]");
      return key;
    }).forEachOrdered(key -> {
      dC.setCapability(key, router.getPropertyMap().get(key));
    });

    executionTarget.getPropertyMap().keySet().stream().filter(key -> (!isKnown(key))).map(key -> {
      log.info("Adding TARGET Capability [" + key + "] as [" + executionTarget.getPropertyMap().get(key) + "]");
      return key;
    }).forEachOrdered(key -> {
      dC.setCapability(key, executionTarget.getPropertyMap().get(key));
    });

    try {
      WebDriver webDriver = new RemoteWebDriver(new URL(getProperty(URL)), dC);
      webDriver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
      return webDriver;
    } catch (Exception e) {

      StringBuilder errorBuilder = new StringBuilder();
      errorBuilder.append("Error connectong to ").append(executionTarget.getName()).append(" at ").append(router.getName()).append(" using:\r\n");

      router.getPropertyMap().keySet().forEach(key -> {
        errorBuilder.append( "\tROUTER: ").append( key ).append( " = [").append( router.getPropertyMap().get(key) ).append( "]\r\n" );
      });
      
      
      executionTarget.getPropertyMap().keySet().forEach(key -> {
        errorBuilder.append( "\tTARGET: ").append( key ).append( " = [").append( executionTarget.getPropertyMap().get(key) ).append( "]\r\n" );
      });

      throw new IllegalArgumentException(errorBuilder.toString(), e );
    }

  }

  public void disconnect(WebDriver webDriver) {
    try {
      log.info("Closing connection to " + executionTarget.getName() + " at " + router.getName());
      webDriver.close();
    } catch (Exception e) {
    }
    try {
      log.info("Destroying WebDriver instance for " + executionTarget.getName() + " at " + router.getName());
      webDriver.quit();
    } catch (Exception e) {
    }
  }

  /**
   * @return the routerDetail
   */
  public String getRouterDetail() {
    return router.getRouterDetail();
  }

  /**
   * @return the targetDetail
   */
  public String getTargetDetail() {
    return executionTarget.getTargetDetail();
  }

}
