package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelUtils;

public class CartPage extends BasicPage {

	public CartPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		super(driver, locators, waiter);
	}
	
	public List<WebElement> getShoppingCart() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("cart_items")));
	}

	public boolean areAllItemsAdded() {
		List<WebElement> items = this.getShoppingCart();
		ExcelUtils.setExcell(this.locators.getProperty("data_source"));
		ExcelUtils.setWorkSheet(0);
		boolean correct = true;
		for (int i=1; i<ExcelUtils.getRowNumber(); i++) {
			String item = items.get(i).findElement(By.xpath(this.locators.getProperty("item_id"))).getText();
			String id = ExcelUtils.getDataAt(i, 0);
			if(!this.isTheItemAdded(item, id)) {
				correct = false;
			}
		}
		return correct;
	}
	public boolean isTheItemAdded(String item, String id) {
		boolean correct = true;
		try {
			item.compareTo(id);
		} catch (Exception e) {
			correct = false;
		}							
		return correct;

	}
	public WebElement getTheCart() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("cart")));
	}
	public void goToTheCart() {
		this.getTheCart().click();
	}
	public boolean isTheCartEmpty() {
		boolean correct = true;
		try {
			this.driver.findElement(By.xpath(this.locators.getProperty("empty_cart")));
		} catch (Exception e) {
			correct = false;
		}
		return correct;
	}
	
}

