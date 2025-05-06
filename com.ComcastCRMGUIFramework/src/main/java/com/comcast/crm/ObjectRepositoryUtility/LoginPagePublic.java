package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPagePublic 
{
	@FindBy(name = "user_name")
	public WebElement usernameEdit;
	
	@FindBy(name = "user_password")
	public WebElement passwordEdit;
	
	@FindBy(id = "submitButton")
	public WebElement loginButton;

	
	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	public void loginAction(String username, String password) 
	{
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginButton.click();
	}
}
