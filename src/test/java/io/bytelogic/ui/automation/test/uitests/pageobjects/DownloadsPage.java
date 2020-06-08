package io.bytelogic.ui.automation.test.uitests.pageobjects;

import org.openqa.selenium.By;

import io.bytelogic.ui.automation.core.interaction.WebController;

public class DownloadsPage extends BasePage {

	protected By browserEx = By.id("browsersExpand");
	protected By browserCol = By.id("browsersCollapse");
	protected By chrome = By.xpath("//h3[contains(text(), 'Chrome')]");
	
	
	public WebController browsersExpandBtn() {
		return new WebController(browserEx);
	}
	
	public WebController browsersCollBtn() {
		return new WebController(browserCol);
	}
	
	public WebController chromeOption(){
		return new WebController(chrome);
	}
}
