package io.bytelogic.ui.automation.core.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import io.bytelogic.ui.automation.core.app.Logger;
import io.bytelogic.ui.automation.core.common.Props.ConfigProps;


public class ReportUtil {

	public static void takeScreenshot(RemoteWebDriver driver) {
		try{
			String timestamp, path = "";
			File screenshotName;
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			screenshotName = new File(ConfigProps.SCREENSHOT_PATH + timestamp + ".jpg");
			FileUtils.copyFile(scrFile, screenshotName);
			String filePath = screenshotName.toString();
			path = "<img src=\"file://" + filePath + "\" alt=\"\"/>";
			Logger.logConsoleMessage("Attaching failure screenshot to the report");
			Reporter.log(path);
		}catch (NullPointerException | IOException | IllegalArgumentException e){}
	}
}
