package io.bytelogic.ui.automation.core.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.bytelogic.ui.automation.core.driver.DriverManager;


public class ScriptExecutor {

	protected JavascriptExecutor jse;
	protected RemoteWebDriver driver;

	public ScriptExecutor() {
		driver = DriverManager.getWebDriver();
		jse = (JavascriptExecutor) driver;
	}

	@SuppressWarnings("unchecked")
	public <T> T execute(String command){
		return (T) jse.executeScript(command); 
	}

	@SuppressWarnings("unchecked")
	public <T> T execute(String command,Object... we ){
		return (T) jse.executeScript(command,we); 
	}
}
