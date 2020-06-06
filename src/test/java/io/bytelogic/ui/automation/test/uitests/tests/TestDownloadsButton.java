package io.bytelogic.ui.automation.test.uitests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.bytelogic.ui.automation.core.common.Props.Groups;
import io.bytelogic.ui.automation.core.common.Props.TestProps;
import io.bytelogic.ui.automation.core.driver.DriverManager;
import io.bytelogic.ui.automation.core.interaction.WebInteraction;
import io.bytelogic.ui.automation.test.BaseTest;
import io.bytelogic.ui.automation.test.uitests.pageobjects.DownloadsPage;
import io.bytelogic.ui.automation.test.uitests.pageobjects.HomePage;

public class TestDownloadsButton extends BaseTest{
	
	private void setup() {
		home = new HomePage();
		dlPage = new DownloadsPage();
	}

	@Test(groups = {Groups.FULL})
	public void testExpandButton() {
		setup();
		
		new WebInteraction().getUrl(TestProps.TEST_URL);
		
		home.downloadButton().waitForVisible().click();
		
		dlPage.browsersCollBtn().waitForUrlChange(TestProps.TEST_URL);
		
		dlPage.browsersExpandBtn().waitForVisible().click();
		
		Assert.assertTrue(dlPage.chromeOption().waitForVisible().isVisible(), "Chrome Option is not visible");
		
	}
}
