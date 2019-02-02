package core;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.MediaEntityBuilder;

import config.SeleniumBaseTest;

public class ActionDriver extends PageObject {
	WebDriver driver;

	public ActionDriver() {
		super(SeleniumBaseTest.getWebDriver());
		driver = SeleniumBaseTest.getWebDriver();
	}

	/**
	 * Navigate to application
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void navigateToApplication(String url) throws Exception {
		try {
			driver.get(url);
			SeleniumBaseTest.childTest
					.pass("Navigated to application successfully");
		} catch (Exception e) {
			SeleniumBaseTest.childTest.fail(
					"Unable to Navigate to application", MediaEntityBuilder
							.createScreenCaptureFromBase64String(screenshot())
							.build());
			SeleniumBaseTest.childTest.info(e);
			throw e;
		}
	}

	public void click(By locator, String eleName) throws Exception {
		try {
			waitUntilElementToBeClickable(locator, explicitWaitInSeconds).click();
			// driver.findElement(locator).click();
			SeleniumBaseTest.childTest
					.pass("Performed click action successfully on :" + eleName);
		} catch (Exception e) {
			SeleniumBaseTest.childTest.fail(
					"Unable to perform click action on :" + eleName,
					MediaEntityBuilder.createScreenCaptureFromBase64String(
							screenshot()).build());
			SeleniumBaseTest.childTest.info(e);
			throw e;
		}
	}

	public void type(By locator, String testData, String eleName)
			throws Exception {
		try {
			// driver.findElement(locator).sendKeys(testData);
			waitUntilPresenceOfElementLocated(locator, explicitWaitInSeconds).sendKeys(testData);
			SeleniumBaseTest.childTest.pass("Performed type in :" + eleName
					+ " with data :" + testData);
		} catch (Exception e) {
			SeleniumBaseTest.childTest.fail("Unable to Perform type in :"
					+ eleName + " with data :" + testData, MediaEntityBuilder
					.createScreenCaptureFromBase64String(screenshot()).build());
			SeleniumBaseTest.childTest.info(e);
			throw e;

		}
	}

	public void isElementDisplayed(By locator, String elementName)
			throws Exception {

		try {
			// driver.findElement(locator).isDisplayed();
			waitUntilPresenceOfElementLocated(locator, explicitWaitInSeconds).isDisplayed();
			SeleniumBaseTest.childTest.pass("Home page loaded and "
					+ elementName + " found");
		} catch (Exception e) {
			SeleniumBaseTest.childTest.fail(
					"Home page not loaded ",
					MediaEntityBuilder.createScreenCaptureFromBase64String(
							screenshot()).build());
			SeleniumBaseTest.childTest.info(e);
			throw e;
		}
	}

	public void mouseHover(By locator, String eleName) throws Exception {
		try {
			Actions action = new Actions(driver);
			// WebElement mouse = driver.findElement(locator);
			// action.moveToElement(mouse).build().perform();
			action.moveToElement(
					waitUntilPresenceOfElementLocated(locator, explicitWaitInSeconds)).build()
					.perform();

			SeleniumBaseTest.childTest.pass("perfomred mouse over on : "
					+ eleName);
		} catch (Exception e) {
			SeleniumBaseTest.childTest.fail("Unable to Perform mouse over :"
					+ eleName, MediaEntityBuilder
					.createScreenCaptureFromBase64String(screenshot()).build());
			SeleniumBaseTest.childTest.info(e);
			throw e;
		}
	}

	public String screenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}
}
