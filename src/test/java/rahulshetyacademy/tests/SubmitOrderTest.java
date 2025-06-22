package rahulshetyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshetyacademy.TestComponents.BaseTest;
import rahulshetyacademy.pageobjects.CartPage;
import rahulshetyacademy.pageobjects.CheckoutPage;
import rahulshetyacademy.pageobjects.ConfirmationPage;
import rahulshetyacademy.pageobjects.LandingPage;
import rahulshetyacademy.pageobjects.OrderPage;
import rahulshetyacademy.pageobjects.ProductCatalogue;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay(input.get("product"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Assert.assertTrue(match);
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		 
 
	}
	
	//To Verify ZARA COAT 3 is displaying orders page
	
		@Test(dependsOnMethods= {"submitOrder"})
		public void orderHistoryTest()
		{
			ProductCatalogue productCatalogue=landingPage.loginApplication("raju.ponnam554@gmail.com", "Cipher@123");
			OrderPage ordersPage=productCatalogue.goToOrdersPage();
			Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		}
		
	
		
		//Extent reports
		@DataProvider
		public Object[][] getData() throws IOException 
		{	
		/*	HashMap<String,String> map=new HashMap<String,String>();
			map.put("email", "raju.ponnam554@gmail.com");
			map.put("password", "Cipher@123");
			map.put("product", "ZARA COAT 3");
			
			HashMap<String,String> map1=new HashMap<String,String>();
			map1.put("email", "raju.po554@gmail.com");
			map1.put("password", "Cipher@123");
			map1.put("product", "ADIDAS ORIGINAL");
		*/
			List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshetyacademy//data//PurchaseOrder.json");
		 			return new Object[][]    {{data.get(0)}, {data.get(1)} };
}
		 /*
		 @DataProvider
		public Object[][] getData()
		{
		return new Object[][]    {{"raju.ponnam554@gmail.com","Cipher@123","ZARA COAT 3"}, {"raju.po554@gmail.com","Cipher@123","ADIDAS ORIGINAL"}};
		 */
}