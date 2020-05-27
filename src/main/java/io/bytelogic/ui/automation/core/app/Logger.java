package io.bytelogic.ui.automation.core.app;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Level;
import org.testng.Reporter;


public class Logger {

	public static void logMessage(String inMessage) {
		logConsoleMessage(inMessage);
		Reporter.log(inMessage);
	}

	public static void logConsoleMessage(String inMessage) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date()) + " " + inMessage);
	}

	public static void disableLog4JConsoleOutput() {
		Logger.logConsoleMessage("Disabling log4j console output.");
		org.apache.log4j.Logger.getLogger("org.BIU.utils.logging.ExperimentLogger").setLevel(Level.OFF);
		org.apache.log4j.Logger.getRootLogger().setLevel(Level.OFF);
	}

	public static void enableLog4JConsoleOutput() {
		Logger.logConsoleMessage("Enabling log4j console output.");
		org.apache.log4j.Logger.getLogger("org.BIU.utils.logging.ExperimentLogger").setLevel(Level.ALL);
		org.apache.log4j.Logger.getRootLogger().setLevel(Level.ALL);
	}
}
