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

	public List<WebElement> getSidebarLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("sidebar_links")));
	}

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

	public boolean checkSidebarLinkPages(String page) {
		boolean correct = true;
		try {
			this.getSidebarLink(page).click();
			String link = this.driver.findElement(By.xpath(this.locators.getProperty("element"))).getText();
			link.compareToIgnoreCase(page);
		} catch (Exception e) {
			correct = false;
		}
		return correct;
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

	public boolean checkPicLinkPages(String page) {
		boolean correct = true;
		try {
			this.getPicLink(page).click();
			String link = this.driver.findElement(By.xpath(this.locators.getProperty("element"))).getText();
			link.compareToIgnoreCase(page);
		} catch (Exception e) {
			correct = false;
		}
		return correct;
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

	public boolean checkQuickLinkPages(String page) {
		boolean correct = true;
		try {
			this.getQuickLink(page).click();
			String link = this.driver.findElement(By.xpath(this.locators.getProperty("element"))).getText();
			link.compareToIgnoreCase(page);
		} catch (Exception e) {
			correct = false;
		}
		return correct;
	}

	public WebElement getSignIn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("sign_in_link")));
	}

	public void clickSignIn() {
		this.getSignIn().click();
	}

	public boolean checkSignInPage() {
		boolean correct = true;
		try {
			this.clickSignIn();
			this.driver.findElement(By.xpath(this.locators.getProperty("login_btn")));
		} catch (Exception e) {
			correct = false;
		}
		return correct;
	}

	public List<WebElement> getAllLinks() {
		return this.driver.findElements(By.xpath("all_links"));
	}

	public boolean allUrlStatusWorking() {
		boolean working = true;
		List<WebElement> links = this.getAllLinks();
		int status = 0;
		for (int i = 0; i < links.size(); i++) {
			status = verifyURLStatus(links.get(i).getAttribute("href"));
			if (status > 400) {
				working = false;
			}
		}
		return working;
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
