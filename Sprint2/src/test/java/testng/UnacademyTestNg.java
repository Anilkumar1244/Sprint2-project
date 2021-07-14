package testng;

import org.testng.annotations.Test;

import library.ExcelUtility;
import pagefactory.LoginPageFactrory;
import pagefactory.SigninPageFactory;
import stepdefinitions.BaseClass;

//import stepdefinitions.LoginStepDefinition;

//import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;


public class UnacademyTestNg {

	public WebDriver driver;
	ExcelUtility excelutil=new ExcelUtility();
	LoginPageFactrory loginwebelement;

	SigninPageFactory  signinwebelement;
	InputStream reader;
	Properties prop;
	InputStream input;

	@DataProvider(name ="verifySignin")
	public Object[][] excelDP1() throws IOException
	{
		prop = new Properties();
		input= new FileInputStream("C:/Users/Hp/git/Sprint2-project/Sprint2/src/test/resources/config.properties");
		prop.load(input);
		//We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
		Object[][] arrObj =excelutil.getExcelData(prop.getProperty("excelsheetpath"),prop.getProperty("sheetname"));
		return arrObj;
		//prop.load(input);
	}


	@Test(priority = 1,dataProvider = "verifySignin")
	public void VerifySignin(String number,String name,String email) throws  InterruptedException 
	{
		String exp="Unacademy - India's Largest Online Learning Platform for UPSC CSE, JEE, NEET, CBSE and 35+ exams";
		Assert.assertEquals(driver.getTitle(), exp);
		System.out.println("user is on home page");

		signinwebelement.ClickOnLogin();
		signinwebelement.ClickONCreateAcc();
		signinwebelement.EnterMobileno(number);
		signinwebelement.clickOnContinue();
		signinwebelement.EnterOtp();
		signinwebelement.EnterNameField(name);
		signinwebelement.EnterEmailField(email);
		signinwebelement.clickOnSubmit();		
	}

	@Test(priority = 2,dataProvider = "verifySignin")
	public void VerifyLogin(String Phonenumber,String name,String email) throws InterruptedException {
		String exp="Unacademy - India's Largest Online Learning Platform for UPSC CSE, JEE, NEET, CBSE and 35+ exams";
		Assert.assertEquals(driver.getTitle(), exp);
		System.out.println("user is on home page");
		loginwebelement.Login();
		loginwebelement.ValidPhoneNumber(Phonenumber);
		loginwebelement.LoginWithPhoneNumberButton();
		loginwebelement.getOtpPage();
		loginwebelement.EnterOtp();
		loginwebelement.EnterNameField(name);
		loginwebelement.EnterEmailField(email);
		loginwebelement.VerifytOtp();		
	}

	@DataProvider(name ="InvalidMobileNumber")
	public Object[][] excelDP2() throws IOException
	{
		prop = new Properties();
		input= new FileInputStream("C:/Users/Hp/git/Sprint2-project/Sprint2/src/test/resources/config.properties");
		prop.load(input);
		//We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
		Object[][] arrObj =excelutil.getExcelData(prop.getProperty("excelsheetpath"),prop.getProperty("sheetname1"));
		return arrObj;
		//prop.load(input);
	}

	@Test(priority = 3,dataProvider = "InvalidMobileNumber")
	public void VerifyInvalidPhoneNumber(String Invalidphonenumber) throws InterruptedException
	{
		String exp="Unacademy - India's Largest Online Learning Platform for UPSC CSE, JEE, NEET, CBSE and 35+ exams";
		Assert.assertEquals(driver.getTitle(), exp);
		System.out.println("user is on home page");
		loginwebelement.Login();
		loginwebelement.InvalidPhoneNumber(Invalidphonenumber);
		loginwebelement.LoginWithPhoneNumberButton();
		loginwebelement.ErrorMessage();
	}

	@DataProvider(name ="InvalidEmail")
	public Object[][] excelDP3() throws IOException
	{
		prop = new Properties();
		input= new FileInputStream("C:/Users/Hp/git/Sprint2-project/Sprint2/src/test/resources/config.properties");
		prop.load(input);
		//We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
		Object[][] arrObj =excelutil.getExcelData(prop.getProperty("excelsheetpath"),prop.getProperty("sheetname1"));
		return arrObj;
		//prop.load(input);
	}

	@Test(priority = 4,dataProvider = "InvalidEmail")
	public void VerifyInvalidEmailId(String Invalidemailid) throws InterruptedException
	{
		String exp="Unacademy - India's Largest Online Learning Platform for UPSC CSE, JEE, NEET, CBSE and 35+ exams";
		Assert.assertEquals(driver.getTitle(), exp);
		System.out.println("user is on home page");
		loginwebelement.Login();
		loginwebelement.ContinueWithEmail();
		loginwebelement.InValidEmail(Invalidemailid);
		loginwebelement.LoginwithValidEmailButton();
		loginwebelement.EmailErrorMessage();
	}



	@BeforeMethod
	public void LaunchApplication() throws IOException 
	{  
		prop = new Properties();

		input= new FileInputStream("C:/Users/Hp/git/Sprint2-project/Sprint2/src/test/resources/config.properties");
		prop.load(input);
		String baseUrl=prop.getProperty("baseUrl");
		String browser = prop.getProperty("browser");
		driver = BaseClass.launchApplication(browser, baseUrl);
		loginwebelement=new LoginPageFactrory(driver);
		signinwebelement=new  SigninPageFactory(driver);

	}



	@AfterMethod
	public void Close() 
	{
		driver.quit();
	}
}


