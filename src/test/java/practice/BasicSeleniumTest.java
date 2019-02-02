package practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BasicSeleniumTest {
	@Test
	public void basicSeleniumTest(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\anilj\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		
		driver.close();
		driver.quit();
	}
}
