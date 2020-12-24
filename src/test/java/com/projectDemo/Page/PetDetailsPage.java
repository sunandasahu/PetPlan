package com.projectDemo.Page;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.projectDemo.Config.Locators;
import com.projectDemo.Utility.BaseClass;

public class PetDetailsPage extends BaseClass {
	
	@FindBy(xpath=Locators.AcceptCookies)
	WebElement acceptCookies;
	
	@FindBy(xpath=Locators.Quote_Dog)
	WebElement quote_Dog;
	
	@FindBy(xpath=Locators.Quote_Cat)
	WebElement quote_Cat;
	
	@FindBy(xpath=Locators.Quote_Rabbit)
	WebElement quote_Rabbit;
	
	@FindBy(xpath=Locators.Pet_Name)
	WebElement pet_Name;
	
	@FindBy(xpath=Locators.Gender_Male)
	WebElement gender_Male;
	
	@FindBy(xpath=Locators.Gender_Female)
	WebElement gender_Female;
	
	@FindBy(xpath=Locators.Neutured_Yes)
	WebElement neutured_Yes;
	
	@FindBy(xpath=Locators.Neutured_No)
	WebElement neutured_No;
	
	@FindBy(xpath=Locators.Injured_Yes)
	WebElement injured_Yes;
	
	@FindBy(xpath=Locators.Injured_No)
	WebElement injured_No;
	
	@FindBy(xpath=Locators.Single_Breed)
	WebElement single_Breed;
	
	@FindBy(xpath=Locators.Mixed_Breed)
	WebElement mixed_Breed;
	
	@FindBy(xpath=Locators.Single_Breed_Name_1)
	WebElement single_Breed_Name_1;
	
	@FindBy(xpath=Locators.Mixed_Breed_Name_1)
	WebElement mixed_Breed_Name_1;
	
	@FindBy(xpath=Locators.Breed_Name_2)
	WebElement breed_Name_2;
	
	@FindBy(xpath=Locators.Select_Day)
	WebElement select_Day;
	
	@FindBy(xpath=Locators.Select_Month)
	WebElement select_Month;
	
	@FindBy(xpath=Locators.Select_Year)
	WebElement select_Year;
	
	@FindBy(xpath=Locators.Assumption_1)
	WebElement assumption_1;
	
	@FindBy(xpath=Locators.Assumption_2)
	WebElement assumption_2;
	
	@FindBy(xpath=Locators.Assumption_3)
	WebElement assumption_3;
	
	@FindBy(xpath=Locators.Assumption_4)
	WebElement assumption_4;
	
	@FindBy(xpath=Locators.Assumption_5)
	WebElement assumption_5;
	
	@FindBy(xpath=Locators.Ins_Start_Day)
	WebElement ins_Start_Day;
	
	@FindBy(xpath=Locators.Ins_Start_Month)
	WebElement ins_Start_Month;
	
	@FindBy(xpath=Locators.Ins_Start_Year)
	WebElement ins_Start_Year;
	
	@FindBy(xpath=Locators.Btn_Continue)
	WebElement btn_Continue;
	
	@FindBy(xpath=Locators.Error_Msg)
	WebElement error_Msg;
	
	//Cat breed name
	@FindBy(xpath=Locators.Breed_Name_Cat)
	WebElement breed_Name_Cat;
	
	
	
	public void  SelectAPet(String PetType) {
		if (PetType.equalsIgnoreCase("DOG")) {
			ClickElement(quote_Dog);
		}else if (PetType.equalsIgnoreCase("CAT")) {
			ClickElement(quote_Cat);
		}else if (PetType.equalsIgnoreCase("RABBIT")) {
			ClickElement(quote_Rabbit);
		}else {
			Failed(false,"Value should be either DOG/CAT/Rabbit,---->PetType = "+PetType);
		}
		Passed(false, "PetType selected as = "+PetType);
	}

	public void  selectGender(String gender) {
		if(gender.equalsIgnoreCase("Male")) {
			ClickElement(gender_Male);
		}else if(gender.equalsIgnoreCase("Female")) {
			ClickElement(gender_Female);
		}else {
			Failed(false,"gender should be male/female--->Gnnder= "+gender);
		}
		Passed(false, "gender selected as = "+gender);
	}
	
	public void selectNeutured(String neutured){
		if(neutured.equalsIgnoreCase("yes")) {
			ClickElement(neutured_Yes);
		}else if(neutured.equalsIgnoreCase("No")) {
			ClickElement(neutured_No);
		}else {
			Failed(false,"Neutured should be yes/No......"+neutured);
		}
		Passed(false,"Neutured selected as..."+neutured);
		
	}
	
