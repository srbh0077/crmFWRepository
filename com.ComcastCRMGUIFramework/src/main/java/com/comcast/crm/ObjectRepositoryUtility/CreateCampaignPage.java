package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignPage 
{
	WebDriver driver;
	public CreateCampaignPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "campaignname")
	private WebElement campaignNameEdit;
	
	@FindBy(xpath = "//input[@title = 'Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(xpath = "//input[@value = 'U']")
	private WebElement assignedToUserChkBox;		
	
	@FindBy(xpath = "//input[@value = 'T']")
	private WebElement assignedToGroupChkBox;	
	
	@FindBy(name = "assigned_user_id")
	private WebElement assignedToUserDD;
	
	@FindBy(name = "assigned_group_id")
	private WebElement assignedToGroupDD;
	
	@FindBy(name = "campaigntype")
	private WebElement campaignTypeDD;
	
	@FindBy(name = "campaignstatus")
	private WebElement campaignStatusDD;
	
	@FindBy(xpath = "//input[@name = 'product_name']/following-sibling::img")
	private WebElement productAddImg;
	
	@FindBy(name = "closingdate")
	private WebElement closeDateEdit;
	
	
	
	
	
	public WebElement getCampaignNameEdit() {
		return campaignNameEdit;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getAssignedToUserChkBox() {
		return assignedToUserChkBox;
	}

	public WebElement getAssignedToGroupChkBox() {
		return assignedToGroupChkBox;
	}

	public WebElement getAssignedToUserDD() {
		return assignedToUserDD;
	}

	public WebElement getAssignedToGroupDD() {
		return assignedToGroupDD;
	}

	public WebElement getCampaignTypeDD() {
		return campaignTypeDD;
	}

	public WebElement getCampaignStatusDD() {
		return campaignStatusDD;
	}

	public WebElement getProductAddImg() {
		return productAddImg;
	}

	public WebElement getCloseDateEdit() {
		return closeDateEdit;
	}
	
	
}
