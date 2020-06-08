package io.bytelogic.ui.automation.test.uitests.pageobjects;

import org.openqa.selenium.By;

import io.bytelogic.ui.automation.core.interaction.WebController;

public class BasePage {
	
	protected By hero = By.tagName("h1");
	
	public WebController heroTitle() {
		return new WebController(hero);
	}

}
