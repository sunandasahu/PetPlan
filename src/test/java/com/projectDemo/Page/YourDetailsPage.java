package com.projectDemo.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.projectDemo.Config.Locators;
import com.projectDemo.Utility.BaseClass;

public class YourDetailsPage extends BaseClass {
	
	@FindBy(xpath=Locators.Your_Details)
	WebElement your_Details;
	
	@FindBy(xpath=Locators.Select_Title)
	WebElement select_Title;
	
	@FindBy(xpath=Locators.FirstName)
	WebElement firstName;
	
	@FindBy(xpath=Locators.SurName)
	WebElement surName;
	
	@FindBy(xpath=Locators.Select_BDate)
	WebElement select_BDate;
	
	@FindBy(xpath=Locators.Select_BMonth)
	WebElement select_BMonth;
	
	@FindBy(xpath=Locators.Select_BYear)
	WebElement select_BYear;
	
	@FindBy(xpath=Locators.Contact_No)
	WebElement contact_No;
	
	@FindBy(xpath=Locators.Email_ID)
	WebElement email_ID;
	
	@FindBy(xpath=Locators.Post_Code)
	WebElement post_Code;
	
	@FindBy(xpath=Locators.Find_Address)
	WebElement find_Address;
	
	@FindBy(xpath=Locators.Select_Address)
	WebElement select_Address;
	
	@FindBy(xpath=Locators.Check_Box)
	WebElement check_Box;
	
	@FindBy(xpath=Locators.Error_Msg2)
	WebElement error_Msg2;
	
	@FindBy(xpath=Locators.Continue_Btn)
	WebElement continue_Btn;
	
	@FindBy(xpath=Locators.Btn_Yes)
	WebElement btn_Yes;
	
	public void veryfyYourDetailPage() {
		WaitForPageToLoad();
//		if (IsElementExists)
		if(your_Details.isDisplayed()) {
			Passed(false,"Page verified");//do i need to write this method in base class?
		}else {
			Failed(false,"page is not verified");
		}
	}
	
	public void selectTitle(String title) {
		if(title.equalsIgnoreCase("Mr")) {
		selectByVisibleText(select_Title, title);
		Passed(false,"title is selected as   "+title);
		}
		else if(title.equalsIgnoreCase("Miss")) {
		selectByVisibleText(select_Title, title);
		Passed(false,"title is selected as   "+title);
		}
		else if(title.equalsIgnoreCase("Mrs")) {
			selectByVisibleText(select_Title, title);
			Passed(false,"title is selected as   "+title);
			}
		else if(title.equalsIgnoreCase("Ms")) {
			selectByVisibleText(select_Title, title);
			Passed(false,"title is selected as   "+title);
			}
		else if(title.equalsIgnoreCase("Dr")) {
			selectByVisibleText(select_Title, title);
			Passed(false,"title is selected as   "+title);
			}
		else {
			Failed(false,"title should be either MR,Miss,Mrs,Ms or DR");
		}
	}
	
	
	
	public void SelectBirthday(String Birthdate) {
		//14-October-2014
		if (Birthdate!="") {
			String[] date = Birthdate.split("-");
			if (date.length==3) {
				String Day = date[0];
				String Month = date[1];
				String Year = date[2];
				selectByVisibleText(select_BDate,Day);
				selectByVisibleText(select_BMonth,Month);
				selectByVisibleText(select_BYear,Year);
			}else {
				Failed(false,"Birthdate should containg with format Day-MonthName-Year");
			}
		}else {
			Failed(false,"Birthdate value is empty");
		}
		Passed(false, "Birthdate selected as = "+Birthdate);
	}
	
	public void selectAddress(String text) {
		Select address= new Select(select_Address);
		highLighterMethod(select_Address);
		address.selectByVisibleText(text);
		//address.selectByIndex(3);
	}
	

	public void ClickOnContinueBtn(WebElement element) {
		int no_ErrorMsg = driver.findElements(By.tagName("ng-message")).size();
		if(no_ErrorMsg>0 && error_Msg2.isDisplayed()) {
			Failed(true,"Some required field are not filled or invelid data");
		}
		else if(element.isEnabled()) {
			ClickElement(continue_Btn);
			WebDriverWait wait = new WebDriverWait(driver,10) ;
			wait.until(ExpectedConditions.titleContains("Pet Insurance for Your Dog"));
			String actualTitle =driver.getTitle();
			String expectedTitle="Pet Insurance for Your Dog, Cat and Rabbit | Petplan";
			if(actualTitle.equals(expectedTitle)) {
				Passed(false,"Title of the page is matched");
			}
			System.out.println("Title of the page is : "+actualTitle);
		}else {
			Failed(true,"Continue button is not enabled");
		}
	Passed(true,"Clicked on Continue button");
	}
	
	
	
	
	public void fetchYourDetails(String title,String FName,String LName,String BirthDay,String Contact_No,String Email,String Postal_Code) {
		veryfyYourDetailPage();
		selectTitle(title);
		SendKey(firstName,FName);
		SendKey(surName,LName);
		SelectBirthday(BirthDay);
		SendKey(contact_No,Contact_No);
		SendKey(email_ID,Email);
		SendKey(post_Code,Postal_Code);
		ClickElement(find_Address);
		sendkeys(select_Address,10,"1 The Rise, Thronton-le-Dele Pickering");
		ClickElement(check_Box);
		ClickOnContinueBtn(continue_Btn);
		Passed(true,"All Details has been filled");
		scrollByVisibilityOfElement(btn_Yes);
		ClickElement(btn_Yes);
		
	}
	
	
	

}
