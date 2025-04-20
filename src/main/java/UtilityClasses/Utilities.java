package UtilityClasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	
	
	public WebDriver driver;
	public WebDriverWait waitDriver;
	public Actions actions;
	public JavascriptExecutor js;
	
	
	public Utilities(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver,this);
		waitDriver = new WebDriverWait(driver, Duration.ofSeconds(10));
		actions = new Actions(driver);
	}
	
	public void sleep(long i)
	{
		try {
			Thread.sleep(i);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void visibilityOfElement(WebElement element)
	{
		waitDriver.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void visibilityOfElementLocatedBy(By by)
	{
		waitDriver.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public JavascriptExecutor getJSExecutor()
	{
        js = (JavascriptExecutor) driver;
        return js;
	}
	
	public void scrollIntoView(WebElement element)
	{
		getJSExecutor().executeScript("arguments[0].scrollIntoView(true);", element);
		sleep(2000);
	}
	
	public void elementToBeClickable(WebElement element)
	{
		waitDriver.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void waitUntilElementToBeClickable(By by)
	{
		waitDriver.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public void moveToElement(WebElement element)
	{
		actions.moveToElement(element).click().perform();
	}

}
