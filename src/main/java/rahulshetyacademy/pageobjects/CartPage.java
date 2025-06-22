package rahulshetyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshetyacademy.AbstractComponents.AbstractComponent;


public class CartPage extends AbstractComponent{
	WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match=cartProducts.stream().anyMatch(cartProds -> cartProds.getText().equalsIgnoreCase(productName));
	return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		CheckoutPage checkoutPage=new CheckoutPage(driver);
		//return new CheckoutPage(driver);
				return checkoutPage;
	}
}