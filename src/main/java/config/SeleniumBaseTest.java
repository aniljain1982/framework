package config;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class SeleniumBaseTest {
	public static WebDriver driver;
	private static String browser;

	String method;
	ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	
	@BeforeTest
	@Parameters({ "browser" })
	public void setParameters(String browser) {
		setBrowser(browser);
	}

	protected void setBrowser(String browserName) {
		this.browser = browserName;
	}

	protected String getBrowser() {
		return this.browser;
	}

	protected void setWebdriver(WebDriver driver) {
		this.driver = driver;
	}

	public static WebDriver getWebDriver() {
		return driver;
	}

	@BeforeTest
	public void generateReport() {
		// Create a blank file at specified location
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")
				+ "/Reports/MyReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@BeforeMethod
	public void methodName(Method method) {
		// Get the method name
		parentTest = extent.createTest(method.getName());

	}

	public void startBrowser() {
		if (getBrowser().toUpperCase().equals("CHROME")) {
			WebDriverManager.chromedriver().setup();
			extent.setSystemInfo("Chrome version", WebDriverManager
					.chromedriver().getDownloadedVersion());
			extent.setSystemInfo("Chrome path", WebDriverManager.chromedriver()
					.getBinaryPath());
			driver = new ChromeDriver();
		} else if (getBrowser().toUpperCase().equals("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (getBrowser().toUpperCase().equals("FF")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		setWebdriver(driver);
	}

	@AfterClass
	public void afterClass() {
		extent.flush();
	}
}
