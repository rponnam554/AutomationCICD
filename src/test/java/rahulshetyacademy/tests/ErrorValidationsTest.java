package rahulshetyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshetyacademy.TestComponents.BaseTest;
import rahulshetyacademy.TestComponents.Retry;
import rahulshetyacademy.pageobjects.CartPage;
import rahulshetyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void submitOrder() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("raju.ponnam554@gmail.com", "Cipher@1234");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMesage());
		
		// div[aria-label='Incorrect email or password.']
		// .ng-tns-c4-12.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		ProductCatalogue productCatalogue=landingPage.loginApplication("raju.po554@gmail.com", "Cipher@123");
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
}
	
}