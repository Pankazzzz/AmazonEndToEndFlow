package AmazonEndToEndFlow.Amazon;


import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AbstractTestClasses.BaseTest;
import DataFetch.JSONDataReader;
import net.bytebuddy.asm.Advice.This;
import AbstractTestClasses.*;

public class DeleteProduct extends BaseTest{
	
	@Test(priority = 1,dataProvider = "dataLoad")
	public void deleteProduct(HashMap<String, String> dataHashMap) throws Exception
	{
		String logoNameString = landingPage.logoName();
		Assert.assertEquals(logoNameString, "Amazon.in");
		SignIn signIn = landingPage.clickOnSignIn();
		signIn.enterEmail(dataHashMap.get("username"));
		signIn.clickOnEmailSubmit();
		signIn.enterPassword(dataHashMap.get("password"));
		signIn.clickOnLogin();
		landingPage.verifyAllProductAdded(Integer.parseInt(dataHashMap.get("quantity")));
		Cart cart = landingPage.addToCart();
		String productQuantity = cart.returnProductQuantity();
		Assert.assertEquals(productQuantity, dataHashMap.get("quantity") , "Product count Did not match");
		if(productQuantity.equals(dataHashMap.get("quantity")))
		{
			cart.deleteProduct();
		}
		Assert.assertTrue(cart.getProductRemoveConfirmation().contains("was removed from Shopping Cart."),"Product did not got deleted");
	}
	
	@DataProvider
	public Object[][] dataLoad() throws IOException
	{
		List<HashMap<String, String>> data= JSONDataReader.dataFetch();
		
		return new Object[][] {{data.get(0)}};
	}
	

}
