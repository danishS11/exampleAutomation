package io.bytelogic.ui.automation.core.app;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.testng.Assert;
import org.w3c.dom.Document;


public class Config {

	private static String SYSTEM_TEST_PROP = "system.test.";
	private static String USER_DIR_PROP = "user.dir";

	public static String getFilePath(String parameterName) {
		String parameterValue = System.getProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase());
		if (parameterValue != null) {
			return System.getProperty(USER_DIR_PROP) + parameterValue.replace("/", File.separator);
		}
		String propFromXML = getXPathValueFromFile(getConfigFileLocation(), getParameterValue(parameterName));
		System.setProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase(), propFromXML);
		return System.getProperty(USER_DIR_PROP) +  propFromXML.replace("/", File.separator);
	}

	public static Integer getInt(String parameterName) {
		String parameterValue = System.getProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase());
		if (parameterValue != null) {
			return Integer.parseInt(parameterValue);
		}
		String propFromXML = getXPathValueFromFile(getConfigFileLocation(), getParameterValue(parameterName));
		System.setProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase(), propFromXML);
		return Integer.parseInt(propFromXML);
	}

	public static Boolean getBoolean(String parameterName) {
		String parameterValue = System.getProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase());
		if (parameterValue != null) {
			return Boolean.valueOf(parameterValue);
		}
		String propFromXML = getXPathValueFromFile(getConfigFileLocation(), getParameterValue(parameterName));
		System.setProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase(), propFromXML);
		return Boolean.valueOf(propFromXML);
	}

	public static String getString(String parameterName) {
		String parameterValue = System.getProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase());
		if (parameterValue != null) {
			return parameterValue;
		}
		String propFromXML = getXPathValueFromFile(getConfigFileLocation(), getParameterValue(parameterName));
		System.setProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase(), propFromXML);
		return propFromXML;
	}

	private static String getConfigFileLocation() {
		String fileLoc = "TestNG.xml";
		return fileLoc.replace("/", File.separator);
	}

	public static String getResultXMLLocation(){
		String fileLoc = System.getProperty(USER_DIR_PROP) + "/test-output/testng-results.xml";
		return fileLoc.replace("/", File.separator);
	}

	private static String getParameterValue(String parameterName) {
		return "//parameter[@name='" + parameterName + "']/@value";
	}

	public static String getGroupValue(){
		String paramName = "//include/@name";
		String parameterValue = System.getProperty(SYSTEM_TEST_PROP + paramName.toLowerCase());
		if (parameterValue != null) {
			return parameterValue;
		}
		String propFromXML = getXPathValueFromFile(getConfigFileLocation(), paramName);
		System.setProperty(SYSTEM_TEST_PROP + paramName.toLowerCase(), propFromXML);
		return propFromXML;
	}

	public static String getXPathValueFromFile(String fileLocation, String xpathQuery) {
		String value = null;
		try {
			File file = new File(fileLocation);
			DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = xmlFactory.newDocumentBuilder();
			Document xmlDoc = docBuilder.parse(file);
			XPathFactory xpathFact = XPathFactory.newInstance();
			XPath xpath = xpathFact.newXPath();
			value = (String) xpath.evaluate(xpathQuery, xmlDoc, XPathConstants.STRING);
		} catch (Exception e) {
			Assert.fail("Failed to retrieve configuration value from Config File at '" + fileLocation 
					+ "' with xpath query '" + xpathQuery + "'.", e);
		}
		return value;
	}
	
}
