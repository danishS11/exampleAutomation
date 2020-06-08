package io.bytelogic.ui.automation.test.uitests.pageobjects;

import org.openqa.selenium.By;

import io.bytelogic.ui.automation.core.interaction.WebController;

public class HomePage extends BasePage {
	
	public HomePage(){
		new WebController().waitTillPageLoaded();
	}

	protected By dlBtn = By.xpath("//a[text()= 'Downloads']");
	
	public WebController downloadButton() {
		return new WebController(dlBtn);
	}
}
