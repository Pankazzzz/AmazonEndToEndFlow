package AmazonEndToEndFlow.Amazon;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class StandAloneFlow {
	
	@Test(priority = 1)
	public void endToEndFlow() throws Exception
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait waitDriver = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String logoNameString = driver.findElement(By.cssSelector("a[aria-label*='Amazon.in']")).getAttribute("aria-label");
		
		Actions actions = new Actions(driver);
		WebElement signElement = driver.findElement(By.xpath("(//a[contains(@href,'signin?openid')])[1]"));
		actions.moveToElement(signElement).build().perform();
		driver.findElement(By.xpath("(//span[text()='Sign in'])[1]")).click();
		Thread.sleep(2000);
		WebElement emailLogin = driver.findElement(By.xpath("//*[@type='email']"));
		waitDriver.until(ExpectedConditions.visibilityOf(emailLogin));
		emailLogin.sendKeys("shuklapankaj799@gmail.com");
		driver.findElement(By.className("a-button-input")).click();
		waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("9022575112@");
		driver.findElement(By.cssSelector("[type='submit']")).click();
		
			
		Assert.assertEquals(logoNameString, "Amazon.in");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("parachute hair oil");
		driver.findElement(By.className("nav-search-submit-text")).click();
		
		List<WebElement> productList = driver.findElements(By.xpath("//div[contains(@class,'a-section a-spacing-small puis-padding-left-small puis-padding-right-small')]"));
		waitDriver.until(ExpectedConditions.visibilityOf(productList.get(0)));
		Thread.sleep(2000);
		for (int i = 0; i < productList.size(); i++) {
		    try {
		        String productName = productList.get(i).findElement(By.xpath(".//h2/span")).getText();
		        System.out.println("Checking product: " + productName);

		        if (productName.contains("Parachute Coconut Oil - 1 L Pet Jar")) {
		            JavascriptExecutor js = (JavascriptExecutor) driver;
		            WebElement button = productList.get(i).findElement(By.xpath(".//div[5]//button"));
		            js.executeScript("arguments[0].scrollIntoView(true);", button);
		            Thread.sleep(2000);

		            waitDriver.until(ExpectedConditions.elementToBeClickable(button)).click();
		            System.out.println("Clicked button for: " + productName);
		            
		            int j=2;
		            while(j<=5)
		            {
		            	productList.get(i).findElement(By.xpath(".//span[@data-a-selector='increment-icon']")).click();
		            	Thread.sleep(1000);
		            	j++;
		            }
		            break; 
		        }
		    } catch (NoSuchElementException e) {
		        System.out.println("Skipping element " + i + " (no h2/span or button found)");
		    }
		}
		WebElement productCountElement = driver.findElement(By.xpath("//span[contains(@class,'nav-cart-count')]"));
		while(productCountElement.getText().equals(5))
		{
			continue;
		}
		
		driver.findElement(By.id("nav-cart-count-container")).click();
		List<WebElement> checkboxes = driver.findElements(By.xpath("(//input[@type='checkbox'])[1]/following-sibling::i"));
		System.out.println("Total checkboxes found: " + checkboxes.size());
		WebElement checkbox = waitDriver.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='checkbox'])[1]/following-sibling::i")));

		actions = new Actions(driver);
		actions.moveToElement(checkbox).click().perform();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).isSelected());
		String selectedProductName = driver.findElement(By.cssSelector("h4 span[class='a-truncate-full']")).getText();
		Assert.assertTrue(selectedProductName.contains("Parachute Coconut Oil - 1 L Pet Jar"));
		driver.findElement(By.cssSelector("[name='proceedToRetailCheckout']")).click();
		driver.close();
	}
	
	
	@Test(dependsOnMethods = "endToEndFlow",priority = 2)
	public void verifyProduct() throws Exception
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait waitDriver = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String logoNameString = driver.findElement(By.cssSelector("a[aria-label*='Amazon.in']")).getAttribute("aria-label");
		
		Actions actions = new Actions(driver);
		WebElement signElement = driver.findElement(By.xpath("(//a[contains(@href,'signin?openid')])[1]"));
		actions.moveToElement(signElement).build().perform();
		driver.findElement(By.xpath("(//span[text()='Sign in'])[1]")).click();
		Thread.sleep(2000);
		WebElement emailLogin = driver.findElement(By.xpath("//*[@type='email']"));
		waitDriver.until(ExpectedConditions.visibilityOf(emailLogin));
		emailLogin.sendKeys("shuklapankaj799@gmail.com");
		driver.findElement(By.className("a-button-input")).click();
		waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("9022575112@");
		driver.findElement(By.cssSelector("[type='submit']")).click();
		
			
		Assert.assertEquals(logoNameString, "Amazon.in");
		driver.findElement(By.id("nav-cart-count-container")).click();
		String productQuantityString = driver.findElement(By.xpath("//div[@name='sc-quantity']//span[2]")).getText();
		Assert.assertEquals(productQuantityString, "5" , "Product count Did not match");
		driver.close();
	}
	
	
	@Test(dependsOnMethods = "verifyProduct",priority = 3)
	public void deleteProduct() throws Exception
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait waitDriver = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String logoNameString = driver.findElement(By.cssSelector("a[aria-label*='Amazon.in']")).getAttribute("aria-label");
		
		Actions actions = new Actions(driver);
		WebElement signElement = driver.findElement(By.xpath("(//a[contains(@href,'signin?openid')])[1]"));
		actions.moveToElement(signElement).build().perform();
		driver.findElement(By.xpath("(//span[text()='Sign in'])[1]")).click();
		Thread.sleep(2000);
		WebElement emailLogin = driver.findElement(By.xpath("//*[@type='email']"));
		waitDriver.until(ExpectedConditions.visibilityOf(emailLogin));
		emailLogin.sendKeys("shuklapankaj799@gmail.com");
		driver.findElement(By.className("a-button-input")).click();
		waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("9022575112@");
		driver.findElement(By.cssSelector("[type='submit']")).click();
		
			
		Assert.assertEquals(logoNameString, "Amazon.in");
		driver.findElement(By.id("nav-cart-count-container")).click();
		String productQuantityString = driver.findElement(By.xpath("//div[@name='sc-quantity']//span[2]")).getText();
		Assert.assertEquals(productQuantityString, "5" , "Product count Did not match");
		
		if(productQuantityString.equals("5"))
		{
			try {
			    while (true) {
			        List<WebElement> quantityElements = driver.findElements(By.xpath("//div[@name='sc-quantity']//span[2]"));

			        // Break loop if cart is empty
			        if (quantityElements.isEmpty() || quantityElements.get(0).getText().equals("0")) {
			            System.out.println("Cart is empty.");
			            break;
			        }

			        // Find and click the delete button
			        List<WebElement> deleteButtons = driver.findElements(By.xpath("(//div[@name='sc-quantity']//button/span)[1]"));
			        if (!deleteButtons.isEmpty()) {
			            deleteButtons.get(0).click();
			            Thread.sleep(2000);
			        } else {
			            break;
			        }
			    }
			} catch (StaleElementReferenceException e) {
			    System.out.println("Stale element encountered. Retrying...");
			} catch (NoSuchElementException e) {
			    System.out.println("No more elements found.");
			}
		}

		waitDriver.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("span[class='a-size-base sc-list-item-removed-msg-text-delete']"))));
		String productRemovedText = driver.findElement(By.cssSelector("span[class='a-size-base sc-list-item-removed-msg-text-delete']")).getText();
		Assert.assertTrue(productRemovedText.contains("was removed from Shopping Cart."),"Product did not got deleted");
		driver.close();
	}
	

}
