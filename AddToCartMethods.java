package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddToCartMethods {
	
	
	@Given("Launch the Amazon URL")
	public void launch_the_amazon_url() {
	    driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		throw new io.cucumber.java.PendingException();
	}
	
	
	@When("Enter productname as {string}")
	public void searchProduct(String productname) {
	driver.findElement(By.cssSelector("input#twotabsearchtextbox")).sendKeys("hats for men");

	}
	@When("click on search button")
	public void clickSearchButton() {
	driver.findElement(By.id("nav-search-submit-button")).click();
	}

*****	@Then("product list should be displayed")
	public void product_list_should_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("click on first hat in the product list")
	public void getFirstProduct() {
	driver.findElement(By.xpath("//div[@class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1']//img[1]")).click();
	
	}
*****	@Then("i should be redirected to the product details page")
	public void i_should_be_redirected_to_the_product_details_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
*****	@Then("product quantity and price should be visible")
	public void product_quantity_and_price_should_be_visible() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("select hat quantity as two from dropdown")
	public void selectProductQnty() {
	driver.findElement(By.xpath("//span[@class='a-dropdown-label']")).click();
	driver.findElement(By.xpath("//a[@id='quantity_1']")).click();
	}

*****	@Then("product\\(hat) quantity should be updated as {int}")
	public void product_hat_quantity_should_be_updated_as(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
*****	@Then("price should be correctly updated for two quantity")
	public void price_should_be_correctly_updated_for_two_quantity() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("click on Add to cart button")
	public void addToCartButton() {
	driver.findElement(By.id("add-to-cart-button")).click();
	}

	@Then("should get the success message on page")
	public void productAddedMessage() {
	String successMessage = driver.findElement(By.xpath("//span[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']")).getText();
	System.out.println(successMessage);
	}

*****	@Then("cart subtotal should be displayed")
	public void cart_subtotal_should_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("click on Cart button or on Go to Cart button")
	public void goToCartButton() {
	driver.findElement(By.id("nav-cart-text-container")).click();
	}

*****	@Then("shopping cart page should be loaded")
	public void shopping_cart_page_should_be_loaded() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("verify correct product quantity is displayed")
	public void productQuanity() {
	String[] splitQty = driver.findElement(By.xpath("//span[@class='a-button-text a-declarative']")).getText().split(":");
	System.out.println(splitQty[1]);
	Assert.assertEquals(splitQty[1], "2", );
	}

	@And("get the price for one quantity")
	public void getProductPrice() {
	    Thread.sleep(3000);
		String singleHatPriceWith$ = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']")).getText();

		Pattern p = Pattern.compile("[^0-9]*([0-9]*,?([0-9]+(\\.[0-9]*))?)");
		Matcher m = p.matcher(singleHatPriceWith$);
		m.matches();
		String singleHatPrice_str = m.group(1).replace(",", "");

		Double singleHatPrice = Double.valueOf(singleHatPrice_str);
		System.out.println(singleHatPrice);
	}

	@Then("verify correct total price is displayed")
	public void totalPrice() {
	    String totalPricericeWith$ = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-activecart']//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")).getText();
		Matcher m1 = p.matcher(totalPricericeWith$);
		m1.matches();
		String twoHatsPrice_num = m1.group(1).replace(",", "");

		Double totalPrice = Double.valueOf(twoHatsPrice_num);
		System.out.println(totalPrice);
		Assert.assertEquals(totalPrice, singleHatPrice * 2);
	}
	@When("change the hat quanitity as one from the dropdown")     // method exists already
	public void selectProductQnty() {
	driver.findElement(By.xpath("//span[@class='a-button-text a-declarative']")).click();
	driver.findElement(By.id("quantity_1")).click();
	}

	@Then("verify hat quantity is updated as one")           //method exists already
	public void productQuanity() {
	Assert.assertEquals(splitQty[1] , "1");
	}

	@And("verify total price is updated correctly for one hat")   //methods exists already
	public totalPrice() {
	    Assert.assertEquals(totalPrice, singleHatPrice);
	}


}
