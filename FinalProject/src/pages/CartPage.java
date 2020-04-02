package pages;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CartPage extends BasicPage {

	public CartPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		super(driver, locators, waiter);
	}

	public List<WebElement> getShoppingCart() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("cart_items")));
	}

	public WebElement getTheCart() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("cart")));
	}

	public void goToTheCart() {
		this.getTheCart().click();
	}

	public WebElement getEmptyCartMessage() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("empty_cart")));
	}
	public double getSubTotalCost() throws ParseException {
		String items = driver.findElement(By.xpath(locators.getProperty("sub_total"))).getText();
		String numbers = items.substring(Math.max(0, items.length() - 6));
		double subTotalCost = Double.valueOf(numbers);

		return subTotalCost;
	}

	public double getTotalItemCost() throws ParseException {
		double totalCost = 0;
		double total = 0;
		NumberFormat format = NumberFormat.getCurrencyInstance();
		List<WebElement> cart = this.getShoppingCart();
		for (int i = 1; i < cart.size() - 1; i++) {
			String items = cart.get(i).findElement(By.xpath(locators.getProperty("total_cost"))).getText();
			total += (double) format.parse(items);
		}
		BigDecimal bd = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
		totalCost = bd.doubleValue();
		return totalCost;
	}
	public boolean isTheItemAdded(int j, String id) {
		List<WebElement> items = this.getShoppingCart();
		boolean correct = false;
		for (int i = j; i < items.size() - 1; i++) {
			String item = items.get(i).findElement(By.xpath(this.locators.getProperty("item_id"))).getText();
			if (item.contains(id)) {
				correct = true;
			}
		}
		return correct;
	}

	public boolean isTheCartEmpty() {
		return this.getEmptyCartMessage().getText().contains("empty");
	}

	public boolean isThePriceRight() throws ParseException {
		return this.getTotalItemCost() == this.getSubTotalCost();
	}

}
