package reuse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import app.HomePage;
import app.LoginPage;

import config.SeleniumBaseTest;

import core.ActionDriver;

public class BusinessFunctions {
	private static Logger log=LogManager.getLogger(BusinessFunctions.class.getName());
	
	WebDriver driver;
	ActionDriver actionDriver;

	public BusinessFunctions() {
		driver = SeleniumBaseTest.getWebDriver();
		actionDriver = new ActionDriver();
	}

	public void login(String userName, String password) throws Exception {
		SeleniumBaseTest.childTest = SeleniumBaseTest.parentTest
				.createNode("Login to application");
		Thread.sleep(6000);
		log.info("Login to application");
		actionDriver.type(LoginPage.inputUserName,
				userName, "user name");
		actionDriver.type(LoginPage.inputPwdName, password, "password");
		Thread.sleep(6000);
		actionDriver.click(LoginPage.inputSubmitButton, "submit button");
		Thread.sleep(6000);
	}

	public void checkIfHomePageLoaded() throws Exception {
		SeleniumBaseTest.childTest = SeleniumBaseTest.parentTest
				.createNode("Check If Login page loaded");
		actionDriver.isElementDisplayed(HomePage.userMenu, "User menu");
	}
}
