package io.bytelogic.ui.automation.core.common;

import io.bytelogic.ui.automation.core.app.Config;

public class Props {
	
	public interface Groups{
		public String FULL = "Full";
		public String BROKEN = "Broken";
	}

	public interface ConfigProps {
		public static String WEB_PORT_NUMBER = Config.getString("webPort");
		public static String DOCKER_IP = System.getProperty("HUB_HOST");
		public static String MACHINE_IP = Config.getString("machineIP");
		public static String OS = System.getProperty("client.os");
		public static String TEST_RESOURCES_PATH = Config.getFilePath("testResourcesPath");
		public static String SCREENSHOT_PATH = Config.getFilePath("screenshotPath");
		public static String MAIN_RESOURCES_PATH = Config.getFilePath("mainResourcesPath");
		public static String MACHINE_OS = System.getProperty("os.name");
	}
	
	public interface TestProps {
		public static String TEST_URL = Config.getString("testUrl");
		public static String CLAIMS_URL = Config.getString("claimsUrl");
	}
	
}
