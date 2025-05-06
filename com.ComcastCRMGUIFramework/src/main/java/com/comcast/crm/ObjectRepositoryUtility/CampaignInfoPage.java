package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignInfoPage 
{
	WebDriver driver;
	public CampaignInfoPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class = 'dvHeaderText']")
	private WebElement headerMsg;
	
	@FindBy(id = "dtlview_Campaign Name")
	private WebElement campaignInfo;
	
	@FindBy(id = "dtlview_Campaign Type")
	private WebElement campaignTypeInfo;
	
	@FindBy(id = "dtlview_Assigned To")
	private WebElement assignedToInfo;
	
	@FindBy(id = "dtlview_Campaign Status")
	private WebElement campaignStatusInfo;
	
	@FindBy(id = "dtlview_Product")
	private WebElement productAddedInfo;
	
	@FindBy(xpath = "//td[text() = 'Expected Close Date']/following-sibling::td")
	private WebElement closingDateInfo;
	
	
	
	
	
	
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getCampaignInfo() {
		return campaignInfo;
	}

	public WebElement getCampaignTypeInfo() {
		return campaignTypeInfo;
	}

	public WebElement getAssignedToInfo() {
		return assignedToInfo;
	}

	public WebElement getCampaignStatusInfo() {
		return campaignStatusInfo;
	}

	public WebElement getProductAddedInfo() {
		return productAddedInfo;
	}

	public WebElement getClosingDateInfo() {
		return closingDateInfo;
	}
	

}