package scripts;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import reuse.BusinessFunctions;

import config.LaunchBrowser;
import config.SeleniumBaseTest;

public class TC1 extends LaunchBrowser{
	@Test
	@Parameters({"userName", "password"})
	public void testLogin(String userName, String password) throws Exception{
		BusinessFunctions fns=new BusinessFunctions();
		fns.login(userName,password);
		//fns.checkIfHomePageLoaded();
	}
}
