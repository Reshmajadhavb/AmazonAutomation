package seleniumIntrodction;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonAddToCart {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.get("https://www.amazon.com/");

		driver.findElement(By.cssSelector("input#twotabsearchtextbox")).sendKeys("hats for men");

		driver.findElement(By.id("nav-search-submit-button")).click();
		driver.findElement(By.xpath(
				"//div[@class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1']//img[1]"))
				.click();
		driver.findElement(By.xpath("//span[@class='a-dropdown-label']")).click();
		driver.findElement(By.xpath("//a[@id='quantity_1']")).click();

		driver.findElement(By.id("add-to-cart-button")).click();
		String successMessage = driver
				.findElement(By.xpath("//span[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']"))
				.getText();
		System.out.println(successMessage);

		// go to cart
		driver.findElement(By.id("nav-cart-text-container")).click();

		// Assert product quantity
		String[] productQnty = driver.findElement(By.xpath("//span[@class='a-button-text a-declarative']")).getText()
				.split(":");
		Assert.assertEquals(productQnty[1], "2");
		System.out.println("Assert successful, hat quantity updated as :" + productQnty[1]);

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

		String totalPriceWith$ = driver.findElement(By.xpath(
				"//span[@id='sc-subtotal-amount-activecart']//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']"))
				.getText();
		Matcher m1 = p.matcher(totalPriceWith$);
		m1.matches();
		String twoHatsPrice_num = m1.group(1).replace(",", "");

		Double totalPriceForTwo = Double.valueOf(twoHatsPrice_num);

		System.out.println(singleHatPrice * 2);
		Assert.assertEquals(totalPriceForTwo, singleHatPrice * 2);
		System.out.println("Assert success, price for two hats correctly updated as :" + totalPriceForTwo);

		// change cart quantity to 1 item

		driver.findElement(By.xpath("//span[@class='a-button-text a-declarative']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("quantity_1")).click();

		// assert for 1 quantity
		String[] productQntyOne = driver.findElement(By.xpath("//span[@class='a-button-text a-declarative']")).getText()
				.split(":");

		Assert.assertEquals(productQntyOne[1], "1");
		System.out.println("Assert successful, hat quantity updated :" + productQntyOne[1]);

		// Verify total price is updated correctly for one hat
		Thread.sleep(5000);

		System.out.println("single hat price" + singleHatPriceWith$);

		String totalPriceUpdated = driver.findElement(By.xpath(
				"//span[@id='sc-subtotal-amount-activecart']//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']"))
				.getText();
		Assert.assertEquals(totalPriceUpdated, singleHatPriceWith$);
		System.out.println("Assert successful, total price for one hat is" + totalPriceUpdated);

	}

}
