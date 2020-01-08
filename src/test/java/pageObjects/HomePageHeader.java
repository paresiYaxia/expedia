package pageObjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.PageBase;
import utilities.reportRelated.Log4jManager;

public class HomePageHeader extends PageBase {
	
	
	/*-----------url section----------------*/
	private String url = "https://www.expedia.ca/";
	
	
	/* ============= elements ================= */
	

	//	Primary Section
	
	@FindBy(xpath = "//a[@id='header-logo']")
	@CacheLookup
	private WebElement headerLogo;
	
	@FindBy(xpath = "//li[@id='header-partner-add-hotel-container']")
	@CacheLookup
	private WebElement listYourProperty;
	
	// account menu ---------------------------------------------------
	@FindBy(xpath = "//button[@id='header-account-menu']")
	@CacheLookup
	private WebElement accountMenu;
	
	@FindBy(xpath = "//a[@id='account-signin']")
	@CacheLookup
	private WebElement accountSignIn;
	
	
	@FindBy(xpath = "//a[@id='account-register']")
	@CacheLookup
	private WebElement accountRegister;
	
	@FindBy(xpath = "//input[@id='gss-signup-first-name']")
	@CacheLookup
	private WebElement firstNameBx;
	
	@FindBy(xpath = "//input[@id='gss-signup-last-name']")
	@CacheLookup
	private WebElement lastNameBx;
	
	@FindBy(xpath = "//input[@id='gss-signup-email']")
	@CacheLookup
	private WebElement emailBx;
	
	@FindBy(xpath = "//input[@id='gss-signup-password']")
	@CacheLookup
	private WebElement passwordBx;

	@FindBy(xpath = "//input[@id='gss-signup-join-program-check']")
	@CacheLookup
	private WebElement joinRewards_Box;
	
	@FindBy(xpath = "//input[@id='gss-marketing-email-opt-in-check']")
	@CacheLookup
	private WebElement marketingEmail_Box;
	
	@FindBy(xpath = "//button[@id='gss-signup-submit']")
	@CacheLookup
	private WebElement signUp_btn;
	
	@FindBy(xpath = "(//iframe[@title='recaptcha challenge'])[1]")
	@CacheLookup
	private WebElement iframeCapcha;
	
	//@id='recaptcha-verify-button'
	
	@FindBy(xpath = "//div[@id='recaptcha-verify-button']")
	@CacheLookup
	private WebElement skip_btn;
	 
	//----------------------------------------------------
	
	
	
	@FindBy(xpath = "//a[@id='header-history']")
	@CacheLookup
	private WebElement myList;
	
	@FindBy(xpath = "//button[@id='header-trip-menu']")
	@CacheLookup
	private WebElement manageTrips;
	
	@FindBy(xpath = "//button[@id='header-support-menu']")
	@CacheLookup
	private WebElement support;
	
	@FindBy(xpath = "//a[@id='header-language-3084']")
	@CacheLookup
	private WebElement language;
	
	
	//	 Utility Section
	
	@FindBy(xpath = "//a[@id='primary-header-hotel']")
	@CacheLookup
	private WebElement hotels;
	
	@FindBy(xpath = "//a[@id='primary-header-package']")
	@CacheLookup
	private WebElement flightHotel;
	
	@FindBy(xpath = "//a[@id='primary-header-flight']")
	@CacheLookup
	private WebElement flights;
	
	@FindBy(xpath = "//a[@id='primary-header-threepp']")
	@CacheLookup
	private WebElement allInclusiveVacations;
	
	@FindBy(xpath = "//a[@id='primary-header-car']")
	@CacheLookup
	private WebElement car;
	
	@FindBy(xpath = "//a[@id='primary-header-vacationRental']")
	@CacheLookup
	private WebElement vacationRental;
	
	@FindBy(xpath = "//a[@id='primary-header-cruise']")
	@CacheLookup
	private WebElement cruises;
	
	@FindBy(xpath = "//a[@id='primary-header-activity']")
	@CacheLookup
	private WebElement thingsToDo;
	
	@FindBy(xpath = "//a[@id='primary-header-rewards']")
	@CacheLookup
	private WebElement rewards;
	
	@FindBy(xpath = "//a[@id='primary-header-deals']")
	@CacheLookup
	private WebElement deals; 
	

	
	/* ============= constructor ================= */
	
	public HomePageHeader() {
		// super(driver);
		PageFactory.initElements(driver, this);
		driver.get(url);
		Log4jManager.info("we get the web");

	}
	
	/* ============= functions and assertions ================= */
	
	
	public HomePageHeader createAnAccount(String firstName,String lastName,String email,String passWord) {
		logger.info("Enter new user first name, last name, email, password");
		accountMenu.click();
		wait.waitTillVisible(accountRegister);
		accountRegister.click();
		wait.waitTillVisible(firstNameBx);
		firstNameBx.clear();
		firstNameBx.sendKeys(firstName);
		lastNameBx.clear();
		lastNameBx.sendKeys(lastName);
		emailBx.clear();
		emailBx.sendKeys(email);
		passwordBx.clear();
		passwordBx.sendKeys(passWord);
		joinRewards_Box.click();
		wait.customWait(2);
		signUp_btn.click();
		Log4jManager.info("Filled in Sign Up section inofrmation from input ");
		return this;

	}
	
	public HomePageHeader assertResgisterImageSelect() {
		driver.switchTo().frame(iframeCapcha);
		logger.info("waiting for assertion");
		wait.waitTillVisible(iframeCapcha);
		assertTrue(skip_btn.isDisplayed());
		wait.customWait(3);
		return this;
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
/* ============= End ================= */
