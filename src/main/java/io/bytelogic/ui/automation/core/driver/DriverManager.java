package io.bytelogic.ui.automation.core.driver;

import org.openqa.selenium.remote.RemoteWebDriver;


public class DriverManager {

	private static ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<RemoteWebDriver>();
	
	public static RemoteWebDriver getWebDriver() {
		return webDriver.get();
	}
	
	public static synchronized void setWebDriver(RemoteWebDriver driver) {
		DriverManager.webDriver.set(driver);
	}
	
	public static synchronized void quitWebDriver(){
		getWebDriver().close();
		getWebDriver().quit();
	}
	
}
