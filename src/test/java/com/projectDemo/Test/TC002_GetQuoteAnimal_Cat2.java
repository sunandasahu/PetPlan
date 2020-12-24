package com.projectDemo.Test;

import org.testng.annotations.Test;

import com.projectDemo.ExcelReader.ExcelOperation;
import com.projectDemo.Page.PetDetailsPage;
import com.projectDemo.Utility.BaseClass;
import com.relevantcodes.extentreports.LogStatus;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

public class TC002_GetQuoteAnimal_Cat2 extends BaseClass {
	public String TestCaseName = "TC002_GetQuoteAnimal_Cat2";
	public String SheetName = "PetDetails";

//	@DataProvider
//	public Object[][] CollectData() {
//		Object[][] data =null;
//		try {
//			data = ExcelOperation.FetchTestCaseDetails(SheetName, TestCaseName);
//		}catch (Exception E) {
//			Failed(false,"Error in Excel sheet reading");
//		}
//		return data;
//	}


	@Test
	public void TestCase() throws Exception {
		test = report.startTest(TestCaseName);
		Info(false, "Execution started for Test Case = "+TestCaseName);
		Init();
		//---------------------------------
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
