package io.bytelogic.ui.automation.core.interaction;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

import io.bytelogic.ui.automation.core.app.Logger;
import io.bytelogic.ui.automation.core.driver.DriverManager;
import io.bytelogic.ui.automation.core.util.ScriptExecutor;

public class WebInteraction {

	private RemoteWebDriver webDriver;

	// locator data
	private By locator;

	// element data
	private Boolean elementPresent;
	private Boolean elementVisible;
	private WebElement webElement;
	private List<WebElement> allWebElements;

	// wait constants
	private static Long fluentWaitTimeout = 5000L;
	private static Integer timeout = 5;
	private static Integer pollingTime = 50;
	private static Integer smallPause = 1000;
	private static final String  GET_READY_STATE = "return document.readyState";

	public WebInteraction(By locator){
		webDriver = DriverManager.getWebDriver();
		this.locator = locator;
	}

	public WebInteraction(WebElement ele){
		webDriver = DriverManager.getWebDriver();
		this.webElement = ele;
	}

	public WebInteraction(){
		webDriver = DriverManager.getWebDriver();
	}

	public WebElement getWebElement() {
		return webElement;
	}

	public void setWebElement(){
		try{
			DriverManager.getWebDriver().findElement(locator);
		} catch (StaleElementReferenceException e) {
			pause(smallPause);
			waitForVisible();
		}
	}

	public By getElementLocator() {
		return locator;
	}

	public List<WebElement> getAllWebElements() {
		return allWebElements;
	}

	public void setAllWebElements(){
		DriverManager.getWebDriver().findElements(locator);
	}


	/**********************************************************************************************
	 * Check if an element is visible on the screen using fluent wait 
	 * 
	 * @return {@link Boolean}
	 **********************************************************************************************/
	public Boolean isVisible(){
		getFluentWait(locator)
		.ignoring(WebDriverException.class)
		.until(new Function<By, Boolean>(){
			public Boolean apply(final By loc){
				elementPresent = false;
				elementVisible = false;
				allWebElements = webDriver.findElements(loc);
				if(allWebElements.size() > 0){
					webElement = allWebElements.get(0);
					elementPresent = true;
					elementVisible = webElement.isDisplayed();
				}
				return elementVisible;
			}
		});
		Logger.logMessage("Is the element located by: " + locator + " visible: " + elementVisible.toString());
		return elementVisible;
	}


	/**********************************************************************************************
	 * Wait for element to be visible
	 * 
	 * @return Fluid instance of WebInteraction class
	 **********************************************************************************************/
	public WebInteraction waitForVisible() {
		Logger.logMessage("Waiting for element: " + locator + " to be visible");
		getFluentWait(locator)
		.ignoring(WebDriverException.class)
		.withMessage("element with locator '" + locator.toString() + "' is not visible when it should be.")
		.until(new Function<By, Boolean>() {
			public Boolean apply(final By loc) {
				webElement = webDriver.findElement(loc);
				return webElement.isDisplayed();
			}
		});

		return this;
	}


	/**********************************************************************************************
	 * This method uses a webElement instead of a By object
	 * to wait on for an object to be visible.
	 * 
	 * @return Fluent instance of Webinteraction class
	 *********************************************************************************************/
	public WebInteraction waitForElementVisible() {
		new FluentWait<WebElement>(webElement)
		.withTimeout(timeoutDuration(TimeUnit.SECONDS, timeout))
		.pollingEvery(pollingDuration(TimeUnit.MILLISECONDS, pollingTime))
		.ignoring(WebDriverException.class)
		.until(new Function<WebElement, Boolean>() {
			public Boolean apply(final WebElement ele){
				return ele.isDisplayed();
			}
		});
		return this;
	}


	/**********************************************************************************************
	 * Wait till page is completely loaded
	 * 
	 * @return {@link Boolean} - true if page is loaded
	 **********************************************************************************************/
	public Boolean waitTillPageLoaded() {

		return getFluentWait()
				.ignoring(WebDriverException.class)
				.withMessage("Wait till page is loaded.")
				.until(new Function<WebDriver, Boolean>() {
					public Boolean apply(WebDriver driver) {
						return (Boolean) new ScriptExecutor().execute(GET_READY_STATE)
								.equals("complete");

					}
				});
	}


	/**********************************************************************************************
	 * Wait for URL is loaded
	 * 
	 * @param title {@link String} - URL to be checked
	 * @return Fluid instance of WebInteraction class
	 **********************************************************************************************/
	public WebInteraction waitTillTitleContains(String title) {
		try {
			this.getFluentWait()
			.ignoring(WebDriverException.class)
			.withMessage(title + "' is not loaded.")
			.until(ExpectedConditions.titleContains(title));
		} catch (TimeoutException e) {
			//			ignore;
		}
		return this;
	}


	/**********************************************************************************************
	 * Waits for an element to not be present before timing out.
	 * 
	 * @return Fluid instance of the Interact class
	 * @exception TimeoutException - An element is present during the entire given timeout
	 *                period.
	 ***********************************************************************************************/
	public WebInteraction waitForNotPresent(Integer timeout, TimeUnit timeUnit) {
		getFluentWait(locator, timeout, timeUnit)
		.ignoring(WebDriverException.class)
		.until(new Function<By, Boolean>() {
			public Boolean apply(final By loc) {
				elementPresent = true;
				allWebElements = webDriver.findElements(loc);
				if (allWebElements.size() == 0) {
					elementPresent = false;
				}
				return !elementPresent;
			}
		});
		return this;
	}


