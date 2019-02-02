package core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

enum Condition {
	PRESENCE_OF_ELEMENT_LOCATED_BY, ELEMENT_TO_BE_CLICKABLE, ELEMENT_TO_BE_CLICKABLE_BY, PROXY_ELEMENT_LOCATED, VISIBILITY_OF_ELEMENT, VISIBILITY_OF_ELEMENT_BY;
}

public class PageObject {
	protected WebDriver driver;
	protected int explicitWaitInSeconds = 120;
	
	public PageObject(WebDriver driver) {
		this.driver = driver;
	}

	public JavascriptExecutor getJavaScriptExecutor() {
		return ((JavascriptExecutor) this.driver);
	}

	protected WebDriverWait explicitWait(int timeOutInSeconds) {
		return new WebDriverWait(driver, timeOutInSeconds);
	}

	private WebElement waitUntil(Condition condition, By by,
			WebElement element, int timeOutInSeconds) {
		WebElement returnWebElement = null;
		try {
			switch (condition) {
			case PRESENCE_OF_ELEMENT_LOCATED_BY:
				returnWebElement = explicitWait(timeOutInSeconds).until(
						ExpectedConditions.presenceOfElementLocated(by));
				break;
			case ELEMENT_TO_BE_CLICKABLE:
				returnWebElement = explicitWait(timeOutInSeconds).until(
						ExpectedConditions.elementToBeClickable(element));
				break;
			case ELEMENT_TO_BE_CLICKABLE_BY:
				returnWebElement = explicitWait(timeOutInSeconds).until(
						ExpectedConditions.elementToBeClickable(by));
				break;
			}
		} catch (Exception e) {

		}
		return returnWebElement;
	}

	protected WebElement waitUntilPresenceOfElementLocated(By by,
			int timeOutInSeconds) {
		return waitUntil(Condition.PRESENCE_OF_ELEMENT_LOCATED_BY, by, null,
				timeOutInSeconds);
	}

	protected WebElement waitUntilElementToBeClickable(By by,
			int timeOutInSeconds) {
		return waitUntil(Condition.ELEMENT_TO_BE_CLICKABLE_BY, by, null,
				timeOutInSeconds);
	}

	protected WebElement waitUntilElementToBeClickable(WebElement element,
			int timeOutInSeconds) {
		return waitUntil(Condition.ELEMENT_TO_BE_CLICKABLE, null, element,
				timeOutInSeconds);
	}
}
