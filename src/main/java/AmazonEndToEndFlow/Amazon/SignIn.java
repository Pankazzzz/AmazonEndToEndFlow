package AmazonEndToEndFlow.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilityClasses.Utilities;

public class SignIn extends Utilities{

WebDriver driver;
	
	public SignIn(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	

//	waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
//	driver.findElement(By.xpath("//input[@type='password']")).sendKeys("9022575112@");
//	driver.findElement(By.cssSelector("[type='submit']")).click();
	
	@FindBy(xpath = "//*[@type='email']")
	WebElement emailLogin;
	
	@FindBy(className = "a-button-input")
	WebElement emailLoginButton;
	
	@FindBy(css="[type='submit']")
	WebElement submit;
	
	By passElement = By.xpath("//input[@type='password']");
		
	
	public void enterEmail(String email)
	{
		visibilityOfElement(emailLogin);
		emailLogin.sendKeys(email);
	}
	
	public void clickOnEmailSubmit()
	{
		emailLoginButton.click();
	}
	
	
	public void enterPassword(String password)
	{	
		visibilityOfElementLocatedBy(passElement);
		driver.findElement(passElement).sendKeys(password);
	}
	
	public void clickOnLogin()
	{
		submit.click();
	}
}
