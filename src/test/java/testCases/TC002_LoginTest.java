package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups={"Sanity", "Master"})
	public void verify_login()
	{
		logger.info("****Starting TC002_LoginTest *****");
		
		try {
			
		
		//HomePage
		HomePage hp =new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on 'MyAccount' Link");
		hp.clickLogin();
		logger.info("Clicked on 'Login' link");
		
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		logger.info("Email entered....");
		
		lp.setPassword(p.getProperty("password"));
		logger.info("Password entered.....");
		
		lp.clickLogin();
		
		
		//MyAccount
		MyAccountPage map=new MyAccountPage(driver);
		logger.info("Evaluating login status...");
		boolean targetPage=map.isMyAccountPageExists();
		
		Assert.assertEquals(targetPage, true, "Login failed");  //login failed will be displayed if boolean evaluates to false
		
		

		}
		catch (Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****Finished TC002_LoginTest *****");
	}
}
