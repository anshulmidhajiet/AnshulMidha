package stepDefinitions;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EbaySteps {
	 WebDriver driver;
	 WebDriverWait wait;
	 
	 @Given("I open the browser and navigate to {string}")
	    public void i_open_the_browser_and_navigate_to(String url) {
	        // Setup ChromeDriver
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get(url);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    }

	 @When("I search for {string}")
	    public void i_search_for(String searchTerm) {
	        WebElement searchBox = driver.findElement(By.id("gh-ac"));
	        searchBox.sendKeys(searchTerm);
	        driver.findElement(By.id("gh-btn")).click();
	    }
	 
	 @When("I Click on the first book in the list")
	    public void i_click_on_the_first_book_in_the_list() {
		 String oldTab = driver.getWindowHandle();
	        WebElement firstItem = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//ul[contains(@class, 'srp-results')]/li[1]/following-sibling::li[1]/div/div[2]/a")));
	        firstItem.click();
	        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	        newTab.remove(oldTab);
	         // change focus to new tab
	         driver.switchTo().window(newTab.get(0));
	    }
	 
	 @When("I add the item to the cart")
	    public void i_add_the_item_to_the_cart() {

	        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//span[text()='Add to cart']")));
	        addToCartButton.click();
	    }
	 @Then("the cart should display {string} item")
	    public void the_cart_should_display_item(String expectedItemCount) {
	        WebElement cartCount = wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.id("gh-cart-n")));
	        String actualItemCount = cartCount.getText();
	        System.out.println("Number of Items in cart->"+actualItemCount);
	        Assert.assertEquals("Cart item count does not match", expectedItemCount, actualItemCount);
	        driver.quit();
	    }
}
