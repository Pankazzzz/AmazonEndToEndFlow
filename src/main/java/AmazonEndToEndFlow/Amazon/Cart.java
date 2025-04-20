package AmazonEndToEndFlow.Amazon;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilityClasses.Utilities;

public class Cart extends Utilities {

WebDriver driver;
	
	public Cart(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "(//input[@type='checkbox'])[1]/following-sibling::i")
	List<WebElement> checkboxes;
	
	By verifyElementClickableBy = By.xpath("(//input[@type='checkbox'])[1]/following-sibling::i");
	
	@FindBy(xpath = "(//input[@type='checkbox'])[1]/following-sibling::i")
	WebElement checkboxElement;

	@FindBy(xpath = "(//input[@type='checkbox'])[1]")
	WebElement selectedCheckboxElement;		

//	Assert.assertTrue(driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).isSelected());
	
	public void verifyCheckBoxesCount()
	{
		System.out.println("Total checkboxes found: " + checkboxes.size());	
	}
	
	public void selectCheckBox()
	{
		waitUntilElementToBeClickable(verifyElementClickableBy);
		moveToElement(checkboxElement);
		sleep(2000);
	}
	
	public boolean verifyCheckBoxSelected()
	{
		return selectedCheckboxElement.isSelected();
	}
	
	@FindBy(css="h4 span[class='a-truncate-full']")
	WebElement productNamElement;
	
	public String returnProductName()
	{
		return productNamElement.getText();
	}
	
	@FindBy(css="[name='proceedToRetailCheckout']")
	WebElement checkoutElement;
	
	public void proceedToCheckOut()
	{
		checkoutElement.click();
		CheckOut checkOut = new CheckOut(driver);
	}
	
	
	

	
	@FindBy(xpath="//div[@name='sc-quantity']//span[2]")
	WebElement productQuantityElement;
	
	@FindBy(xpath="//div[@name='sc-quantity']//span[2]")
	List<WebElement> quantityElements;
	
	@FindBy(xpath = "(//div[@name='sc-quantity']//button/span)[1]")
	List<WebElement> deleteButtons;
	
	@FindBy(css="span[class='a-size-base sc-list-item-removed-msg-text-delete']")
	WebElement productRemoveConfirmationElement;
	
	public String returnProductQuantity()
	{	
		return productQuantityElement.getText();
	}
	
	public void deleteProduct() throws InterruptedException
	{
		
		try {
		    while (true) {
		    	
		        // Break loop if cart is empty
		        if (quantityElements.isEmpty() || quantityElements.get(0).getText().equals("0")) {
		            System.out.println("Cart is empty.");
		            break;
		        }

		        // Find and click the delete button
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
	
	public String getProductRemoveConfirmation()
	{
		visibilityOfElement(productRemoveConfirmationElement);
		return productRemoveConfirmationElement.getText();
	}
}
