package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ImacProdPage extends BasePage {

	//constructor
	
	public ImacProdPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locator
	
	@FindBy(xpath="//h1[normalize-space()='iMac']")
	WebElement prodText;
	
	//action
	
	public boolean doesImacExist()
	{
		try {
			return(prodText.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
}
