package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

	//constructor
	
	public SearchPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locator
	
	@FindBy(xpath="//a[normalize-space()='iMac']")
	WebElement imacLink;
	
	//action
	public void imacProdLink()
	{
		imacLink.click();
	}
}
