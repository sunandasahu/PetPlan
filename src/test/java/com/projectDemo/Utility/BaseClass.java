package com.projectDemo.Utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import com.projectDemo.Config.ConfigDetails;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	public static ExtentReports report = new ExtentReports(ConfigDetails.ResultFolder+"ExtentReportResults.html"+timeStamp+".html",true);
	public static ExtentTest test;
	public static WebDriver driver;
	public static WebDriverWait wait;

	public String takeScreenShot() {		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(ConfigDetails.ScreenShotFolder + System.currentTimeMillis()+ ".png");
		String errflpath = Dest.getAbsolutePath();
		try {
			FileUtils.copyFile(scrFile, Dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.log(LogStatus.INFO, test.addScreenCapture(errflpath));
		return errflpath;
	}

	public void Passed(boolean ScreenShot, String Comments)
	{
		System.out.println(Comments);
		test.log(LogStatus.PASS, Comments);
		if (ScreenShot) {
			takeScreenShot();
		}
	}

	public void Failed(boolean ScreenShot, String Comments)
	{
			System.out.println(Comments);
			test.log(LogStatus.FAIL, Comments);
			if (ScreenShot==true) {	
				try {
					takeScreenShot();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			try {
				throw new Exception(Comments);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void Info(boolean ScreenShot, String Comments)
	{
		System.out.println(Comments);
		test.log(LogStatus.INFO, Comments);
		if (ScreenShot==true) {
			takeScreenShot();
		}
	}
	
	public void  SendKey(WebElement Element,String Value) {	
		if (Value==null || Value=="" || Value.isEmpty()) {
			Failed(false, "value is empty in method SendKey");
		}
		WaitForVisibiltyOfElement(20,Element);
		if (Element.isEnabled() && Element.isDisplayed()) {
			Element.clear();
			Element.sendKeys(Value);
		}else {
			Failed(true, Element.getText() +" is not displayed, or inactive");
		}
		Passed(false,"Value entered "+Value);
	}

	public void ClickElement(WebElement element) {
		WaitForVisibiltyOfElement(30,element);
		if (element.isDisplayed()) {
			element.click();
			Info(false, element.getText()+" Clicked");
		}else {
			Failed(false, element.getText()+ " not visible");
		}

	}
	public void getJSDropdown(String dropDown, String elementID)throws Exception{

	     JavascriptExecutor executor = (JavascriptExecutor)driver;
	     String dropdownScript = "var select = window.document.getElementById('" + 
	             dropDown +
	             "'); for(var i = 0; i < select.options.length; i++){if(select.options[i].text == '" +
	             elementID +
	             "'){ select.options[i].selected = true; } }";

	     Thread.sleep(2000);
	     executor.executeScript(dropdownScript);
	     Thread.sleep(2000);

	     String clickScript = "if ("+"\"createEvent\""+" in document) {var evt = document.createEvent("+"\"HTMLEvents\""+");     evt.initEvent("+"\"change\""+", false, true); " + dropDown + ".dispatchEvent(evt); } else " + dropDown + ".fireEvent("+"\"onchange\""+");";

	     executor.executeScript(clickScript);

	 }
	
	public void highLighterMethod(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');", element);
	}
	
	//To scroll down the web page by the visibility of the element.
	public void scrollByVisibilityOfElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if(element.isDisplayed()) {
		js.executeScript("arguments[0].scrollIntoView();", element);
		Passed(false,"scroll down to the element");
		}
		else {
			Failed(false,"element is not present");
		}
	}

	public void Skipped(String Comments)
	{
		System.out.println(Comments);
		test.log(LogStatus.INFO, Comments);
		try {
			throw new SkipException (Comments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isElemntExists(int TimeOutSeconds, String LocatorType, String Locator) {
		driver.manage().timeouts().implicitlyWait(TimeOutSeconds, TimeUnit.SECONDS);
		boolean found = false;
		if  (LocatorType.equalsIgnoreCase("XPATH")) {
			if (driver.findElements(By.xpath(Locator)).size() > 0) {
				found = true;
			}
		}else if (LocatorType.equalsIgnoreCase("ID")) {
			if (driver.findElements(By.id(Locator)).size() > 0) {
				found = true;
			}
		}else if  (LocatorType.equalsIgnoreCase("NAME")) {
			if (driver.findElements(By.name(Locator)).size() > 0) {
				found = true;
			}
		}
		driver.manage().timeouts().implicitlyWait(ConfigDetails.ImpTimeOut, TimeUnit.SECONDS);
		return found;
	}
	
	public void Init() {
		
		try { //Close all open chrome instances opened
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /T");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
			wait(3);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		String browsername=ConfigDetails.BrowserName;
		if(browsername.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-infobars");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
			driver = new ChromeDriver(options);
		}
		else if (browsername.equalsIgnoreCase("opera"))
		{
			WebDriverManager.operadriver().setup();
			driver=new OperaDriver();
		}
		else if(browsername.equalsIgnoreCase("FireFox"))
		{
			System.setProperty("webdriver.firefox.bin","C:\\Users\\sunan\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
			WebDriverManager.firefoxdriver().setup();
//			File pathToBinary = new File("C:\\Program Files\\Mozilla Firefox15\\Firefox.exe");
//			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
//			FirefoxProfile firefoxProfile = new FirefoxProfile();
//			FirefoxDriver _driver = new FirefoxDriver(ffBinary,firefoxProfile);
			driver=new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(ConfigDetails.ImpTimeOut,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(ConfigDetails.Url);
		Info(false, "Browser Initialized = "+ConfigDetails.BrowserName);
		Info(false, "URL Opened = "+ConfigDetails.Url);
	}

	public void selectByVisibleText(WebElement element, String Value) {
		if (Value==null || Value=="" || Value.isEmpty()) {
			Failed(false, "value ie empty in method selectByVisibleText");
		}
		WaitForVisibiltyOfElement(20,element);
		try {
			if (element.isDisplayed()) {
				Select select=new Select(element);
				select.selectByVisibleText(Value);
				Info(false,Value+" is selected");
			}else {
				Failed(false, element.getText()+"is not visible");
			}
		} catch (Exception e) {
			Failed(false,Value+" is not selected");
		}

	}


	public void selectByIndex(WebElement element) {
		Select select=new Select(element);
		select.selectByVisibleText("index");
	}

	public void swithToAnotherWindow(int Window_num)
	{
		List<String> windowlist=null;
		Set<String> windows=driver.getWindowHandles();
		windowlist=new ArrayList<String>(windows);
		String currentWindow=driver.getWindowHandle();
		if(!currentWindow.equalsIgnoreCase(windowlist.get(Window_num - 1)))
		{
			driver.switchTo().window(windowlist.get(Window_num - 1));
		}

	}
	public void checkBox(String element)
	{
		WebElement checkBox=driver.findElement(By.xpath(element));
		checkBox.click();
	}
	public void frame(int index)
	{
		driver.switchTo().frame(index);

	}
	public void frame(String name)
	{
		driver.switchTo().frame(name);

	}
	public void frame(WebElement element)
	{
		driver.switchTo().frame(element);
	}

	public void closeBrowser(WebDriver driver)
	{
		driver.quit();
	}
	public void sendkeys(WebElement element,int timeout,String value)
	{
		new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}
	public void click(WebElement element,int timeout)
	{
		new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}




	/**
	 * This function click an element using java script
	 *
	 * @param driver
	 *            WebDriver reference.
	 * @param element
	 *            Element which are going to click.
	 *
	 * @throws Exception
	 *             if Element is not found exception occurred
	 */

	public void Click_JavaScript(WebElement element) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			Failed(false,"TimeoutException in commonWaitToFindElement for :"+e.getMessage());
		}
	}

	/**
	 * @param driver
	 *            WebDriver reference
	 * @param timeOutInSeconds
	 *            integer type waiting time in second after that time out of
	 *            waiting
	 *
	 * @param element
	 *            Element which is going to found
	 * @return boolean Status of WebDriver element i.e. found or not
	 * @throws TimeoutException
	 *             if time out exception occurred
	 */
	public boolean WaitForVisibiltyOfElement(int timeOutInSeconds, WebElement element) throws TimeoutException {
		wait(1);
		boolean result = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			result = true;
		} catch (TimeoutException e) {
			Failed(false,
					"TimeoutException in commonWaitToFindElement for :"
							+ e.getMessage());
		}
		return result;
	}
	/**
	 * This function will wait for to load page completely
	 *
	 * @param driver
	 *            instance of Web driver
	 *
	 */
	public synchronized void WaitForPageToLoad() {
		String str = null;
		try {
			str = (String) ((JavascriptExecutor) driver)
					.executeScript("return document.readyState");
			System.out.println(str);
		} catch (Exception e) {
			// it's need when JS isn't worked
			WaitForPageToLoad();
			return;
		}
		System.out.println("ttt " + str);
		while (!str.equals("complete")) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			str = (String) ((JavascriptExecutor) driver)
					.executeScript("return document.readyState");
		}
	} 

	/**
	 * This function return current date time.
	 *
	 * @return String of current data time
	 *
	 */

	public String getCurrentDateAndTime() {
		DateFormat format = new SimpleDateFormat("dd.MM.yy_hh.mm.ss");
		String dtTime = format.format(getDate());
		return dtTime;
	}
	/**
	 * This function return date.
	 *
	 * @return
	 */
	public Date getDate() {
		return new Date();
	}
	/**
	 * @param driver
	 *            WebDriver reference
	 * @param timeOutInSeconds
	 *            integer type waiting time in second after that time out of
	 *            waiting
	 * @param findElementBy
	 *            String type expression of finding the element using :
	 *            xpath/ID/CSS path
	 * @param expr
	 *            String type expression of xpath/ID/CSS path
	 * @return WebDriver WebElement
	 * @throws TimeoutException
	 *             if time out exception occurred
	 */
	public WebElement WaitToFindElementPresent_GetElement(int timeOutInSeconds, String findElementBy,String expr) throws TimeoutException 
	{
		WebElement element = null;
		try {
			// Element is Click able - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			switch(findElementBy){
			   case "xpath" :
				   element = wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(expr)));
			   case "cssSelector" :
				   element = wait.until(ExpectedConditions.elementToBeClickable(By
							.cssSelector(expr)));
			   case "className":
				   element = wait.until(ExpectedConditions.elementToBeClickable(By
							.className(expr)));
			   case "linkText" :
				   element = wait.until(ExpectedConditions.elementToBeClickable(By
						.linkText(expr)));
			   case "id":
				   element = wait.until(ExpectedConditions.elementToBeClickable(By
							.id(expr)));
			   default : 
			      Info(false, "WaitToFindElementPresent_GetElement methods input parameter error");
			}
		} catch (NoSuchElementException e) {
			Failed(false,
					"NoSuchElementException in commonWaitToFindElement_GetElement for :"
							+ e.getMessage());
		} catch (TimeoutException e) {
			Failed(false,
					"TimeoutException in commonWaitToFindElement_GetElement for :"
							+ e.getMessage());
		} catch (Exception e) {
			Failed(false,
					"Exception in commonWaitToFindElement_GetElement for :"
							+ e.getMessage());
		}
		return element;
	}

	/**
	 * @param driver
	 *            WebDriver reference
	 * @param numberoftimes
	 *            integer type containing no of time scroll down needs to be
	 *            pressed
	 * @param delay
	 *            integer type containing the time to wait after performing
	 *            scroll down
	 */
	public void scrollDown( Integer numberoftimes,
			Integer delay) {

		try {
			for (int i = 0; i <= numberoftimes; i++) {
				driver.findElement(By.tagName("body"))
				.sendKeys(Keys.ARROW_DOWN);
			}
			Thread.sleep(delay);

		} catch (Exception e) {
			Failed(false,
					"Exception in scrollDown:" + e.getMessage());
		}
	}

	/**
	 * @param driver
	 *            WebDriver reference
	 * @param timeOutInSeconds
	 *            integer type waiting time in second after that time out of
	 *            waiting
	 * @param findElementBy
	 *            String type expression of finding the element using :
	 *            xpath/ID/CSS path
	 * @param expr
	 *            String type expression of xpath/ID/CSS path
	 * @return boolean Status of WebDriver element i.e. found or not
	 * @throws TimeoutException
	 *             if time out exception occurred
	 */
	public boolean WaitForPresenceOfElement(int timeOutInSeconds, String findElementBy, String expr)
			throws TimeoutException {
		boolean result = false;
		try {
			// Element is Click able - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement element = null;
			if (findElementBy.equals("xpath")) {
				element = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(expr)));
			} else if (findElementBy.equals("id")) {
				element = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(expr)));
			} else if (findElementBy.equals("link text")) {
				element = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.linkText(expr)));
			}

			if (element != null) {
				result = true;
			} else {
				result = false;
			}
		} catch (TimeoutException e) {
			Failed(false,
					"TimeoutException in commonWaitForPresenceOfElement for :"
							+ e.getMessage());
		}
		return result;
	}


	public static String fn_GetTimeStamp(){
		DateFormat DF=DateFormat.getDateTimeInstance();
		Date dte=new Date();
		String DateValue=DF.format(dte);
		DateValue=DateValue.replaceAll(":", "_");
		DateValue=DateValue.replaceAll(",", "");
		return DateValue;
	}

	public static void wait(int second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}












}
