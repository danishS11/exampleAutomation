package io.bytelogic.ui.automation.test.uitests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.bytelogic.ui.automation.core.common.Props.Groups;
import io.bytelogic.ui.automation.core.common.Props.TestProps;
import io.bytelogic.ui.automation.core.driver.DriverManager;
import io.bytelogic.ui.automation.core.interaction.WebInteraction;
import io.bytelogic.ui.automation.test.BaseTest;
import io.bytelogic.ui.automation.test.uitests.pageobjects.ClaimsPage;
import io.bytelogic.ui.automation.test.uitests.pageobjects.HomePage;

public class TestClaimButton extends BaseTest{

	@Test(groups = {Groups.FULL})
	public void testClaimBtn() {
		home = new HomePage();
		
		claims = new ClaimsPage();
		
		new WebInteraction().getUrl(TestProps.TEST_URL);
		
		home.filaAClaimBtn().waitForVisible().click();
		
		claims.amazonButton().waitForUrlChange(TestProps.TEST_URL);
		
		String url = DriverManager.getWebDriver().getCurrentUrl();
		
		Assert.assertEquals(url, TestProps.CLAIMS_URL, "The Expected URL is not what's expected");
		
	}
}
