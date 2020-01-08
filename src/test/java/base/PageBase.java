package base;

import org.openqa.selenium.WebDriver;

import utilities.elements.AlertHandler;
import utilities.elements.FindElementUtil;
import utilities.elements.JavascriptHelper;
import utilities.elements.WaitController;
import utilities.elements.WebElementUtil;
import utilities.reportRelated.TestListener;

//Base of every other page object classes, declared as an abstract class
//so it cannot be instantiated and no other object can be created.
public class PageBase extends TestListener {

	// since we extends TestListener, we could get the driver from there
	// so we no longer need to declare this driver
	// protected WebDriver driver;

	// data member
	protected JavascriptHelper jsHelper;
	protected WaitController wait;
	protected FindElementUtil by;
	protected WebElementUtil elementUtils;
	protected AlertHandler alert;
	
	
	
	

	// Constructor - Initializations
	public PageBase() {
		// this.driver = incomingDriver;
		// we also change the argument for our data member
		this.jsHelper = new JavascriptHelper(driver);
		this.wait = new WaitController(driver);
		this.by = new FindElementUtil(driver);
		this.elementUtils = new WebElementUtil(driver);
		this.alert = new AlertHandler(driver);

	}

	/**
	 * Returns the WebDriver object
	 *
	 * @return Driver instance from the parent class
	 */
	protected WebDriver driver() {
		return driver;
	}

}
