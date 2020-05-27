package io.bytelogic.ui.automation.test;

import org.testng.annotations.BeforeMethod;

import io.bytelogic.ui.automation.test.uitests.pageobjects.HomePage;


public class BaseTest {
	
	public HomePage home;

	@BeforeMethod(alwaysRun = true)
	public void setUp(){
		//Can add more boilerplate code here 
	}
}
