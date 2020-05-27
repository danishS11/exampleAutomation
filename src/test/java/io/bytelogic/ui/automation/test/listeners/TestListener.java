package io.bytelogic.ui.automation.test.listeners;

import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.bytelogic.ui.automation.core.app.Logger;
import io.bytelogic.ui.automation.core.driver.DriverFactory;
import io.bytelogic.ui.automation.core.driver.DriverManager;
import io.bytelogic.ui.automation.core.util.ReportUtil;
import io.bytelogic.ui.automation.test.BaseTest;


public class TestListener extends BaseTest implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		Logger.logConsoleMessage("=================== TEST " + result.getName() + " STARTED =================");
		new DriverFactory().webDriverInit();
		try {
			DriverManager.getWebDriver().manage().window().maximize();
		} catch (WebDriverException e) {
			// ignore for now.
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Logger.logMessage("=============== TEST " + result.getName() + " PASSED ==============");
		stopSession();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ReportUtil.takeScreenshot(DriverManager.getWebDriver());
		stopSession();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		stopSession();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		Logger.disableLog4JConsoleOutput();	
		Logger.logMessage("========== NEW TEST SESSION STARTING =========");
	}

	@Override
	public void onFinish(ITestContext context) {
		Logger.logMessage("==========TEST COMPLETE=========");		
	}

	private void stopSession() {
		if (DriverManager.getWebDriver() != null) {
			if (DriverManager.getWebDriver().getSessionId() != null) {
				// close the driver session
				try {
					DriverManager.getWebDriver().quit();
				} catch (Exception e) {
					Logger.logConsoleMessage("Driver failed to quit gracefully.");
					e.printStackTrace();
				}
			}
		}
	}

}
