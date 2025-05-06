package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.WebdriverUtility.DriverUtility;

public class HomePage 
{
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText = "Products")
	private WebElement productsLink;
	
	@FindBy(linkText = "Leads")
	private WebElement leadsLink;

	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignsLink;
	
	@FindBy(linkText = "Price Books")
	private WebElement pricebookLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;
	
	
	
	
	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getProductsLink() {
		return productsLink;
	}

	public WebElement getLeadsLink() {
		return leadsLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}

	public WebElement getPricebookLink() {
		return pricebookLink;
	}

	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}
	

	public void navigateToCampaignPage()
	{
		DriverUtility du = new DriverUtility();
		du.toMouseHover(driver, moreLink);
	/*	
		moreLink.click();
	*/	
		campaignsLink.click();
	}
	
	public void navigateToPricebookPage()
	{
		DriverUtility du = new DriverUtility();
		du.toMouseHover(driver, pricebookLink);
	/*	
		Actions action = new Actions(driver);
		action.moveToElement(pricebookLink).perform();
	*/	
		pricebookLink.click();
	}
	
	public void signOutAction()
	{
		DriverUtility du = new DriverUtility();
		du.toMouseHover(driver, adminImg);
		signOutLink.click();
	}
}
