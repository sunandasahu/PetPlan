package com.projectDemo.Test;

import org.testng.annotations.Test;

import com.projectDemo.ExcelReader.ExcelOperation;
import com.projectDemo.Utility.BaseClass;
import org.testng.annotations.BeforeMethod;

import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterTest;

public class TC001_AddUser extends BaseClass {
public String TestCaseName = "TC001_AddUser";
public String SheetName = "User";
	
	@DataProvider
	  public Object[][] CollectData() {
		Object[][] data =null;
		try {
		data = ExcelOperation.FetchTestCaseDetails(SheetName, TestCaseName);
		}catch (Exception E) {
			//exten report
			System.out.println("error in data reading");
		}
		return data;
	  }
 
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("b m");
  }

  
  @Test(dataProvider = "CollectData")
  public void TestCase(Hashtable <String,String> data) throws Exception {
	  test = report.startTest(TestCaseName);
	  if ((ExcelOperation.isExecutable(TestCaseName)) && data.get("Execution").equalsIgnoreCase("Y")) {
		 Skipped(TestCaseName+"'s execution control has been made to no in excel sheet");
	  }
	  Failed(false, "Test-----------------------123444");
	  
  }
  
  
  
  @AfterMethod
  public void afterMethod() {
	  System.out.println("a m");
  }


  
  @AfterTest
  public void afterTest() {
	  System.out.println(" at");
	  if(report!=null) {
		  report.endTest(test);
		  report.flush();
	  }
  }

}
