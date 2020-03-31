package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasicPage {

	public HomePage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		super(driver, locators, waiter);
	}

	public WebElement getEnterTheStore() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("enter_store")));
	}

	public void clickEnterTheStore() {
		this.getEnterTheStore().click();
	}

	public WebElement getJPetStoreDemo() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("logo")));
	}

	public boolean enteredTheStore() {
		boolean entered = true;
		try {
			this.getJPetStoreDemo();
		} catch (Exception e) {
			entered = false;
		}
		return entered;
	}

}
