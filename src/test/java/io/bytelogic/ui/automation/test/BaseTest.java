package io.bytelogic.ui.automation.test;

import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import io.bytelogic.ui.automation.test.uitests.pageobjects.DownloadsPage;
import io.bytelogic.ui.automation.test.uitests.pageobjects.HomePage;


public class BaseTest {
	
	public HomePage home;
	public DownloadsPage dlPage;
	public SoftAssert sAssert;

	@BeforeMethod(alwaysRun = true)
	public void setUp(){
		sAssert = new SoftAssert();
	}
}
