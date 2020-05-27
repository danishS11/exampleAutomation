package io.bytelogic.ui.automation.test.uitests.pageobjects;

import org.openqa.selenium.By;

import io.bytelogic.ui.automation.core.interaction.WebInteraction;

public class HomePage {
	
	public HomePage(){
		new WebInteraction().waitTillPageLoaded();
	}

	protected By claimBtn = By.xpath("//a[@class = 'nav-item btn btn-primary' and not(contains(@tabIndex, '-1'))]");
	
	public WebInteraction filaAClaimBtn() {
		return new WebInteraction(claimBtn);
	}
}
