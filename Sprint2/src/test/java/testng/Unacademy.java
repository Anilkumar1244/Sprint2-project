package testng;

import org.testng.annotations.Test;

import stepdefinitions.LoginStepDefinition;

import org.testng.annotations.BeforeTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class Unacademy {
 
	private static final String ValidPhonenumber = "7676788030";
	private static final String Invalidphonenumber ="0000000000";
	WebDriver driver;
	LoginStepDefinition unacad=new LoginStepDefinition();
	
  @Test
  public void LoginWithValidPhonenumber( ) throws InterruptedException {
	   
	  unacad.user_click_login_button();
	  unacad.user_enters_ValidPhonenumber_as(ValidPhonenumber);
	  unacad.user_clicks_on_Login_button();
	  unacad.user_should_be_on_OTP_page();
	  unacad.user_enter_the_Otp();
	  unacad.user_clicks_on_verify_otp_button();
	  unacad.User_should_be_on_Home_page();
  }
  
  @Test
  public void LoginWithinValidPhonenumber() throws InterruptedException {
	   
	  unacad.user_click_login_button();
	  unacad.user_enters_Invalidphonenumber(Invalidphonenumber);
	  unacad.user_clicks_on_Login_button();
	  unacad.Dispaly_the_error_message();
	  
  }
  
	@BeforeTest
  public void beforeTest() throws IOException {
		
		unacad.user_is_launch_the_application();
  }

  @AfterTest
  public void afterTest() {
	  
	  driver.close();
  }

}
