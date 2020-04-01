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

import pages.SignInPage;
import utils.ExcelUtils;

public class SignInTest {
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
		public void logInTest() throws InterruptedException {
			driver.navigate().to(this.locators.getProperty("sign_in_page_url"));
			ExcelUtils.setExcell(this.locators.getProperty("data_source"));
			ExcelUtils.setWorkSheet(1);
			SignInPage sip = new SignInPage(driver, locators, waiter);
			for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
				String id = ExcelUtils.getDataAt(i, 0);
				String pass = ExcelUtils.getDataAt(i, 1);
				sip.logIn(id, pass);
				sa.assertTrue(sip.successfulLogIn());
				sip.clickSignOut();
				driver.navigate().to(this.locators.getProperty("sign_in_page_url"));
			}
			sa.assertAll();
		}

		@AfterClass
		public void afterClass() {
			this.driver.close();
		}
	}
}
