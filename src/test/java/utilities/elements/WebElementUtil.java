package utilities.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.reportRelated.Log4jManager;

public class WebElementUtil {
	private WebDriver driver;

	// CONSTRUCTOR
	public WebElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Selects element with element text value
	 *
	 * @param elems Element List
	 * @param value Element with desired value
	 * @return null if element not found, otherwise found element
	 */
	public WebElement selectElementByTextValue(List<WebElement> elems, String value) {
		try {
			for (int i = 0; i < elems.size(); i++) {
				String actual = elems.get(i).getText();
				if (value.equalsIgnoreCase(actual)) {
					return elems.get(i);
				}
			}
			return null;
		} catch (Exception e) {
			Log4jManager.error("Failed to select element with text value: " + value);
		}
		return null;
	}

	/**
	 * This method guarantees that given checkbox element is selected
	 *
	 * @param checkBox checkbox element that needs to be clicked
	 */
	public void clickCheckBox(WebElement checkBox) {
		try {
			if (checkBox.isSelected() == true) {
				// given check-box is already selected one
				return;
			} else if (checkBox.isSelected() == false) {
				checkBox.click();
				return;
			}
			return;
		} catch (Exception e) {
			Log4jManager.error("Failed to click the specified checkBox");
		}
		return;
	}

	/**
	 * This method guarantees that given checkbox element is de-selected
	 *
	 * @param elem checkbox element that needs to be de-selected
	 */
	public void deselectCheckBox(WebElement elem) {
		try {
			if (elem.isSelected() == true) {
				elem.click();
				return;
			} else if (elem.isSelected() == false) {
				return;
			}
			return;
		} catch (Exception e) {
			Log4jManager.error("Failed to deselect the specified checkbox");
		}
		return;
	}

	/**
	 * From given list of elements, clicks element that is specified by the values
	 * parameters. Note that you have to know that text values that will be used for
	 * the selection in advance
	 *
	 * @param checkboxList list of elements
	 * @param values       text values of desired elements to be clicked
	 */
	public void clickMultipleCheckboxWith(List<WebElement> checkboxList, String... values) {
		try {
			for (int i = 0; i < checkboxList.size(); i++) {
				for (int v = 0; v < values.length; v++) {
					String elemText = checkboxList.get(i).getText();
					String valueText = values[v];
					if (elemText.equalsIgnoreCase(valueText)) {
						checkboxList.get(i).click();
					}
				} // for:values
			} // for:elems
			return;
		} catch (Exception e) {
			Log4jManager.error("Failed to check multiple check box with following values: " + values.toString());
		}
		return;
	}

	/**
	 * Checks whether the specified element with location information given with By
	 * exist or not.
	 *
	 * @param by
	 * @return TRUE if element exist, FALSE otherwise
	 */
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			Log4jManager
					.error("Element with specified location information not present, element info: " + by.toString());
			return false;
		}
	}

	/**
	 * Clicks the specified element with By object.
	 *
	 * @param by element that needs to be clicked
	 */
	public void clickElement(By by) {
		try {
			WebElement element = driver.findElement(by);
			element.click();
		} catch (Exception ex) {
			Log4jManager
					.error("Element with specified locaiton info cannot be clicked, element info: " + by.toString());
		}
	}

	public void highlight(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
					element);
		} catch (Exception e) {
			Log4jManager.error("Failed to hightlight specific element");
		}
	}

}
