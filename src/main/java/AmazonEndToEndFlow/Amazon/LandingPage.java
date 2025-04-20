package AmazonEndToEndFlow.Amazon;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilityClasses.Utilities;

public class LandingPage extends Utilities{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(css="a[aria-label*='Amazon.in']")
	WebElement logoNamElement;
	
	@FindBy(xpath="(//a[contains(@href,'signin?openid')])[1]")
	WebElement signElement;
	
	@FindBy(xpath="(//span[text()='Sign in'])[1]")
	WebElement signInButton;
	
	@FindBy(className = "nav-search-submit-text")
	WebElement searchProduct;
	
	@FindBy(id = "twotabsearchtextbox")
	WebElement setProduct;
	
	public void loadLandingPage()
	{
		driver.get("https://www.amazon.in/");
	}
	
	public String logoName()
	{
		return logoNamElement.getAttribute("aria-label");
	}
	
	public void moveToSignIn()
	{
		actions.moveToElement(signElement).build().perform();
	}
	
	public SignIn clickOnSignIn()
	{
		moveToSignIn();
		signInButton.click();
		sleep(2000);
		SignIn signIn = new SignIn(driver);
		return signIn;
	}
	
	public void setProduct(String product)
	{
		setProduct.sendKeys(product);
	}
	
	public void searchProduct()
	{
		searchProduct.click();
	}

	
	@FindBy(xpath = "//div[contains(@class,'a-section a-spacing-small puis-padding-left-small puis-padding-right-small')]")
	List<WebElement> productList;
	
	By productName = By.xpath(".//h2/span"); 
	

	By addToCartButtonElement=By.xpath(".//div[5]//button");
	
	@FindBy(xpath = ".//span[@data-a-selector='increment-icon']")
	WebElement incrementElement;
	
	By incrementBy = By.xpath(".//span[@data-a-selector='increment-icon']");
	
	public void waitForvailabilityOfProduct()
	{
		visibilityOfElement(productList.get(0));
		sleep(2000);
	}
	
	public List<WebElement> getProductList()
	{
		return productList;
	}

	public void incrementQuantity(int index, int quantity)
	{
		 if(quantity>1)
         {
         		int j=2;
	            while(j<=quantity)
	            {
	            	productList.get(index).findElement(incrementBy).click();
	            	sleep(1000);
	            	j++;
	            }
         }
	}
	
	public void addProduct(String productName,int quantity)
	{		
		for (int i = 0; i < productList.size(); i++) {
	    try {
	        String productNameString = getProductList().get(i).findElement(this.productName).getText();
	        System.out.println("Checking product: " + productName);

	        if (productNameString.contains(productName)) {
	            WebElement button = getProductList().get(i).findElement(addToCartButtonElement);
	            scrollIntoView(button);
	            elementToBeClickable(button);
	            System.out.println("Clicked button for: " + productName);
	            incrementQuantity(i,quantity);     
	            break;
	        }
	    } catch (NoSuchElementException e) {
	        System.out.println("Skipping element " + i + " (no h2/span or button found)");
	    }
	}
	}
	
	@FindBy(xpath = "//span[contains(@class,'nav-cart-count')]")
	WebElement addedProductElement;
	
	public void verifyAllProductAdded(int quantity)
	{
		while(addedProductElement.getText().equals(quantity))
		{
			continue;
		}
	}
	
	@FindBy(id="nav-cart-count-container")
	WebElement cartButtonElement;
	
	public Cart addToCart()
	{
		cartButtonElement.click();
		Cart cart = new Cart(driver);
		return cart;
	}

}
