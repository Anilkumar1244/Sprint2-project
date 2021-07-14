package stepdefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefactory.LoginPageFactrory;
import pagefactory.SigninPageFactory;

public class LoginStepDefinition {


	WebDriver driver;
	LoginPageFactrory loginwebelement;
	SigninPageFactory  signinwebelement;
	@Before
	public void user_is_launch_the_application() throws IOException   {

		Properties prop= new Properties();
		InputStream input= new FileInputStream("C:/Users/Hp/git/Sprint2-project/Sprint2/src/test/resources/config.properties");
		prop.load(input);
		String baseUrl=prop.getProperty("baseUrl");
		String browser = prop.getProperty("browser");
		driver = BaseClass.launchApplication(browser, baseUrl);
		String log4jConfPath = "C:\\Users\\Hp\\git\\Sprint2-project\\Sprint2\\src\\test\\resources\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		loginwebelement=new LoginPageFactrory(driver);
		signinwebelement=new  SigninPageFactory(driver);
	}

	@After
	public void Stop()
	{
		driver.quit();
	}

	//Login feature
	@Given("user is on home page")
	public void user_is_on_home_page() throws IOException   {
		String exp="Unacademy - India's Largest Online Learning Platform for UPSC CSE, JEE, NEET, CBSE and 35+ exams";
		Assert.assertEquals(driver.getTitle(), exp);
		System.out.println("user is on home page");
	}

	@When("user click login button")
	public void user_click_login_button() throws InterruptedException {

		loginwebelement.Login();
		System.out.println("user click login button");

	}

	@When("user enters ValidPhonenumber as {string}")
	public void user_enters_ValidPhonenumber_as(String ValidPhonenumber) throws InterruptedException {

		loginwebelement.ValidPhoneNumber(ValidPhonenumber);
		System.out.println("user enter the ValidPhonenumber");
	}


	@When("user clicks on Login button")
	public void user_clicks_on_Login_button() throws InterruptedException {

		loginwebelement.LoginWithPhoneNumberButton();
		System.out.println("user clicks on Login button");
	}

	@Then("user should be on OTP page")
	public void user_should_be_on_OTP_page() throws InterruptedException {

		loginwebelement.getOtpPage();
		System.out.println("user should be on OTP page");
	}

	@Then("user enter the Otp")
	public void user_enter_the_Otp() throws InterruptedException  {

		loginwebelement.EnterOtp();

		System.out.println("user enter the Otp");
		Thread.sleep(5000);
	}

	@Then("user clicks on verify otp button")
	public void user_clicks_on_verify_otp_button() throws InterruptedException {

		loginwebelement.VerifytOtp();
		System.out.println("user clicks on verify otp button");
	}

	@Then("User should be on Home page")
	public void User_should_be_on_Home_page() throws InterruptedException {

		String exp="Unacademy - India's Largest Online Learning Platform for UPSC CSE, JEE, NEET, CBSE and 35+ exams";
		Assert.assertEquals(driver.getTitle(), exp);
		System.out.println("User should be on Home page");
	}


	@When("user enters Invalidphonenumber {string}")
	public void user_enters_Invalidphonenumber(String Invalidphonenumber) throws InterruptedException {

		loginwebelement.InvalidPhoneNumber(Invalidphonenumber);
		System.out.println("user enter the Invalidphonenumber");
	}

	@Then("Dispaly the error message")
	public void Dispaly_the_error_message() {

		loginwebelement.ErrorMessage();	
		System.out.println("error message is displayed");

	}

	@When("user clicks on continue with email button")
	public void user_clicks_on_continue_with_email_button() throws InterruptedException {

		loginwebelement.ContinueWithEmail();
		System.out.println("user clicks on continue with email button");
	}

	@When("user click on Login button")
	public void user_click_on_Login_button() throws InterruptedException {

		loginwebelement.LoginwithValidEmailButton();
		System.out.println("user click on Login button");
	}

	@When("user enters Invalidemailid as {string}")
	public void user_enters_Invalidemailid_as(String Invalidemailid) throws InterruptedException {

		loginwebelement.InValidEmail(Invalidemailid);
		System.out.println("user enter the Invalidemailid");
	}

	@Then("Error message should be displayed")
	public void Error_message_should_be_displayed() {
		loginwebelement.EmailErrorMessage();
		System.out.println("Error message should be displayed");
	}


	@Then("user enter the Regsitered name as {string}")
	public void user_enter_the_Regsitered_name_as(String name) throws InterruptedException {
		loginwebelement.EnterNameField(name);
		System.out.println("user enter the name");
	}

	@Then("user enter the Regsitered Email as {string}")
	public void user_enter_the_Regsitered_Email_as(String email) throws InterruptedException {
		loginwebelement.EnterEmailField(email);
		System.out.println("user enter the email");
	}


	//Sign in
	@When("user clicks login button")
	public void user_clicks_login_button() throws InterruptedException {


		signinwebelement.ClickOnLogin();
		System.out.println("user click login button");
	}


	@When("user click on create your account button")
	public void user_click_on_create_your_account_button() throws InterruptedException {
		signinwebelement.ClickONCreateAcc();
	}

	@When("user enter the Phonenumber as {string}")
	public void user_enter_the_Phonenumber_as(String number) throws InterruptedException {
		signinwebelement.EnterMobileno(number);
		System.out.println("user enter the phone number");
	}

	@When("user click on continue button")
	public void user_click_on_continue_button() throws InterruptedException {
		signinwebelement.clickOnContinue();
		System.out.println("user click on continue button");
	}

	@When("user enter the name as {string}")
	public void user_enter_the_Name_as(String name) throws InterruptedException {
		signinwebelement.EnterNameField(name);
		System.out.println("user enter the name");
	}

	@When("user enter the Email as {string}")
	public void user_enter_the_Emailid_as(String email) throws InterruptedException {
		signinwebelement.EnterEmailField(email);
		System.out.println("user enter the email");
	}

	@When("user click on submit button")
	public void user_click_on_submit_button() throws InterruptedException {
		signinwebelement.clickOnSubmit();
		System.out.println("user click the submit button");
	}

	@When("user enter Otp")
	public void user_enter_Otp() throws InterruptedException {
		signinwebelement.EnterOtp();
		System.out.println("user enter the OTP");
	}


	/*@Then("User should be on Home page")
	public void user_Should_be_on_Home_page() {
		String exp="Unacademy - India's Largest Online Learning Platform for UPSC CSE, JEE, NEET, CBSE and 35+ exams";
		Assert.assertEquals(driver.getTitle(), exp);
		System.out.println("user is on home page");
	}*/

}

