package io.bytelogic.ui.automation.test.uitests.pageobjects;

import org.openqa.selenium.By;

import io.bytelogic.ui.automation.core.interaction.WebInteraction;

public class ClaimsPage {

	protected By amznBtn = By.xpath("//button[@value='Amazon']");
	
	public WebInteraction amazonButton() {
		return new WebInteraction(amznBtn);
	}
}
