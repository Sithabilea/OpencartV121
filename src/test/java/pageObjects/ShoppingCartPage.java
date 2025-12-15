package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage{

	//Constructor
	public ShoppingCartPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	//Locator
	@FindBy(linkText="iMac")
	WebElement imacProdLink;
	
	
	//Action
	public boolean isImacLinkDisplayed()
	{
		try {
			return(imacProdLink.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
