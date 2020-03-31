package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage extends BasicPage {

	public SignInPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		super(driver, locators, waiter);
	}

	public WebElement getRegistrationLink() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("registration_link")));
	}

	public void goToRegistrationPage() {
		this.getRegistrationLink().click();
	}

	public WebElement getUsernameInput() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("username_input")));
	}

	public void setUsername(String username) {
		this.getUsernameInput().clear();
		this.getUsernameInput().sendKeys(username);
	}

	public WebElement getPasswordInput() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("password_input")));
	}

	public void setPassword(String password) {
		this.getPasswordInput().clear();
		this.getPasswordInput().sendKeys(password);
	}

	public WebElement getLoginBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("loginBtn")));
	}

	public void clickLogin() {
		this.getLoginBtn().click();
	}

	public WebElement getSignOut() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signOut")));
	}

	public void clickSignOut() {
		this.getSignOut().click();
	}

	public WebElement getMyAccount() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("myAccount")));
	}

	public void logIn(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
		this.clickLogin();
	}

	public boolean successfulLogIn() {
		boolean correct = true;
		try {
			this.getMyAccount().isDisplayed();
		} catch (Exception e) {
			correct = false;
		}
		return correct;
	}
}
