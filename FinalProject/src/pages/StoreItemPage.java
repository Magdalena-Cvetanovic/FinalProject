package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoreItemPage extends BasicPage {

	public StoreItemPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		super(driver, locators, waiter);
	}
	public WebElement getAddToCartBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("add_to_cart")));
	}
	public void clickAddToCartBtn() {
		this.getAddToCartBtn().click();
	}
	

}
