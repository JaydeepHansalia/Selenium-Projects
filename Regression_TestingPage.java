package com.org.regressionPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;

import com.commonlibrary.CommonFunctions;

public class Regression_TestingPage extends CommonFunctions {
	
	@FindBy(how = How.XPATH, using = "//td[@id='content']/div[14]/div[2]/div[1]/a/img")
	private WebElement firstad;

	public void homepageAd() throws InterruptedException {
		// Verify first ad
		//WebElement firstad = driver.findElement(By.xpath("//td[@id='content']/div[14]/div[2]/div[1]/a/img"));
		String firstadhref = driver.findElement(By.xpath("//td[@id='content']/div[14]/div[2]/div[1]/a"))
				.getAttribute("href");
		firstad.click();
		System.out.println("Click on first add");
		
		// Switch to another tab
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(2000);
		String tab2url = driver.getCurrentUrl();

		if (tab2url.contains(firstadhref))
			Reporter.log("User redirected to first ad", true);
		else
			Reporter.log("User not redirected to first ad", false);
		driver.close();
		driver.switchTo().window(tabs2.get(0));

		// Verify second ad
		WebElement secondad = driver.findElement(By.xpath("//td[@id='content']/div[14]/div[2]/div[2]/a/img"));
		String secondadhref = driver.findElement(By.xpath("//td[@id='content']/div[14]/div[2]/div[2]/a"))
				.getAttribute("href");
		secondad.click();

		// Switch to another tab
		ArrayList<String> tabs_2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs_2.get(1));
		Thread.sleep(2000);
		String tab2_url = driver.getCurrentUrl();
		if (tab2_url.contains(secondadhref))
			Reporter.log("User redirected to second ad", true);
		else
			Reporter.log("User not redirected to second ad", true);
		driver.close();
		driver.switchTo().window(tabs2.get(0));
	}

	public void forgotPasswordLink() throws InterruptedException {
		WebElement forgotpassword = driver.findElement(By.xpath("//span[@id='link-forgot-password']"));
		// Click on forgot password link
		forgotpassword.click();

		// Verify forgot password popup open or not
		WebElement forgotpasswordpopup = driver.findElement(By.xpath("//div[@id='forgot-password-form']"));
		if (forgotpasswordpopup.isDisplayed())
			Reporter.log("Forgot your passwor pop up window appeared", true);
		else
			Reporter.log("Forgot your passwor pop up window not appeared", false);

		// Click on cancel button from forgot password popup
		driver.findElement(By.xpath("//button[@id='btn-send-email-cancel']")).click();

	}

	public void login() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='usernameBox']")).sendKeys("amit2");
		driver.findElement(By.xpath("//input[@id='passwordBox']")).sendKeys("temp1234");
		driver.findElement(By.xpath("//input[@id='ph-logon-button-green']")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		try {
			if ((driver.findElement(By.xpath("//div[@id='ph-system-message-box' and @style='display: block;']")))
					.isDisplayed())
				driver.findElement(By.xpath("//button[@id='btn-continue']")).click();
			else
				Reporter.log("Sign in without popup", true);
		} catch (NoSuchElementException e) {
			Reporter.log("Sign in without popup", true);
		}
		
		//Click on any alert popup
		Thread.sleep(2000);
		WebElement alert_popup = driver.findElement(By.xpath("//div[@id='ph-btn-alert-close']"));
		
		if(alert_popup.isDisplayed())
			alert_popup.click();
		
		String url = driver.getCurrentUrl();

		if (url.contains("HomePage"))
			Reporter.log("User successfully redirected to home page", true);
		else
			Reporter.log("User not redirected to home page", true);

	}

	public void clickOnAllTabInHeader() throws IOException {

		for (int i = 2; i <= 5; i++) {
			driver.findElement(By.xpath("//ul[@id='ph-menu']/li[" + i + "]/a")).click();
			String Tab = driver.findElement(By.xpath("//ul[@id='ph-menu']/li[" + i + "]/a/img")).getText();
			String url = driver.getCurrentUrl();
			int responcecode = verifyResponceCode(url);

			if (responcecode == 200)
				Reporter.log("User successfully redirected to " + Tab + " tab", true);
			else
				Reporter.log("User not redirected to " + Tab + " tab", true);
		}

		for (int i = 2; i <= 3; i++) {
			driver.findElement(By.xpath("//div[@id='ph-btn-menu-content']/button[" + i + "]")).click();
			String url = driver.getCurrentUrl();
			int responcecode = verifyResponceCode(url);

			if (responcecode == 200)
				Reporter.log("User successfully redirected to saved report tab", true);
			else
				Reporter.log("User not redirected to saved report tab", true);
		}

	}

	public void clickOnUserProfileDropdown() throws IOException, InterruptedException {
		driver.findElement(By.xpath("//ul[@id='ph-panelBar-andew-wiseman']")).click();

		// Click on User Profile
		driver.findElement(By.xpath("//ul[@class='k-group k-panel']/li[6]")).click();
		String url = driver.getCurrentUrl();
		int responcecode = verifyResponceCode(url);

		if (responcecode == 200)
			Reporter.log("User successfully redirected to user profiel", true);
		else
			Reporter.log("User not redirected to user profiel", true);

		// Click on Sign Out Button
		driver.findElement(By.xpath("//ul[@id='ph-panelBar-andew-wiseman']")).click();
		driver.findElement(By.xpath("//ul[@class='k-group k-panel']/li[8]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='btn-logOut-yes']")).click();
		WebElement Signin_Button = driver.findElement(By.xpath("//input[@id='ph-logon-button-green']"));

		if (Signin_Button.isDisplayed())
			Reporter.log("User successfully Logout", true);
		else
			Reporter.log("User not Logout", true);

	}

	public void homeScreen() throws InterruptedException, IOException {
		login();

		driver.findElement(By.xpath("//ul[@id='ph-menu']/li[1]/a")).click();
		Thread.sleep(5);

		// Verify All Retail Categories Graph
		WebElement All_Retail_Categories = driver
				.findElement(By.xpath("//div[@class='iframe-border k-block ph-window-border']"));

		if (All_Retail_Categories.isDisplayed())
			Reporter.log("All Retail Categories graph is visible", true);
		else
			Reporter.log("All Retail Categories graph is not visible", true);

		// Verify Channel DIY Index and Channel Dept Store Index Graphs
		for (int i = 1; i <= 2; i++) {
			WebElement Graphs = driver.findElement(By.xpath("//div[@id='ph-hompage-left-tag']/div[2]/div[" + i + "]"));
			String Graph2_3 = driver.findElement(By.xpath("//div[@id='ph-hompage-left-tag']/div[2]/div[" + i
					+ "]//div[@class='k-header k-iframe-header ph-window-head']")).getText();

			if (Graphs.isDisplayed())
				Reporter.log("" + Graph2_3 + " is visible", true);
			else
				Reporter.log("" + Graph2_3 + " is not visible", true);
		}

		// Verify Latest News Section
		WebElement latestnewsbox = driver.findElement(By.xpath("//div[@id='ph-latest-news-head']"));

		if (latestnewsbox.isDisplayed())
			Reporter.log("Latest news section is visible", true);
		else
			Reporter.log("Latest news section is not visible", true);

		// Verify External Links
		int external_link_size = driver.findElements(By.xpath("//div[@id='ph-external-links-content']/div")).size();

		for (int i = 1; i <= external_link_size - 1; i++) {
			String href = driver.findElement(By.xpath("//div[@id='ph-external-links-content']/div[" + i + "]/a"))
					.getAttribute("href");
			System.out.println(href);
			int responcecode = verifyResponceCode(href);

			if (responcecode == 200)
				Reporter.log("User successfully redirected to " + href + "", true);
			else
				Reporter.log("User not redirected to " + href + "", true);
		}
	}

	public void reportScreen() throws InterruptedException {
		driver.findElement(By.xpath("//ul[@id='ph-menu']/li[2]/a")).click();
		Thread.sleep(5);

		// Verify Report Sidebar

		if ((driver.findElement(By.xpath("//div[@id='ph-report-treeview']"))).isDisplayed())
			Reporter.log("Report sidebar is visible", true);
		else
			Reporter.log("Report sidebar is not visible", true);

		// Click on collapse arrow
		Thread.sleep(2000);
		WebElement collapsearrow = driver.findElement(By.xpath("//div[contains(@class,'k-collapse-prev')]"));

		if (collapsearrow.isDisplayed())
			Reporter.log("Report session expanded successfully", true);
		else
			Reporter.log("Report session not expanded", true);

		collapsearrow.click();

		WebElement expandarrow = driver.findElement(By.xpath("//div[contains(@class,'k-expand-prev')]"));

		if (expandarrow.isDisplayed())
			Reporter.log("Report session collapsed successfully", true);
		else
			Reporter.log("Report session not collapsed", true);

		expandarrow.click();

	}

	public void homePageLink() throws InterruptedException {
		driver.findElement(By.xpath("//ul[@id='ph-menu']/li[5]/a")).click();
		Thread.sleep(5);

		// Verify Home Page Links

		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[1]")).click();
		Thread.sleep(2000);

		int size = driver.findElements(By.xpath("//table[@id='table-links']/tbody/tr")).size();

		for (int i = 1; i <= size; i++) {
			String link = driver.findElement(By.xpath("//table[@id='table-links']/tbody/tr[" + i + "]/td[2]/input"))
					.getAttribute("value");

			WebElement toggle_button = driver
					.findElement(By.xpath("//table[@id='table-links']/tbody/tr[" + i + "]/td[3]/div/label"));
			String toggle_button_status = toggle_button.getAttribute("class");

			toggle_button.click();
			String toggle_button_status_after_click = toggle_button.getAttribute("class");

			if (toggle_button_status != toggle_button_status_after_click)
				Reporter.log("" + link + "-------Toggle button working fine", true);
			else
				Reporter.log("" + link + "-------Toggle button not working fine", true);

			toggle_button.click();
		}
	}

	public void homePageNewsArticles() throws InterruptedException {

		// Click on Home Page News Articles
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[2]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contentArea']")));

		WebElement home_page_news_articles_box = driver.findElement(By.xpath("//div[@class='contentArea']"));
		if (home_page_news_articles_box.isDisplayed())
			Reporter.log("Home Page News Articles box is visible", true);
		else
			Reporter.log("Home Page News Articles box is not visible", true);

		// Click on edit button
		driver.findElement(
				By.xpath("//div[@class='k-grid-content']/table/tbody/tr[1]//div[@class='editIcon grid-button']"))
				.click();
		Thread.sleep(2000);

		// Enter the title
		driver.findElement(By.xpath("//input[@id='Title']")).sendKeys("Test12345");

		// Click on save article button
		WebElement save_article_button = driver
				.findElement(By.xpath("//input[@class='orange-button btn-submit-news']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", save_article_button);
		save_article_button.click();
		Thread.sleep(2000);

		String title = driver.findElement(By.xpath("//div[@class='k-grid-content']/table/tbody/tr[1]/td[1]")).getText();
		System.out.println(title);

		if (title.contains("Test12345"))
			Reporter.log("Edit button working fine", true);
		else
			Reporter.log("Edit button not working fine", true);

		// Click on add article button
		driver.findElement(By.xpath("//input[@id='btnAddNews']")).click();
		driver.findElement(By.xpath("//input[@id='Title']")).sendKeys("Test12345");
		driver.findElement(By.xpath("//textarea[@id='Message']")).sendKeys("Test12345");

		WebElement sourceElement = driver
				.findElement(By.xpath("//ul[@class='sortable1 connectedSortable ui-sortable']/li[118]/span[1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sourceElement);
		Thread.sleep(500);
		WebElement destiElement = driver
				.findElement(By.xpath("//ul[@class='sortable2 connectedSortable ui-sortable']"));
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, destiElement).build().perform();

		driver.findElement(By.xpath("//input[@class='orange-button btn-submit-news']")).click();

		if (title == "Test12345")
			Reporter.log("New article successfully added", true);
		else
			Reporter.log("New article not added", true);

		driver.findElement(
				By.xpath("//div[@class='k-grid-content']/table/tbody/tr[1]//div[@class='removeIcon grid-button']"))
				.click();

		driver.findElement(By.xpath("//input[@id='btnYes']")).click();

	}

	public void endUserLicenseAgreement() throws InterruptedException {

		// Click on End User License Agreement
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[3]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contentArea']")));

		WebElement endUserLicenseAgreementcontent = driver.findElement(By.xpath("//div[@class='contentArea']"));
		if (endUserLicenseAgreementcontent.isDisplayed())
			Reporter.log("End User License Agreement is visible", true);
		else
			Reporter.log("End User License Agreement is not visible", true);
	}

	public void intellectualPropertyAgreement() throws InterruptedException {

		// Click on Intellectual Property Agreement
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[4]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contentArea']")));

		WebElement intellectualPropertyAgreement = driver.findElement(By.xpath("//div[@class='contentArea']"));
		if (intellectualPropertyAgreement.isDisplayed())
			Reporter.log("Intellectual Property Agreement is visible", true);
		else
			Reporter.log("Intellectual Property Agreement is not visible", true);
	}

	public void signInPageAds() throws InterruptedException {

		// Click on Sign In Page Ads
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[5]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='editor-banner-advertising']")));

		WebElement signInPageAds = driver.findElement(By.xpath("//div[@class='editor-banner-advertising']"));
		if (signInPageAds.isDisplayed())
			Reporter.log("Sign In Page Ads is visible", true);
		else
			Reporter.log("Sign In Page Ads is not visible", true);
	}

	public void savedReports() throws InterruptedException {

		// Click on Saved Reports
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[6]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contentArea']")));

		WebElement savedReports = driver.findElement(By.xpath("//div[@class='contentArea']"));
		if (savedReports.isDisplayed())
			Reporter.log("Saved Reports is visible", true);
		else
			Reporter.log("Saved Reports is not visible", true);

		// Scroll on the specific report
		WebElement report = driver.findElement(By.xpath("//td[text()='Store Sales Date Select_MOB727']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", report);
		Thread.sleep(200);

		// Click on user icon button
		driver.findElement(By.xpath("//tr[@title='This is my imp report']/td[5]/div")).click();

		// Delete the user
		WebElement user = driver
				.findElement(By.xpath("//div[@class='users-list arrow_box_right']//li[1]/span[text()='Admin, Amit']"));
		Actions action = new Actions(driver);
		action.moveToElement(user).build().perform();

		driver.findElement(By.xpath("//div[@class='users-list arrow_box_right']//li[1]//div[@class='removeIcon']"))
				.click();

		driver.findElement(By.xpath("//input[@id='btnYes']")).click();
		driver.findElement(By.xpath("//tr[@title='This is my imp report']/td[5]/div")).click();
		Thread.sleep(200);

		try {
			if (user.isDisplayed())
				Reporter.log("User not removed", true);
			else
				Reporter.log("User successfully removed", true);
		} catch (StaleElementReferenceException e) {
			Reporter.log("User successfully removed", true);
		}
	}

	public void contatUsForm() throws InterruptedException {

		// Click on Contat Us Form
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[7]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='ajaxResult']//div[@class='k-block']")));

		WebElement contatusform = driver.findElement(By.xpath("//div[@id='ajaxResult']//div[@class='k-block']"));
		boolean flag = contatusform.isDisplayed();
		verifyTrue(flag, "Contat Us Form is visible", "Contat Us Form is not visible");
	}

	public void signInCopyright() throws InterruptedException {

		// Click on Contat Us Form
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[8]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contentArea']")));

		WebElement signInCopyright = driver.findElement(By.xpath("//div[@class='contentArea']"));
		boolean flag = signInCopyright.isDisplayed();
		verifyTrue(flag, "Sign In Copyright is visible", "Sign In Copyright is not visible");
	}

	public void Groups_Users() throws InterruptedException {

		// Click on Contat Us Form
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[9]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contentArea']")));

		WebElement Groups_Users = driver.findElement(By.xpath("//div[@class='contentArea']"));
		boolean flag = Groups_Users.isDisplayed();
		verifyTrue(flag, "Groups/Users is visible", "Groups/Users is not visible");
	}

	public void subscriptions() throws InterruptedException {

		// Click on Contat Us Form
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[10]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contentArea']")));

		WebElement subscriptions = driver.findElement(By.xpath("//div[@class='contentArea']"));
		boolean flag = subscriptions.isDisplayed();
		verifyTrue(flag, "Subscriptions is visible", "Subscriptions is not visible");
	}

	public void systemAlerts() throws InterruptedException {

		// Click on Contat Us Form
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[11]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contentArea']")));

		WebElement systemAlerts = driver.findElement(By.xpath("//div[@class='contentArea']"));
		boolean flag = systemAlerts.isDisplayed();
		verifyTrue(flag, "System Alerts is visible", "System Alerts is not visible");
	}

	public void helpGuide() throws InterruptedException {

		// Click on Contat Us Form
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[12]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='ph-help-page-iframe']")));

		WebElement helpGuide = driver.findElement(By.xpath("//div[@id='ph-help-page-iframe']"));
		boolean flag = helpGuide.isDisplayed();
		verifyTrue(flag, "Help Guide is visible", "Help Guide is not visible");
	}

	public void createAUserEmail() throws InterruptedException {

		// Click on Contat Us Form
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[13]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contentArea']")));

		WebElement createAUserEmail = driver.findElement(By.xpath("//div[@class='contentArea']"));
		boolean flag = createAUserEmail.isDisplayed();
		verifyTrue(flag, "Create A User Email is visible", "Create A User Email is not visible");
	}

	public void staffEmailAlerts() throws InterruptedException {

		// Click on Contat Us Form
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[14]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='ajaxResult']")));

		WebElement staffEmailAlerts = driver.findElement(By.xpath("//div[@id='ajaxResult']"));
		boolean flag = staffEmailAlerts.isDisplayed();
		verifyTrue(flag, "Staff Email Alerts is visible", "Staff Email Alerts is not visible");
	}

	public void emailTemplates() throws InterruptedException {

		// Click on Contat Us Form
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[15]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contentArea']")));

		WebElement emailTemplates = driver.findElement(By.xpath("//div[@class='contentArea']"));
		boolean flag = emailTemplates.isDisplayed();
		verifyTrue(flag, "Email Templates is visible", "Email Templates is not visible");
	}

	public void systemMessages() throws InterruptedException {

		// Click on Contat Us Form
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[16]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='ajaxResult']")));

		WebElement systemMessages = driver.findElement(By.xpath("//div[@id='ajaxResult']"));
		boolean flag = systemMessages.isDisplayed();
		verifyTrue(flag, "System Messages is visible", "System Messages is not visible");
	}

	public void loadingMessages() throws InterruptedException {

		// Click on Contat Us Form
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[17]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='ajaxResult']")));

		WebElement loadingMessages = driver.findElement(By.xpath("//div[@id='ajaxResult']"));
		boolean flag = loadingMessages.isDisplayed();
		verifyTrue(flag, "Loading Messages is visible", "Loading Messages is not visible");
	}

	public void browserStatistics() throws InterruptedException {

		// Click on Contat Us Form
		driver.findElement(By.xpath("//td[@class='admMenu']/div/div[18]")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contentArea']")));

		WebElement browserStatistics = driver.findElement(By.xpath("//div[@class='contentArea']"));
		boolean flag = browserStatistics.isDisplayed();
		verifyTrue(flag, "Browser Statistics is visible", "Browser Statistics is not visible");
	}

	public void alertsPopup() throws InterruptedException {
		// Verify Alert is present or not
		WebElement alert_button = driver.findElement(By.xpath("//button[@id='ph-alerts-button']"));
		WebElement alert_count = driver
				.findElement(By.xpath("//button[@id='ph-alerts-button']//span[@id='ph-btn-alert-count']"));

		String getalertcount = alert_count.getAttribute("style");

		if (!(getalertcount.contains("display: none;"))) {
			alert_button.click();
			Thread.sleep(2000);
			WebElement alert_popup = driver.findElement(By.xpath("//div[@id='ph-btn-alert-close']"));
			boolean flag = alert_popup.isDisplayed();

			verifyTrue(flag, "Alerts Popup is visible", "Alerts Popup is not visible");
			alert_popup.click();
		} else
			Reporter.log("No any alerts are present", true);
	}

	public void userProfile() throws IOException, InterruptedException {

		// Click on user profile dropdown
		driver.findElement(By.xpath("//ul[@id='ph-panelBar-andew-wiseman']")).click();

		// Click on each option
		int size = driver.findElements(By.xpath("//li[contains(@class,'k-item k-state-default')]")).size();

		for (int i = 1; i <= size - 1; i++) {
			
			String option = driver
					.findElement(
							By.xpath("//li[contains(@class,'k-item k-state-default')][" + i + "]/a[@class='k-link']"))
					.getText();
			
			driver.findElement(By.xpath("//li[contains(@class,'k-item k-state-default')][" + i + "]")).click();
			String url = driver.getCurrentUrl();
			int responcecode = verifyResponceCode(url);

			if (responcecode == 200)
				Reporter.log("User successfully redirected to " + option + "", true);
			else
				Reporter.log("User not redirected to " + option + "", true);

			driver.findElement(By.xpath("//ul[@id='ph-panelBar-andew-wiseman']")).click();
		}

		// Click on sign out button
		driver.findElement(By.xpath("//li[@id='ph-btnpanel-logout-button']")).click();
		Thread.sleep(2000);

		WebElement signoutpopup = driver.findElement(By.xpath("//button[@class='gray-button']"));
		boolean flag = signoutpopup.isDisplayed();

		verifyTrue(flag, "Sign Out screen is visible", "Sign Out screen is not visible");
		signoutpopup.click();
	}

}
