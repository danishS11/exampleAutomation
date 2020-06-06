package io.bytelogic.ui.automation.test.uitests.pageobjects;

import org.openqa.selenium.By;

import io.bytelogic.ui.automation.core.interaction.WebInteraction;

public class HomePage extends BasePage {
	
	public HomePage(){
		new WebInteraction().waitTillPageLoaded();
	}

	protected By dlBtn = By.xpath("//a[text()= 'Downloads']");
	
	public WebInteraction downloadButton() {
		return new WebInteraction(dlBtn);
	}
}