	/**********************************************************************************************
	 * Pauses the test action.
	 * 
	 * @param waitTime - {@link Integer} - The amount of time in milliseconds to pause.
	 * @return Fluid instance of the Interact class
	 **********************************************************************************************/
	public WebInteraction pause(Integer waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	/**********************************************************************************************
	 * Pauses the test action.
	 *
	 * @param waitTime - {@link Integer} - The amount of time to pause.
	 * @param units- {@link TimeUnit } - the time unit for the {@code waitTime} argument
	 * @return Fluid instance of the Interact class
	 **********************************************************************************************/
	public WebInteraction pause(Integer waitTime, TimeUnit units) {
		Logger.logMessage("Pausing for '" + waitTime + " " + units + "'");
		try {
			units.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	/*********************************************************************************************
	 * Wrapped Selenium type() method which handles stale element as well
	 * 
	 * @param input {@link String} The string that needs to be typed
	 * 
	 * @return WebInteraction object
	 *********************************************************************************************/
	public WebInteraction type(String input){
		getNonStaleElement().sendKeys(input);
		return this;
	}

	/********************************************************************************************
	 * Wrapped click() method which handles stale element as well
	 * 
	 * @return WebInteraction object
	 ********************************************************************************************/
	public WebInteraction click(){
		getNonStaleElement().click();
		return this;
	}

	/********************************************************************************************
	 * Wrapped sendKeys() method which handles stale element as well 
	 * 
	 * @param {@link Keys} Takes keys as argument to 
	 * send unconventional keys. E.g. Keys.ENTER etc.
	 * 
	 * @return WebInteraction object
	 ********************************************************************************************/
	public WebInteraction keyPress(Keys keys){
		new Actions(DriverManager.getWebDriver())
		.moveToElement(webElement).sendKeys(keys).perform();;
		return this;
	}

	/********************************************************************************************
	 * Wrapped Selenium navigate().to() method and waits for the page is loaded.
	 * 
	 * @param url {@link String} A url to be navigated to
	 * 
	 * @return WebInteraction object
	 ********************************************************************************************/
	public WebInteraction getUrl(String url){
		Logger.logMessage("Navigating to the test page");
		DriverManager.getWebDriver().navigate().to(url);
		waitTillPageLoaded();
		return this;
	}

	/**********************************************************************************************
	 * Method that waits for the current URL to change from old to new.
	 * 
	 * @param oldUrl {@link String} The old URL that needs to be changed to the new one. 
	 **********************************************************************************************/
	public void waitForUrlChange(final String oldUrl){
		new FluentWait<WebDriver>((webDriver))
		.withTimeout(timeoutDuration(TimeUnit.MILLISECONDS, fluentWaitTimeout))
		.pollingEvery(pollingDuration(TimeUnit.MILLISECONDS, pollingTime))
		.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver input) {
				return (!(webDriver).getCurrentUrl().equals(oldUrl));
			}
		});
	}

	/**
	 * Get fluent wait
	 * @return {@link FluentWait} - Fluent wait
	 */
	private FluentWait<WebDriver> getFluentWait() {
		return new FluentWait<WebDriver>(webDriver)
				.withTimeout(timeoutDuration(TimeUnit.MILLISECONDS, fluentWaitTimeout))
				.pollingEvery(pollingDuration(TimeUnit.MILLISECONDS, pollingTime));
	}

	private FluentWait<By> getFluentWait(final By locator) {
		return new FluentWait<By>(locator)
				.withTimeout(timeoutDuration(TimeUnit.MILLISECONDS, fluentWaitTimeout))
				.pollingEvery(pollingDuration(TimeUnit.MILLISECONDS, pollingTime));
	}

	private FluentWait<By> getFluentWait(final By locator, Integer timeout, TimeUnit timeUnit){
		return new FluentWait<By>(locator)
				.withTimeout(timeoutDuration(timeUnit, timeout))
				.pollingEvery(pollingDuration(TimeUnit.MILLISECONDS, pollingTime));
	}

	private static Duration timeoutDuration(TimeUnit timeUnit, long timeDuration) {
		Duration dur = null;
		switch(timeUnit) {
		case MINUTES:
			dur = Duration.ofMinutes(timeDuration);
			break;
		case SECONDS :
			dur = Duration.ofSeconds(timeDuration);
			break;

		case MILLISECONDS :
			dur = Duration.ofMillis(timeDuration);
			break;

		default : 
		}
		return dur;
	}

	private static Duration pollingDuration(TimeUnit timeUnit, long timeDuration) {
		Duration dur = null;
		switch(timeUnit) {
		case SECONDS :
			dur = Duration.ofSeconds(timeDuration);
			break;

		case MILLISECONDS :
			dur = Duration.ofMillis(timeDuration);
			break;

		default : 
		}
		return dur;
	}

	private WebElement getNonStaleElement() {
		try {
			webElement.isEnabled();
		} catch (StaleElementReferenceException e) {
			pause(smallPause);
			waitForVisible();
		}
		return webElement;
	}
}
