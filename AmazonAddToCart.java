package seleniumIntrodction;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;


import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonAddToCart {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.com/");

		driver.findElement(By.cssSelector("input#twotabsearchtextbox")).sendKeys("hats for men");

		driver.findElement(By.id("nav-search-submit-button")).click();
		driver.findElement(By.xpath("//div[@class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1']//img[1]")).click();
		driver.findElement(By.xpath("//span[@class='a-dropdown-label']")).click();
		driver.findElement(By.xpath("//a[@id='quantity_1']")).click();
		driver.findElement(By.id("add-to-cart-button")).click();
		String successMessage = driver
				.findElement(By.xpath("//span[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']"))
				.getText();
		System.out.println(successMessage);

		// go to cart
		driver.findElement(By.id("nav-cart-text-container")).click();


		Thread.sleep(2000);
		String singleHatPriceWith$ = driver.findElement(By.xpath(
				"//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']"))
				.getText();

		Pattern p = Pattern.compile("[^0-9]*([0-9]*,?([0-9]+(\\.[0-9]*))?)");
		Matcher m = p.matcher(singleHatPriceWith$);
		m.matches();
		String singleHatPrice_num = m.group(1).replace(",", "");

		Double singleHatPrice = Double.valueOf(singleHatPrice_num);
		System.out.println(singleHatPrice);

		// assert total price for 2 hats

		String totalPriceWith$ = driver.findElement(By.id("sc-subtotal-amount-activecart")).getText(); 
		Matcher m1 = p.matcher(totalPriceWith$);
		m1.matches();
		String twoHatsPrice_num = m1.group(1).replace(",", "");

		Double totalPrice = Double.valueOf(twoHatsPrice_num);
		System.out.println(totalPrice);
		Assert.assertEquals(totalPrice, singleHatPrice * 2);

		// change cart quantity to 1 item

		
		driver.findElement(By.xpath("//span[@class='a-button-text a-declarative']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("quantity_1")).click();

 //assert for 1 quantity
		String[] productQntyOne = driver.findElement(By.xpath("//span[@class='a-button-text a-declarative']")).getText().split(":");

		Assert.assertEquals(productQntyOne[1], "1");
		System.out.println("Assert successful, hat quantity updated :" +productQntyOne[1]);
	

	//Verify total price is updated correctly for one hat
		

		String singleHat=driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']")).getText();
		System.out.println(singleHat);
		String totalPriceUpdated =driver.findElement(By.id("sc-subtotal-amount-activecart")).getText();
		System.out.println(totalPriceUpdated);
	    Assert.assertEquals(totalPriceUpdated, singleHat);

	    


		

	}

}
 