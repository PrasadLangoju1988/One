package com.orasi;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import static com.orasi.shared_library.*;
import com.orasi.event.spi.*;
import com.orasi.model.EOTException;
import com.orasi.model.StepException;
import com.orasi.model.TestPayload;

public class ExecutionWrapper implements Runnable {

  private final Logger log = LoggerFactory.getLogger(ExecutionWrapper.class);
  private final EndpointDevice eD;
  private final TestWrapper tW;
  private long startTime;
  private long lastAccess = System.currentTimeMillis();
  private final int testExecutionId = getTestCounter();

  public ExecutionWrapper(EndpointDevice eD, TestWrapper tW) {
    this.eD = eD;
    this.tW = tW;
  }

  public long getRunTime() {
    return System.currentTimeMillis() - startTime;
  }

  @Override
  public void run() {
    startTime = System.currentTimeMillis();

    Thread.currentThread().setName(tW.getName() + " running on " + eD.getName());

    TestPayload testPayload = new TestPayload();
    testPayload.setExecutionIdentifier(SuiteExecutionWrapper.instance().getExecutionId());
    testPayload.setTestExecutionIdentifier(testExecutionId);
    testPayload.setSuiteId(SuiteExecutionWrapper.instance().getId());
    testPayload.setTestDetail(tW.getTestDetail());
    testPayload.setTargetDetail(eD.getTargetDetail());
    testPayload.setRouterDetail(eD.getRouterDetail());
    notifyListeners(new TestEvent(testPayload, tW.getName(), 1));

    MonitoredRemoteWebDriver wD = null;

    try {
      DataManager.instance().initializeTest();
      //
      // Connect to the device
      //
      wD = new MonitoredRemoteWebDriver((RemoteWebDriver) eD.connect(), this);

      //
      // Execute the test
      //
      tW.executeTest(SuiteExecutionWrapper.instance().getExecutionId(), testExecutionId, wD);

      testPayload = new TestPayload();
      testPayload.setTestExecutionIdentifier(testExecutionId);
      notifyListeners(new TestEvent(testPayload, tW.getName(), 4));
      if (log.isInfoEnabled()) {
        log.info("Test Successful!");
      }

    } catch (EOTException e) {
      testPayload = new TestPayload();
      testPayload.setMessage(e.getMessage());
      testPayload.setTestExecutionIdentifier(testExecutionId);
      if (e.isTestPassed()) {
        if (log.isInfoEnabled()) {
          log.info("Test Successful!");
        }
        notifyListeners(new TestEvent(testPayload, tW.getName(), 4));
      } else {
        log.error(tW.getClass().getName() + "[" + testExecutionId + "] on " + eD.getName() + " failed with" + e.getMessage());
        notifyListeners(new TestEvent(testPayload, tW.getName(), 3));
      }
    } catch (StepException t) {
      log.error(tW.getClass().getName() + "[" + testExecutionId + "] on " + eD.getName() + " failed with" + t.getMessage());
      testPayload = new TestPayload();
      testPayload.setMessage(t.getMessage());
      testPayload.setTestExecutionIdentifier(testExecutionId);
      notifyListeners(new TestEvent(testPayload, tW.getName(), 3));
    } catch (Throwable t) {
      testPayload = new TestPayload();
      if (t.getCause() != null) {
        log.error(tW.getClass().getName() + "[" + testExecutionId + "] on " + eD.getName() + " failed with" + t.getCause().getMessage());
        testPayload.setMessage(t.getCause().getMessage());
      } else {
        log.error(tW.getClass().getName() + "[" + testExecutionId + "] on " + eD.getName() + " failed with" + t.getMessage());
        testPayload.setMessage(t.getMessage());
      }

      testPayload.setTestExecutionIdentifier(testExecutionId);
      notifyListeners(new TestEvent(testPayload, tW.getName(), 3));
    } finally {
      if ( log.isInfoEnabled() ) {
        log.info( "Test Complete!" );
      }
      DataManager.instance().afterTest();
      if (wD != null) {
        try {
          wD.close();
        } catch (Exception e) {
        }
        try {
          wD.quit();
        } catch (Exception e) {
        }
      }
      Thread.currentThread().setName("Idle Thread...");
    }
  }

  protected List<Throwable> getThrowableList(Throwable throwable) {
    final List<Throwable> list = new ArrayList<>();
    while (throwable != null && !list.contains(throwable)) {
      list.add(throwable);
      throwable = throwable.getCause();
    }
    return list;
  }

  protected Throwable getRootCause(final Throwable throwable) {
    final List<Throwable> list = getThrowableList(throwable);
    return list.isEmpty() ? throwable : list.get(list.size() - 1);
  }

  private byte[] screenShot(WebDriver webDriver) {
    if (webDriver instanceof TakesScreenshot) {
      return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    } else {
      return null;
    }
  }

  /**
   * @return the lastAccess
   */
  public long getLastAccess() {
    return lastAccess;
  }

  public void setLastAccess() {
    this.lastAccess = System.currentTimeMillis();
  }

  /**
   * @return the eD
   */
  public EndpointDevice getEndpointDevice() {
    return eD;
  }

  /**
   * @return the tW
   */
  public TestWrapper getTestWrapper() {
    return tW;
  }

  public int getId() {
    return testExecutionId;
  }
}
