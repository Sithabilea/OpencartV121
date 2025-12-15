package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ImacProdPage;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC005_AddToCartPageTest extends BaseClass {

	@Test(groups={"Sanity", "Master"})
	public void verify_addToCart()
	{
		logger.info("****Starting TC005_AddToCartPageTest****");
		
		
		try {
			//HomePage
			HomePage hp=new HomePage(driver);
			hp.searchInputText();
			logger.info("Entered 'imac' in search bar");
			hp.searchButton();
			
			
			//SearchPage
			SearchPage sp=new SearchPage(driver);
			sp.imacProdLink();
			logger.info("Clicked on 'imac' product on products display page");
			
			//ImacProductPage
			ImacProdPage ipp=new ImacProdPage(driver);
			ipp.clickAddToCart();
			logger.info("Clicked on 'Add To Cart'");
			
			ipp.clickCartLink();
			logger.info("Clicked on link to shopping cart");
			
			
			
			//ShoppingCartPage
			ShoppingCartPage scp=new ShoppingCartPage(driver);
			logger.info("Validating if imac product is added to cart");
			boolean addToCartValidate=scp.isImacLinkDisplayed();
			
			Assert.assertEquals(addToCartValidate, true, "Add to Cart Failed"); //"Add to Cart Failed" will be displayed if boolean evaluates to false
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****Finished TC005_AddToCartPageTest****");
		
		
	}
}
