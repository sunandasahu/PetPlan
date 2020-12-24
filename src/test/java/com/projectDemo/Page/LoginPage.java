package com.projectDemo.Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.projectDemo.Utility.BaseClass;

public class LoginPage extends BaseClass{
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//span[contains(text(),'Username')])[2]")
	private WebElement userName;
	
	@FindBy(xpath="(//span[contains(text(),'Password')])[2]")
	private WebElement password;
	
	@FindBy(xpath="//input[@name='Submit']")
	private WebElement loginBtn;
	
	public void enterUserName(String uname) {
		userName.clear();
		userName.sendKeys("uname");
	}
	
	public void enterPassword(String pwd) {
		password.clear();
		password.sendKeys("pwd");
	}
	
	public void clickonLoginBtn() {
		loginBtn.click();
	}
	
	

}
