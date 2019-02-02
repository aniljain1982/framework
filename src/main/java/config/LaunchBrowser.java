package config;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class LaunchBrowser extends SeleniumBaseTest {
	@BeforeClass
	@Parameters({ "url" })
	public void setup(String url) {
		startBrowser();
		if (getWebDriver() != null) {
			getWebDriver().get(url);
		} else {
			System.out.println("Driver is null");
		}
	}

	@AfterClass
	public void tearDown() {
		getWebDriver().close();
		getWebDriver().quit();
	}
}
