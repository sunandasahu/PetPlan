package com.projectDemo.Test;

import org.testng.annotations.Test;

import com.projectDemo.ExcelReader.ExcelOperation;

import com.projectDemo.Page.PetDetailsPage;
import com.projectDemo.Page.YourDetailsPage;
import com.projectDemo.Utility.BaseClass;
import com.relevantcodes.extentreports.LogStatus;

import java.util.Hashtable;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterTest;

public class TC001_GetQuoteAnimal_Dog extends BaseClass {
	public String TestCaseName = "TC001_GetQuoteAnimal_Dog";
	public String SheetName = "PetDetails";

	@DataProvider
	public Object[][] CollectData() {
		Object[][] data =null;
		try {
			data = ExcelOperation.FetchTestCaseDetails(SheetName, TestCaseName);
		}catch (Exception E) {
			Failed(false,"Error in Excel sheet reading");
		}
		return data;
	}


	@Test(dataProvider = "CollectData")
	public void TestCase(Hashtable <String,String> data) throws Exception {
		test = report.startTest(TestCaseName);
		Info(false, "Execution started for Test Case = "+TestCaseName);
		if ((!ExcelOperation.isExecutable(TestCaseName)) || data.get("Execution").equalsIgnoreCase("N")) {
			throw new SkipException (TestCaseName+"'s execution control has been made to no in excel sheet");
		}
		//---------------------------------
		Init();
		//---------------------------------
		PetDetailsPage petDetailsPage = new PetDetailsPage();
		YourDetailsPage yourDetailsPage=new YourDetailsPage();
		 PageFactory.initElements(driver, petDetailsPage);
		 PageFactory.initElements(driver, yourDetailsPage);
		//----------------------------------
		 petDetailsPage.petDetails_Dog(data.get("AnimalType"), data.get("Name"), data.get("Gender"), data.get("Neutered"),
				data.get("Injury"), data.get("BreedType"),data.get("Breed_Name_1"),data.get("Breed_Name_2"),
				data.get("PetBirthDate"));
		 
			
			yourDetailsPage.fetchYourDetails(data.get("Title"), data.get("FirstName"), data.get("SurName"), data.get("CustomerBirthDate"),
					data.get("ContactNumber"), data.get("Email_ID"), data.get("Postal_Code"));
		
		
		
		
		driver.close();

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE){
			System.out.println("Test failed"+ result.getThrowable());
			test.log(LogStatus.FAIL,"Execution completed -- Test failed"+ result.getThrowable());
		}else if(result.getStatus()==ITestResult.SKIP) {
			System.out.println("Test failed"+ result.getThrowable());
			test.log(LogStatus.SKIP, "Test skipped"+ result.getThrowable());
		}else {
			test.log(LogStatus.PASS, "Execution completed -- Test Passed-> "+ TestCaseName);
		}	
	}

	@AfterTest
	public void afterTest() {
		if(report!=null) {
			report.endTest(test);
			report.flush();
		}
		try {
			driver.quit();
			System.out.println("all driver quit");
		}catch(Exception e) {
			System.out.println("All browser process already closed");
		}
	}

}
