package io.bytelogic.ui.automation.core.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.bytelogic.ui.automation.core.common.Props.ConfigProps;

public class DriverFactory {

	
	public void webDriverInit(){
		ChromeOptions cap = new ChromeOptions();
		String url = "http://" + ConfigProps.DOCKER_IP + ":" + ConfigProps.WEB_PORT_NUMBER + "/wd/hub";
		cap.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		try {
			DriverManager.setWebDriver(new RemoteWebDriver(new URL(url), cap));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
