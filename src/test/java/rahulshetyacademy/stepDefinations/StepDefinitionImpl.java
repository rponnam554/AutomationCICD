package rahulshetyacademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshetyacademy.TestComponents.BaseTest;
import rahulshetyacademy.pageobjects.CartPage;
import rahulshetyacademy.pageobjects.CheckoutPage;
import rahulshetyacademy.pageobjects.ConfirmationPage;
import rahulshetyacademy.pageobjects.LandingPage;
import rahulshetyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
@Given("I landed on Ecommerce Page")
public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage=launchApplication();
	}
 @Given("^Logged in with username(.+) and password(.+)$")
 public void logged_in_with_username_password(String username,String password)
 {
		productCatalogue=landingPage.loginApplication(username,password);

 }
 
 @When("^I add product (.+) to cart$")
 public void i_add_product_to_cart(String productName) throws InterruptedException
 {
	 List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
 }
 
 @And("Checkout (.+) and submit the order")
 public void checkout_and_submit_the_order(String productName) throws InterruptedException
 {
	 CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay(productName);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Assert.assertTrue(match);
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		confirmationPage=checkoutPage.submitOrder();
 }
 
 @Then("{string} message is displayed on confirmationPage")
 public void mesage_displayed_confirmationPage(String string)
 {
	 String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
 }
 
 @Then("^\"([^\"]*)\" message is displayed$")
 public void something_message_is_displayed(String strArg1)
 {
	 Assert.assertEquals(strArg1, landingPage.getErrorMesage());
	 driver.close(); 
 
 }
}
