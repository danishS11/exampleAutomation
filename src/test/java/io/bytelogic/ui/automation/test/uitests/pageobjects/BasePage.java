package io.bytelogic.ui.automation.test.uitests.pageobjects;

import org.openqa.selenium.By;

import io.bytelogic.ui.automation.core.interaction.WebInteraction;

public class BasePage {
	
	protected By hero = By.tagName("h1");
	
	public WebInteraction heroTitle() {
		return new WebInteraction(hero);
	}

}
