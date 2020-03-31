package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import pages.PetStoreMenuPage;

public class PetStoreMenuTest {

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

	@Test
	public void checkFunctionalLinks() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("menu_url"));

		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);
		psmp.allUrlStatusWorking();
		sa.assertAll();
	}

	@Test
	public void checkPageNavigation() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("menu_url"));

		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);

		psmp.checkQuickLinkPages("fish");
		driver.navigate().to(this.locators.getProperty("menu_url"));
		psmp.checkPicLinkPages("fish");
		driver.navigate().to(this.locators.getProperty("menu_url"));
		psmp.checkSidebarLinkPages("fish");
		sa.assertAll();

	}

	@Test
	public void checkSignIn() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("menu_url"));

		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);

		Assert.assertTrue(psmp.checkSignInPage());
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
