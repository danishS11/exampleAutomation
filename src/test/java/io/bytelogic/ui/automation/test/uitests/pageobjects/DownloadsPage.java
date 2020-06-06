package io.bytelogic.ui.automation.test.uitests.pageobjects;

import org.openqa.selenium.By;

import io.bytelogic.ui.automation.core.interaction.WebInteraction;

public class DownloadsPage extends BasePage {

	protected By browserEx = By.id("browsersExpand");
	protected By browserCol = By.id("browsersCollapse");
	protected By chrome = By.xpath("//h3[contains(text(), 'Chrome')]");
	
	
	public WebInteraction browsersExpandBtn() {
		return new WebInteraction(browserEx);
	}
	
	public WebInteraction browsersCollBtn() {
		return new WebInteraction(browserCol);
	}
	
	public WebInteraction chromeOption(){
		return new WebInteraction(chrome);
	}
}
