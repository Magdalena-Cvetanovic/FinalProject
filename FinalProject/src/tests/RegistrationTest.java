package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreMenuPage;
import pages.RegistrationPage;
import pages.SignInPage;
import utils.ExcelUtils;

public class RegistrationTest {
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
	public void registrationTest() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("registration_url"));
		ExcelUtils.setExcell(this.locators.getProperty("data_source"));
		ExcelUtils.setWorkSheet(1);
		ExcelUtils.setRandomUserId();
		
		RegistrationPage rp = new RegistrationPage(driver, locators, waiter);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			rp.goToRegistration();
			String id = ExcelUtils.getDataAt(i, 0);
			String pass = ExcelUtils.getDataAt(i, 1);
			String repeatPass = ExcelUtils.getDataAt(i, 1);
			String firstName = ExcelUtils.getDataAt(i, 2);
			String lastName = ExcelUtils.getDataAt(i, 3);
			String email = ExcelUtils.getDataAt(i, 4);
			String phone = ExcelUtils.getDataAt(i, 5);
			String addr1 = ExcelUtils.getDataAt(i, 6);
			String addr2 = ExcelUtils.getDataAt(i, 7);
			String city = ExcelUtils.getDataAt(i, 8);
			String state = ExcelUtils.getDataAt(i, 9);
			String zip = ExcelUtils.getDataAt(i, 10);
			String country = ExcelUtils.getDataAt(i, 11);
			rp.fillOutRegistration(id, pass, repeatPass, firstName, lastName, email, phone, addr1, addr2, city, state, zip, country);
			sa.assertTrue(rp.isRegistrationSuccessful());
		}
		
		sa.assertAll();
	}
	@Test
	public void logInTest() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("signInLink"));
		ExcelUtils.setExcell(this.locators.getProperty("data_source"));
		ExcelUtils.setWorkSheet(1);
		SignInPage sip = new SignInPage(driver, locators, waiter);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String id = ExcelUtils.getDataAt(i, 0);
			String pass = ExcelUtils.getDataAt(i, 1);
			sip.logIn(id, pass);
			sa.assertTrue(sip.successfulLogIn());
			sip.clickSignOut();
			driver.navigate().to(this.locators.getProperty("signInLink"));
	}
		sa.assertAll();
}
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
