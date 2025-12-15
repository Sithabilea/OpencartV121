package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	//constructor - to invoke the parent class constructor using 'super' keyword
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	
	//locators
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement searchInput;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement searchBtn;
	
	
	//action methods
	public void clickMyAccount()
	{
		lnkMyaccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}
	
	public void searchInputText()
	{
		searchInput.sendKeys("imac");
	}
	
	public void searchButton()
	{
		searchBtn.click();
	}
	
	
}
