package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartPage;
import pages.StoreItemPage;
import utils.ExcelUtils;

public class CartTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
	private SoftAssert sa;

	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.locators = new Properties();
		locators.load(new FileInputStream("config/locators.properties"));
		driver.manage().window().maximize();
		sa = new SoftAssert();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void addAllProductsToCartTest() throws InterruptedException {

		ExcelUtils.setExcell(this.locators.getProperty("data_source"));
		ExcelUtils.setWorkSheet(0);

		StoreItemPage sip = new StoreItemPage(driver, locators, waiter);

		CartPage cp = new CartPage(driver, locators, waiter);
		// add items from the table to the cart
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String link = ExcelUtils.getDataAt(i, 1);
			this.driver.navigate().to(link);
			sip.clickAddToCartBtn();
			String id = ExcelUtils.getDataAt(i, 0);
			sa.assertTrue(cp.isTheItemAdded(i, id));
			sa.assertTrue(cp.isTheItemAdded(i, id));
		}

		sa.assertAll();
	}

	@Test(priority = 3)
	public void clearTheCookiesTest() throws InterruptedException {
		CartPage cp = new CartPage(driver, locators, waiter);
		cp.goToTheCart();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();

		Assert.assertTrue(cp.isTheCartEmpty());
	}

	@Test(priority = 2)
	public void checkTheTotalPriceTest() throws InterruptedException, ParseException {
		CartPage cp = new CartPage(driver, locators, waiter);
		Assert.assertTrue(cp.isThePriceRight());
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
