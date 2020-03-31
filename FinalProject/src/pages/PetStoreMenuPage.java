package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetStoreMenuPage extends BasicPage {

	public PetStoreMenuPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		super(driver, locators, waiter);
	}

	public List<WebElement> getAllLinks() {
		return this.driver.findElements(By.xpath("//*[@href]"));
	}

	public List<WebElement> getSidebarLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("sidebar_links")));
	}

	// method for finding the link to the desired page
	public WebElement getSidebarLink(String page) {
		List<WebElement> links = this.getSidebarLinks();
		WebElement link = links.get(0);

		for (int i = 0; i < links.size(); i++) {
			if (links.get(i).getAttribute("href").toLowerCase().contains(page)) {
				link = links.get(i);
				break;
			}
		}
		return link;
	}

	public List<WebElement> getPicLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("pic_links")));
	}

	public WebElement getPicLink(String page) {
		List<WebElement> links = this.getPicLinks();
		WebElement link = links.get(0);

		for (int i = 0; i < links.size(); i++) {
			if (links.get(i).getAttribute("href").toLowerCase().contains(page)) {
				link = links.get(i);
				break;
			}
		}
		return link;
	}

	public List<WebElement> getQuickLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("quick_links")));
	}

	public WebElement getQuickLink(String page) {
		List<WebElement> links = this.getQuickLinks();
		WebElement link = links.get(0);

		for (int i = 0; i < links.size(); i++) {
			if (links.get(i).getAttribute("href").toLowerCase().contains(page)) {
				link = links.get(i);
				break;
			}
		}
		return link;
	}

	public WebElement getPageName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("element")));
	}

	public WebElement getLogInBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("login_btn")));
	}

	public WebElement getSignIn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("sign_in_link")));
	}

	public void clickSignIn() {
		this.getSignIn().click();
	}

	public boolean allUrlStatusWorking() {
		boolean working = false;
		List<WebElement> links = this.getAllLinks();
		int status = 0;
		for (int i = 0; i < links.size(); i++) {
			status = verifyURLStatus(links.get(i).getAttribute("href"));
			if (status < 400) {
				working = true;
			}
		}
		return working;
	}

	// method for checking whether the link takes us to the correct page
	public boolean clickOnSidebarLink(String page) {
		boolean correct = false;
		this.getSidebarLink(page).click();
		if (this.getPageName().getText().toLowerCase().contains(page)) {
			correct = true;
		}

		return correct;
	}

	public boolean clickOnPicLInk(String page) {
		boolean correct = false;
		this.getPicLink(page).click();
		if (this.getPageName().getText().toLowerCase().contains(page)) {
			correct = true;
		}

		return correct;
	}

	public boolean clickOnQuickLink(String page) {
		boolean correct = false;
		this.getQuickLink(page).click();
		if (this.getPageName().getText().toLowerCase().contains(page)) {
			correct = true;
		}

		return correct;
	}

	public boolean checkSignInPage() {
		return this.getLogInBtn().isDisplayed();
	}

	public static int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

}
