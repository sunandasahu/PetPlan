package com.projectDemo.ExcelReader;

import java.util.Hashtable;

import com.projectDemo.Config.ConfigDetails;

public class ExcelOperation  {
	static Xls_Reader reader =new Xls_Reader(ConfigDetails.ExcelPath);

	public static boolean isExecutable(String TestCaseName) {
		int rowNumber = reader.getRowCount("TestCase");
		if (rowNumber==0) {
			System.out.println("FAIL---Beause there is now row in the sheet TestCase");
		}
		boolean truefalse = false;
		for (int i =1; i<=rowNumber; i++) {
			if ((reader.getCellData("TestCase", 0,i)).equalsIgnoreCase(TestCaseName)) {
				String YN = reader.getCellData("TestCase", 1, i);
				if (YN.equalsIgnoreCase("Y")) {
					truefalse =true;
					break;
				}
			}
		}	
		return truefalse;
	}
	
	public static Object[][] FetchTestCaseDetails(String SheetName, String TestCaseName) {
		int rowNumber = reader.getRowCount(SheetName);
		if (rowNumber==0) {
			System.out.println("FAIL---Beause there is now row in the sheet "+SheetName);
		}
		int startingrow =1;
		int endrow = rowNumber;
		int MatchedTCRowStart = 0 ;
		int tcCount =0;
		for (int i=startingrow ;i<=endrow;i++) {
			String TCCellValue = reader.getCellData(SheetName, 0, i);
			if (TCCellValue.equalsIgnoreCase(TestCaseName)) {
				if (tcCount==0) {
					MatchedTCRowStart = i ; 
					System.out.println("Matching test case data found from row number ="+MatchedTCRowStart);
				}
				tcCount =tcCount+1;	
			}
		}
		int MatchedTCRowEnd = MatchedTCRowStart +tcCount-1 ;
		System.out.println("Matching test case data found until row number ="+MatchedTCRowEnd);
		//=============================================
	    //Hashtable , int
		Object[][] data = new Object[tcCount][1];
		int ColumnCount = reader.getColumnCount(SheetName) ;
		int datarow= 0 ;
		for (int rowstart =MatchedTCRowStart;rowstart <=MatchedTCRowEnd ;rowstart++ )
		{
			Hashtable<String, String> table = null;
			table =new Hashtable<String, String> ();
			for(int col=1;col<=ColumnCount;col++) {
				String Key = reader.getCellData(SheetName, col, MatchedTCRowStart-1);
				Key = Key.replace("\\r\\n", "");
				String Val = reader.getCellData(SheetName, col, rowstart);
				table.put(Key, Val);
			}
			data[datarow][0] = table;
			datarow =datarow+1;
		}
		return data ;
	}
}
