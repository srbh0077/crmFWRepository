package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "user_name")
	private WebElement usernameEdit;	// Generally it has default Access Modifier, only accessed within package
										// To achieve Encapsulation we must restrict direct access.
	@FindBy(name = "user_password")
	private WebElement passwordEdit;
	
	@FindBy(id = "submitButton")
	private WebElement loginButton;

	
	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	public void loginAction(String url, String username, String password) 
	{
		driver.get(url);
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginButton.click();
	}
	
}
