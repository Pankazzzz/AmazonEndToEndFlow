package AbstractTestClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import AmazonEndToEndFlow.Amazon.LandingPage;
import DataFetch.JSONDataReader;
import io.cucumber.java.After;

public class BaseTest extends JSONDataReader{
	
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public void initializer() throws IOException
	{
		FileInputStream fileInputStream = new FileInputStream("/Users/pankajshukla/eclipse-workspace/Amazon/src/test/java/DataResources/ConfigDetails.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);
		
		String browserName = properties.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			 driver = new ChromeDriver();		
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	public String getScreenShot(WebDriver driver,String testCaseName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File screeshotPath =   ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+"/ExtentReports/"+testCaseName+".png");
		FileHandler.copy(screeshotPath, destFile);
		return destFile.getAbsolutePath();	  
	}
	
	@BeforeMethod
	public void load() throws IOException
	{
		initializer();
		landingPage = new LandingPage(driver);
		landingPage.loadLandingPage();
	}
	
	@AfterMethod
	public void quit()
	{
		driver.quit();
	}
	

}
