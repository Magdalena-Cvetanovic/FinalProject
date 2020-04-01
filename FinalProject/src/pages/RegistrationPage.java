package pages;

import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasicPage {

	public RegistrationPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		super(driver, locators, waiter);
	}

	public WebElement getSignIn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("sign_in_link")));
	}

	public void goToSignIn() {
		this.getSignIn().click();
	}

	public WebElement getRegistration() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("registration_link")));
	}

	public void clickOnRegistrerNow() {
		this.getRegistration().click();
	}

	public void goToRegistration() {
		this.goToSignIn();
		this.clickOnRegistrerNow();
	}

	public WebElement getUserId() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("user_id")));
	}

	public void setUserName(String userid) {
		this.getUserId().clear();
		this.getUserId().sendKeys(userid);
	}

	public WebElement getNewPass() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("new_pass")));
	}

	public void setNewPass(String pass) {
		this.getNewPass().clear();
		this.getNewPass().sendKeys(pass);
	}

	public WebElement getRepeatPass() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("repeat_pass")));
	}

	public void setRepeatPass(String pass) {
		this.getRepeatPass().clear();
		this.getRepeatPass().sendKeys(pass);
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("first_name")));
	}

	public void setFirstName(String fName) {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(fName);
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("last_name")));
	}

	public void setLastName(String lName) {
		this.getLastName().clear();
		this.getLastName().sendKeys(lName);
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("email")));
	}

	public void setEmail(String email) {
		this.getEmail().clear();
		this.getEmail().sendKeys(email);
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("phone")));
	}

	public void setPhone(String phone) {
		this.getPhone().clear();
		this.getPhone().sendKeys(phone);
	}

	public WebElement getAddress1() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("address1")));
	}

	public void setAddress1(String address) {
		this.getAddress1().clear();
		this.getAddress1().sendKeys(address);
	}

	public WebElement getAddress2() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("address2")));
	}

	public void setAddress2(String address) {
		this.getAddress2().clear();
		this.getAddress2().sendKeys(address);
	}

	public WebElement getCity() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("city")));
	}

	public void setCity(String city) {
		this.getCity().clear();
		this.getCity().sendKeys(city);
	}

	public WebElement getState() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("state")));
	}

	public void setState(String state) {
		this.getState().clear();
		this.getState().sendKeys(state);
	}

	public WebElement getZip() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("zip")));
	}

	public void setZip(String zip) {
		this.getZip().clear();
		this.getZip().sendKeys(zip);
	}

	public WebElement getCountry() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("country")));
	}

	public void setCountry(String country) {
		this.getCountry().clear();
		this.getCountry().sendKeys(country);
	}

	public WebElement getLanguage() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("lang_select")));
	}

	public Select getLanguageSelect() {
		return new Select(this.getLanguage());

	}

	public void chooseLanguageByValue(String value) {
		this.getLanguageSelect().selectByValue(value);
	}

	public void chooseLanguageByIndex(int index) {
		this.getLanguageSelect().selectByIndex(index);
	}

	public WebElement getCategory() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("category_select")));
	}

	public Select getCategorySelect() {
		return new Select(this.getCategory());
	}

	public void chooseCategoryByValue(String value) {
		this.getCategorySelect().selectByValue(value);
	}

	public void chooseCategoryByIndex(int index) {
		this.getCategorySelect().selectByIndex(index);
	}

	public WebElement getMyList() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("enable_list")));
	}

	public void clickMyList() {
		this.getMyList().click();
	}

	public WebElement getMyBanner() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("enable_banner")));
	}

	public void clickMyBanner() {
		this.getMyBanner().click();
	}

	public WebElement getSaveBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("save_btn")));
	}

	public void clickSaveBtn() {
		this.getSaveBtn().click();
	}

	public WebElement getMap() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("map")));
	}

	public int getRandomLang() {
		Random rand = new Random();
		int language = rand.nextInt(2);
		return language;
	}

	public int getRandomCategory() {
		Random rand = new Random();
		int category = rand.nextInt(5);
		return category;
	}

	public boolean isRegistrationSuccessful() {
		boolean correct = true;
		try {
			this.getMap().isDisplayed();
		} catch (Exception e) {
			correct = false;
		}
		return correct;
	}

	public void fillOutRegistration(String id, String pass, String repeatPass, String firstName, String lastName,
			String email, String phone, String addr1, String addr2, String city, String state, String zip,
			String country) {
		this.setUserName(id);
		this.setNewPass(pass);
		this.setRepeatPass(repeatPass);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setAddress1(addr1);
		this.setAddress2(addr2);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setCountry(country);
		this.chooseLanguageByIndex(this.getRandomLang());
		this.chooseCategoryByIndex(this.getRandomCategory());
		this.clickMyList();
		this.clickMyBanner();
		this.clickSaveBtn();

	}

	public void fillRegistrationIncompletely1(String id, String pass, String firstName, String lastName, String email,
			String phone, String addr1, String addr2, String city, String state, String zip, String country) {
		this.setUserName(id);
		this.setNewPass(pass);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setAddress1(addr1);
		this.setAddress2(addr2);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setCountry(country);
		this.chooseLanguageByIndex(this.getRandomLang());
		this.chooseCategoryByIndex(this.getRandomCategory());
		this.clickMyList();
		this.clickMyBanner();
		this.clickSaveBtn();

	}

	public void fillRegistrationIncompletely2(String id, String pass, String firstName, String email, String phone,
			String addr1, String addr2, String city, String state, String zip, String country) {
		this.setUserName(id);
		this.setNewPass(pass);
		this.setRepeatPass(pass);
		this.setFirstName(firstName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setAddress1(addr1);
		this.setAddress1(addr2);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setCountry(country);
		this.chooseLanguageByIndex(this.getRandomLang());
		this.chooseCategoryByIndex(this.getRandomCategory());
		this.clickMyList();
		this.clickMyBanner();
		this.clickSaveBtn();

	}
}