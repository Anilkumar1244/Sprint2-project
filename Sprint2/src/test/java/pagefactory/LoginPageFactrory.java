package pagefactory;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
//BasicConfigurator.configure();
public class LoginPageFactrory {
	//BasicConfigurator.configure();
	public WebDriver driver;

	@CacheLookup
	@FindBy(xpath="//button[@aria-label='Login']") WebElement Login;
	@FindBy(xpath="//input[@placeholder='Enter your mobile number']") WebElement ValidPhoneNumber;
	@FindBy(xpath="//div//button[@class='Button__StyledButton-dg3jck-0 bwPiUm']") WebElement LoginWithPhoneNumberButton;
	@FindBy(xpath = "//h2[@class='H2-s1k28w-0 EnterOtpStep__Header-sc-73b7t6-10 WOSgl kQEuid']") WebElement getOtpPage;
	@FindBy(xpath="//input[@placeholder='One time password']") WebElement EnterOtp;
	@FindBy(xpath="//button[@aria-label='Verify OTP']") WebElement VerifytOtp;
	@FindBy(xpath="//input[@placeholder='Enter your mobile number']") WebElement InvalidPhoneNumber;
	@FindBy(xpath="//P[@class='TextField__StyledP2-sc-17ki4o0-0 fSmstq']") WebElement ErrorMessage;
	@FindBy(xpath="//h6[@class='H6-sc-1gn2suh-0 EnterNumberStep__StyledH6-sc-17qxvlo-3 dqwUfd cVVQLV']") WebElement ContinueWithEmail;
	@FindBy(xpath="//button[@class='Button__StyledButton-dg3jck-0 bwPiUm EnterEmailStep__LoginButton-sc-1txz5a5-5 cpauKv']") WebElement LoginwithEmailButton;
	@FindBy(xpath="//input[@placeholder='Email address']") WebElement InValidEmail;
	@FindBy(xpath="//P[@class='TextField__StyledP2-sc-17ki4o0-0 fSmstq']") WebElement EmailErrorMessage;
	@FindBy(xpath ="//span[text()='United States']") WebElement selectcountry;
	@FindBy(xpath ="//img[@class='CountryMenu__StyledImg-sc-7z48zf-2 fLndBE']") WebElement countrydropdown;
	@FindBy(xpath = "//input[@placeholder='Name']") WebElement Namefield;
	@FindBy(xpath = "//input[@placeholder='Email address']") WebElement emailidfield;


	public static final String ACCOUNT_SID="AC89fd00d73c446f91036c3aca25fde506";
	public static final String AUTH_TOKEN="72dc4ef761b32ba8f96304b87465e6c6";

	public LoginPageFactrory(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void Login() throws InterruptedException
	{
		Login.click();
		Thread.sleep(5000);
	}
	public void ValidPhoneNumber(String ValidPhonenumner) throws InterruptedException
	{
		countrydropdown.click();
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView();",selectcountry);
		Thread.sleep(1000);
		selectcountry.click();
		Thread.sleep(2000);
		ValidPhoneNumber.sendKeys(ValidPhonenumner);
	}
	public void LoginWithPhoneNumberButton() throws InterruptedException
	{
		LoginWithPhoneNumberButton.click();
		Thread.sleep(5000);
	}
	public void getOtpPage()
	{
		String Actual=getOtpPage.getText();
		String Exp="Enter OTP";
		Assert.assertEquals(Actual, Exp);

	}

	public void EnterNameField(String name) throws InterruptedException 
	{
		Thread.sleep(2000);
		Namefield.sendKeys(name);	
	}
	public void EnterEmailField(String email) throws InterruptedException 
	{
		Thread.sleep(2000);
		emailidfield.sendKeys(email);	
	}
	public void EnterOtp() throws InterruptedException
	{
		Thread.sleep(6000);
		Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
		String smsBody=getMessage();
		String OTPNumber=smsBody.replaceAll("[^-?0-9]+", " ");
		EnterOtp.sendKeys(OTPNumber);
		Thread.sleep(6000);
	}
	public void VerifytOtp() throws InterruptedException
	{
		VerifytOtp.click();
		Thread.sleep(5000);
	}

	public void InvalidPhoneNumber(String Invalidphonenumber) throws InterruptedException
	{
		countrydropdown.click();
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView();",selectcountry);
		Thread.sleep(1000);
		selectcountry.click();
		Thread.sleep(2000);
		InvalidPhoneNumber.sendKeys(Invalidphonenumber);
	}
	public void ErrorMessage()
	{
		String Actual=ErrorMessage.getText();
		String Exp="Failed to send sms to the number";
		Assert.assertEquals(Actual, Exp);	
	}
	public void ContinueWithEmail() throws InterruptedException
	{
		ContinueWithEmail.click();
		Thread.sleep(5000);	
	}

	public void LoginwithValidEmailButton() throws InterruptedException
	{
		LoginwithEmailButton.click();
		Thread.sleep(5000);		
	}
	public void InValidEmail(String Invalidemailid) throws InterruptedException
	{
		InValidEmail.sendKeys(Invalidemailid);
		Thread.sleep(5000);		
	}
	public void EmailErrorMessage()
	{
		String Actual=EmailErrorMessage.getText();
		String Exp="This email is not registered with us";
		Assert.assertEquals(Actual, Exp);	
	}

	public static String getMessage() {
		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
				.filter(m -> m.getTo().equals("+12534657600")).map(Message::getBody).findFirst()
				.orElseThrow(IllegalStateException::new);
	}

	private static Stream<Message> getMessages() {
		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
		return StreamSupport.stream(messages.spliterator(), false);
	}
}

