package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ImacProdPage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_SearchProductTest extends BaseClass{

	@Test(groups= {"Sanity", "Master"})
	public void verify_prod_search()
	{
		logger.info("****Starting TC004_SearchProductTest****");
		
		
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
			lp.setEmail(p.getProperty("email"));
			logger.info("Email entered....");
			
			lp.setPassword(p.getProperty("password"));
			logger.info("Password entered.....");
			
			lp.clickLogin();
			
			
		//MyAccountPage
			MyAccountPage map=new MyAccountPage(driver);
			logger.info("Entering 'imac' in search bar...");
			map.searchProduct();
			
			logger.info("Clicking on search submit button");
			map.searchSubmitBtn();
	
		//SearchPage
			
			SearchPage search=new SearchPage(driver);
			logger.info("Clicking on 'imac' product link");
			search.imacProdLink();
			
			
			
		//ImacProductPage
			ImacProdPage imac=new ImacProdPage(driver);
			logger.info("Evaluating product existence status.....");
			boolean targetProduct=imac.doesImacExist();
			
			Assert.assertEquals(targetProduct, true, "Login failed");  //login failed will be displayed if boolean evaluates to false
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****Finished TC004_SearchProductTest****");
	}
	
	
}
