package pagefactory;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

public class SigninPageFactory {
	
	
	
	public String option= null;
	public WebDriver driver;
	@FindBy(xpath="//button[@aria-label='Login']") WebElement Login;
	@FindBy(xpath="//h6[text()='create your account']") WebElement createaccount_link;
	@FindBy(xpath="//input[@placeholder='Enter your mobile number']") WebElement mobilenumberentry;
	@FindBy(xpath = "//input[@placeholder='One time password']") WebElement otpfield;
	@FindBy(xpath = "//input[@placeholder='Name']") WebElement Namefield;
	@FindBy(xpath = "//input[@placeholder='Email address']") WebElement emailidfield;
	@FindBy(xpath = "//button[@class='Dropdown__DropDownButton-qkumcs-0 gwYbe']")WebElement stateselectionDropdown;
	@FindBy(xpath = "//button[text()='Continue']") WebElement Continuecmd;
	//@FindBy(xpath = "//button[text()='Select - State of residence']") WebElement dropdownlist;
	//@FindBy(xpath = "//span[text()='Karnataka']") WebElement stateseletion;
	@FindBy(xpath ="//button[text()='Submit']") WebElement Submitcmd;
	@FindBy(xpath ="//span[text()='United States']") WebElement selectcountry;
	@FindBy(xpath ="//img[@class='CountryMenu__StyledImg-sc-7z48zf-2 fLndBE']") WebElement countrydropdown;
	
	public static final String ACCOUNT_SID="AC4b65aae49e712b316322eeb942ae9d16";
	public static final String AUTH_TOKEN="d4a9cdd26e922c410343bcf74b182c46";

	public SigninPageFactory(WebDriver driver)
	{
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void ClickOnLogin() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//Thread.sleep(0);
		Login.click();
		Thread.sleep(0);
	}
	
	public void EnterOtp() throws InterruptedException
	{
		Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
		String smsBody=getMessage();
		String OTPNumber=smsBody.replaceAll("[^-?0-9]+", " ");
		otpfield.sendKeys(OTPNumber);;
		Thread.sleep(6000);
	}
	
    
    
    public void ClickONCreateAcc() throws InterruptedException
    {
    	Thread.sleep(2000);
    	createaccount_link.click();
    }
    public void EnterMobileno(String number) throws InterruptedException
    {
    	countrydropdown.click();
    	JavascriptExecutor je = (JavascriptExecutor) driver;
    	je.executeScript("arguments[0].scrollIntoView();",selectcountry);
    	Thread.sleep(1000);
    	selectcountry.click();
    	Thread.sleep(2000);
    	mobilenumberentry.sendKeys(number);
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
    
   /* public void Selectdropdown() throws InterruptedException 
    {
    	Thread.sleep(2000);
    	stateselectionDropdown.click();
    	JavascriptExecutor je = (JavascriptExecutor) driver;
    	je.executeScript("arguments[0].scrollIntoView();",stateseletion);
    	Thread.sleep(1000);
    	stateseletion.click();
	}*/
    
    public void clickOnContinue() throws InterruptedException 
    {
    	Thread.sleep(2000);
	    	Continuecmd.click();
	}
    
    public void clickOnSubmit() throws InterruptedException 
    {
    	Thread.sleep(2000);
	    	Submitcmd.click();
	}
    
    public static String getMessage()
    {
    	  return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
    			  .filter(m ->m.getTo().equals("+13867533857")).map(Message::getBody).findFirst()
    			  .orElseThrow(IllegalStateException::new);		             
    }
    private static Stream<Message> getMessages()
    {
    	 ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
    	 return StreamSupport.stream(messages.spliterator(), false);
    }
    
}


