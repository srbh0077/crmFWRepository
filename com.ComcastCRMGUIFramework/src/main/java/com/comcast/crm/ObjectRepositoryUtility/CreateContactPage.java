package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class CreateContactPage 
{
	WebDriver driver;
	public CreateContactPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement contactNameEdit;
	
	@FindBy(xpath = "//input[@title = 'Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(xpath = "//input[@name = 'account_name']/following-sibling::img")
	private WebElement orgNameAddImg;
	
	@FindBy(name = "support_start_date")
	private WebElement supportStartDateEdit;
	
	@FindBy(name = "support_end_date")
	private WebElement supportEndDateEdit;

	
	
	public WebElement getContactNameEdit() {
		return contactNameEdit;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getOrgNameAddImg() {
		return orgNameAddImg;
	}

	public WebElement getSupportStartDateEdit() {
		return supportStartDateEdit;
	}

	public WebElement getSupportEndDateEdit() {
		return supportEndDateEdit;
	}

	
	
	
	
	
	public void createContactAction(String contactName) 
	{
		contactNameEdit.sendKeys(contactName);
		saveButton.click();
	}
	
	/**
	 * 
	 * @param contactName
	 * @param daysFromNow
	 */
	public void createContactAction(String contactName, int daysFromNow)
	{
		contactNameEdit.sendKeys(contactName);
		
		JavaUtility ju = new JavaUtility();
		String startDate = ju.getSystemDateInDDMMYYYY();
		supportStartDateEdit.clear();
		supportStartDateEdit.sendKeys(startDate);
		
		String endDate = ju.getRequiredDateInDDMMYYYY(daysFromNow);
		supportEndDateEdit.clear();
		supportEndDateEdit.sendKeys(endDate);
		
		saveButton.click();
	}
	
	/**
	 * 
	 * @param contactName
	 * @param orgName
	 */
	public void createContactAction(String contactName, String partialData, String orgName) 
	{
		contactNameEdit.sendKeys(contactName);
		
		orgNameAddImg.click();
		String parentID = driver.getWindowHandle();
		
		DriverUtility du = new DriverUtility();
		du.switchToChildWindow(driver, partialData);
			OrganizationsPopupWindow opw = new OrganizationsPopupWindow(driver);
			opw.getOrgNameEdit().sendKeys(orgName);
			opw.getSearchButton().click();
			opw.getOrgNameFound().click();
			//driver.findElement(By.xpath("//a[text() = '" + orgName + "']")).click();
		
		driver.switchTo().window(parentID);
		
		saveButton.click();
	}
}
