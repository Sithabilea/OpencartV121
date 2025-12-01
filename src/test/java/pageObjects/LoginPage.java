package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	//constructor
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locator
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmailAddress;
	
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtLoginPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	//action
	
	public void setEmail(String email)
	{
		txtEmailAddress.sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		txtLoginPassword.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btnLogin.click();
	}
}
