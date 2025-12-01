package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")  //need to include an additional parameter of class since dataprovider class is in a different package and import utilities package
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException
	{
        logger.info("****Starting TC003_LoginDDT *****");
		
        try
        {
		//HomePage
		HomePage hp =new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on 'MyAccount' Link");
		hp.clickLogin();
		logger.info("Clicked on 'Login' link");
		
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		logger.info("Email entered....");
		
		lp.setPassword(pwd);
		logger.info("Password entered.....");
		
		lp.clickLogin();
		
		
		//MyAccount
		MyAccountPage map=new MyAccountPage(driver);
		logger.info("Evaluating login status...");
		boolean targetPage=map.isMyAccountPageExists();
		
		
		//EVALUATION
		
		/*
		 Data is valid - login success - test passed - logout
		 Data is valid - login failed - test failed
		 
		 Data is invalid - login success - test failed - logout
		 Data is invalid - login failed - test passed
		 */
		if(exp.equalsIgnoreCase("Valid"))
		{
			if (targetPage==true)
			{
				map.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		
		
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				map.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
        }
        catch(Exception e)
        {
        	Assert.fail();
        }
        Thread.sleep(3000);
		logger.info("****Completed TC003_LoginDDT *****");
	}
	
}