	public void selectInjured(String injured) {
		if(injured.equalsIgnoreCase("Yes")) {
			ClickElement(injured_Yes);
		}else if(injured.equalsIgnoreCase("NO")) {
			ClickElement(injured_No);
		}else {
			Failed(false,"Injured should be yes/No......"+injured);
		}
		Passed(false,"Injured selected as..."+injured);
	}
	
	
	public void enterBreedName(String breed,String Breed_Name_1,String Breed_Name_2) {
		if (breed.equalsIgnoreCase("Single")) {
			ClickElement(single_Breed);
			SendKey(single_Breed_Name_1, Breed_Name_1);
		}
		else if(breed.equalsIgnoreCase("mixed")) {
			 ClickElement(mixed_Breed);
			SendKey(mixed_Breed_Name_1, Breed_Name_1);
			SendKey(breed_Name_2,Breed_Name_2);
        }
		else {
			Failed(true,"Element not present   "+breed);
		}
		Passed(false,"Successfully send breed name");	
	}


	
	public void SelectBirthday(String Birthdate) {
		//14-October-2014
		if (Birthdate!="") {
			String[] date = Birthdate.split("-");
			if (date.length==3) {
				String Day = date[0];
				String Month = date[1];
				String Year = date[2];
				selectByVisibleText(select_Day,Day);
				selectByVisibleText(select_Month,Month);
				selectByVisibleText(select_Year,Year);
			}else {
				Failed(false,"Birthdate should containg with format Day-MonthName-Year");
			}
		}else {
			Failed(false,"Birthdate value is empty");
		}
		Passed(false, "Birthdate selected as = "+Birthdate);
	}
	public void selectTodayDate(String DaytoSelect) {
//		if (DaytoSelect.equalsIgnoreCase("today")) {
//			Date date = new Date();
//			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//			int year  = localDate.getYear(); String Year = String.valueOf(year);
//			int month = localDate.getMonthValue();
//			String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
//			String Month = monthNames[month-1];
//			int day   = localDate.getDayOfMonth(); String Day = String.valueOf(day);
//			selectByVisibleText(ins_Start_Day,Day);
//			selectByVisibleText(ins_Start_Month,Month);
//			selectByVisibleText(ins_Start_Year,Year);
//			
//		}else {
			if (DaytoSelect!="") {
				String[] date = DaytoSelect.split("-");
				if (date.length==3) {
					String Day = date[0];
					String Month = date[1];
					String Year = date[2];
					selectByVisibleText(ins_Start_Day,Day);
					selectByVisibleText(ins_Start_Month,Month);
					selectByVisibleText(ins_Start_Year,Year);
				}else {
					Failed(false,"Ins start should containg with format Day-MonthName-Year");
				}
			}else {
				Failed(false,"Ins start  value is empty");
			}
			Passed(false, "Ins start  selected as = "+DaytoSelect);
		}

	public void clickContinueBtn(WebElement element) {
		int no_ErrorMsg = driver.findElements(By.tagName("ng-message")).size();
		if(no_ErrorMsg>0 && error_Msg.isDisplayed()) {
			Failed(true,"Some required field are not filled or invelid data");
		}
		else if(element.isEnabled()) {
			ClickElement(btn_Continue);
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
	
	
	public void petDetails_Dog(String AnimalType, String Name, String Gender, String Neutured,String Injured,String Breed,
			String Br_Name_1,String Br_Name_2, String Birthdate ) throws InterruptedException {
		if (isElemntExists(3,"Xpath",Locators.AcceptCookies)) {
			acceptCookies.click();
		}
		SelectAPet(AnimalType);
		WaitForPresenceOfElement(50,"xpath", Locators.Pet_Name);
		SendKey(pet_Name,Name);
		selectGender(Gender);
		selectNeutured(Neutured);
		selectInjured(Injured);
		enterBreedName(Breed,Br_Name_1,Br_Name_2);
		SelectBirthday(Birthdate);
		ClickElement(assumption_1);
		ClickElement(assumption_2);
		ClickElement(assumption_3);
		ClickElement(assumption_4);
		ClickElement(assumption_5);
		
		clickContinueBtn(btn_Continue);
		Passed(true, "All get quote details filled" );
		
	}
	
	public void petDetails_Cat(String AnimalType, String Name, String Gender, String Neutured,String Injured,
			String BR_Name,String BirthDay)throws InterruptedException {
		if (isElemntExists(3,"Xpath",Locators.AcceptCookies)) {
			acceptCookies.click();
		}
		SelectAPet(AnimalType);
		WaitForPresenceOfElement(50,"xpath", Locators.Pet_Name);
		SendKey(pet_Name,Name);
		selectGender(Gender);
		selectNeutured(Neutured);
		selectInjured(Injured);
		SendKey(breed_Name_Cat,BR_Name);
		SelectBirthday(BirthDay);
		
		clickContinueBtn(btn_Continue);
		Passed(true, "All get quote details filled" );
	}
	
	public void petDetails_Rabbit(String AnimalType, String Name, String Gender, String Neutured,String Injured,
			String BR_Name,String BirthDay)throws InterruptedException {
		if (isElemntExists(3,"Xpath",Locators.AcceptCookies)) {
			acceptCookies.click();
		}
		SelectAPet(AnimalType);
		WaitForPresenceOfElement(50,"xpath", Locators.Pet_Name);
		SendKey(pet_Name,Name);
		selectGender(Gender);
		selectNeutured(Neutured);
		selectInjured(Injured);
		SendKey(breed_Name_Cat,BR_Name);
		SelectBirthday(BirthDay);
		
		clickContinueBtn(btn_Continue);
		Passed(true, "All get quote details filled" );
	}
	
	
}
