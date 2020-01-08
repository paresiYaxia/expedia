package utilities.elements;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import utilities.reportRelated.Log4jManager;

public class WaitController {
	private WebDriver driver;

	public WaitController(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Wait till the element to be visible, after 40 seconds, if the element is
	 * still not visible it will throw an NoSuchElementException
	 *
	 * @param element
	 */
	public void waitTillVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			Log4jManager.error("Wait Error: element was not visible after 60 seconds.");
		}
	}

	/**
	 * Waits till the visibility of element provided by the locater is achieved
	 *
	 * @param locator
	 */
	public void waitTillVisibilityOfElement(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			Log4jManager.error("Wait Error: element with BY LOCATOR info given was not visible even after 40s.");
		}
	}

	/**
	 * Wait till the text to be present, after 40 seconds, if the text is not
	 * present it will throw an NoSuchElementException
	 *
	 * @param by
	 */
	public void waitTillTextToBePresent(By by, String txt) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(by, txt));
		} catch (Exception e) {
			Log4jManager.error("Wait Error: the text: " + txt + ", is not present.");
		}
	}

	/**
	 * Wait till the text to be present, after 40 seconds, if the text is not
	 * present it will throw an NoSuchElementException
	 *
	 * @param element
	 */
	public void waitForTextToAppear(WebElement element, String textToAppear) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 300);
			wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
		} catch (Exception e) {
			Log4jManager.error("Wait Error: the text: " + textToAppear + ", did not appear after wait.");
		}
	}

	/**
	 * Waits till the element to be invisible.
	 *
	 * @param element
	 */
	public void waitTillInvisible(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			Log4jManager.error("Wait Error: the element did not become invisible even after 60 seconds");
		}
	}

	/**
	 * Waits till the element is click-able or not, if the element is click-able, it
	 * will return the click-able element
	 *
	 * @param element
	 * @return
	 */
	public void waitTillClickable(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			Log4jManager.error("Wait Error: element was not clicable even after 60 seconds later.");
		}
	}

	/***
	 * Waits till element specified with By object is found, if it finds the element
	 * it will return the element as WebElement object, otherwise it will print
	 * stack trace and and return null.
	 *
	 * @param by
	 */
	public WebElement fluentWaitTillElemFound(final By by) {
		WebElement targetElem = null;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(120, TimeUnit.SECONDS)
					.pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
			targetElem = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(by);
				}
			});
		} catch (Exception ex) {
			Log4jManager.error("Wait Error: the element was not found even after fluently waiting for 120 seconds.");
		}
		return targetElem;
	}

	/**
	 * wait for a page to complete loading before doing other actions.
	 *
	 * @param driver
	 */
	public void waitForPageLoad() {
		try {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(pageLoadCondition);
		} catch (Exception e) {
			Log4jManager.error("Wait Error: Page faild to load even after 60 seconds.");
		}
	}

	/**
	 * Waits as long as the user specifies, the time unit is SECOND.
	 *
	 * @param element
	 */
	public void customWait(int inSeconds) {
		try {
			Thread.sleep(inSeconds * 1000);
		} catch (InterruptedException ex) {
			Log4jManager.error("Wait Error: failed to wait for specified seconds: " + inSeconds);
		}
	}

}
