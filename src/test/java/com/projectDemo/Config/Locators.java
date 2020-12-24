package com.projectDemo.Config;

public interface Locators {
	
	//GET Quote Page
	//AnimalType_Dog
	public static final String Quote_Dog = "//span[@id='petTypeDogLabel[0]']";
	
	public static final String Pet_Name = "//input[@id=\"name[0]\"]";
	public static final String Gender_Male="//span[@id='MaleLabel[0]']";
	public static final String Gender_Female="//span[@id='FemaleLabel[0]']";
	public static final String Neutured_Yes="(//span[@id='YesLabel[0]'])[1]";
	public static final String Neutured_No="(//span[@id='NoLabel[0]'])[1]";
	public static final String Injured_Yes="(//span[@id='YesLabel[0]'])[2]";
	public static final String Injured_No="(//span[@id='NoLabel[0]'])[2]";
	public static final String Single_Breed = "//span[@id='SingleBreedLabel[0]']";
	public static final String Mixed_Breed = "//span[@id='MultiBreedLabel[0]']";
	public static final String Single_Breed_Name_1 = "//input[@id='breedOne[0]']";//input[@id='breed[0]']
	public static final String Mixed_Breed_Name_1 = "//input[@id='b1MultiBreed[0]']";//input[@id='breed[0]']
	public static final String Breed_Name_2 = "//input[@id='b2MultiBreed[0]']";
	
	public static final String Select_Day="//select[@id='dateOfBirth[0]relday']";
	public static final String Select_Month="//select[@id='dateOfBirth[0]relmonth']";
	public static final String Select_Year="//select[@id='dateOfBirth[0]relyear']";
	public static final String AcceptCookies ="//*[@id='onetrust-accept-btn-handler']";
	public static final String Assumption_1="//span[@id='behaviourLabel[0]']";
	public static final String Assumption_2="//span[@id='hasAttackedLabel[0]']";
	public static final String Assumption_3="//span[@id='isGuardDogLabel[0]']";
	public static final String Assumption_4="//span[@id='livesWithAlcoholLabel[0]']";
	public static final String Assumption_5="//span[@id='takenToWorkLabel[0]']";
	
	public static final String Ins_Start_Day="//select[@id='startDate[]relday']";
	public static final String Ins_Start_Month="//select[@id='startDate[]relmonth']";
	public static final String Ins_Start_Year="//select[@id='startDate[]relyear']";
	
	public static final String Error_Msg="//*[@ng-message=\"required\" or @ng-message=\"invalid\" ]";
	public static final String Btn_Continue="//button[contains(text(),'Continue')]";
	//AnimalType_CAT
	public static final String Quote_Cat = "//span[@id='petTypeCatLabel[0]']";
	public static final String Breed_Name_Cat="//input[@id='breed[0]']";
	// AnimalType_Rabbit
	public static final String Quote_Rabbit = "//span[@id='petTypeRabbitLabel[0]']";
	
	//Your Details Page
	public static final String Your_Details="//h1[contains(text(),'Your details')]";
	public static final String Select_Title="//select[@id='title']";
	public static final String FirstName="//input[@id='firstName']";
	public static final String SurName="//input[@id='lastName']";
	public static final String Select_BDate="//select[@id='dateOfBirth[]relday']";
	public static final String Select_BMonth="//select[@id='dateOfBirth[]relmonth']";
	public static final String Select_BYear="//select[@id='dateOfBirth[]relyear']";
	public static final String Contact_No="//input[@id='contactNumber']";
	public static final String Email_ID="//input[@id='email']";
	public static final String Post_Code="//input[@id='postCode']";
	public static final String Find_Address="//*[contains(text(),'Find address')]";
	public static final String Select_Address="//select[@id='addressList']";
	public static final String Check_Box="//span[@class='ng-binding']";
	public static final String Continue_Btn="//button[@class='btn-primary']";
	public static final String Error_Msg2="//*[@ng-message=\"required\" or @ng-message=\"pattern\" ]";
	
	//Quote page
	
	public final String Btn_Yes="//button[@id='coverEssentialBtn']";
}
