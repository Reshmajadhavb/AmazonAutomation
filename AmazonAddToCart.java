package seleniumIntrodction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionMouse {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
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

		// assert quantity is 2
		String[] splitQty = driver.findElement(By.xpath("//span[@class='a-button-text a-declarative']")).getText().split(":");

		// int totalQuantyOfHats = Integer.parseInt(splitQty[1]);
		System.out.println(splitQty[1]);
		Assert.assertEquals(splitQty[1], "2");

		// get the single item price
		Thread.sleep(3000);
		String singleHatPriceWith$ = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']")).getText();

		Pattern p = Pattern.compile("[^0-9]*([0-9]*,?([0-9]+(\\.[0-9]*))?)");
		Matcher m = p.matcher(singleHatPriceWith$);
		m.matches();
		String singleHatPrice_num = m.group(1).replace(",", "");

		Double singleHatPrice = Double.valueOf(singleHatPrice_num);
		System.out.println(singleHatPrice);

		// assert total price for 2 hats

		String totalPricericeWith$ = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-activecart']//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")).getText();
		Matcher m1 = p.matcher(totalPricericeWith$);
		m1.matches();
		String twoHatsPrice_num = m1.group(1).replace(",", "");

		Double totalPrice = Double.valueOf(twoHatsPrice_num);
		System.out.println(totalPrice);
		Assert.assertEquals(totalPrice, singleHatPrice * 2);

		// change cart quantity to 1 item

		driver.findElement(By.xpath("//span[@class='a-button-text a-declarative']")).click();
		driver.findElement(By.id("quantity_1")).click();

		driver.navigate().refresh();

		// Assert quantity updated as 1
		 Assert.assertEquals(splitQty[1] , "1");

		// Assert total price is updated for 1 item
		Assert.assertEquals(totalPrice, singleHatPrice);

	}

}
