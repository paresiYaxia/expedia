package utilities.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.reportRelated.Log4jManager;

public class FindElementUtil {

	private WebDriver driver;

	// CONSTRUCTOR
	public FindElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This will find element(s) using the ID attribute
	 *
	 * @param using element's id attribute value
	 * @return WebElement if found, null otherwise
	 */
	public WebElement id(String using) {
		WebElement elem;
		try {
			elem = driver.findElement(By.id(using));
			return elem;
		} catch (NoSuchElementException e) {
			Log4jManager.error("Error finding element with id: " + using);
			return null;
		}
	}

	/**
	 * This will find element(s) using the XPath expression
	 *
	 * @param using XPath string or pattern
	 * @return WebElement if found, null otherwise
	 */
	public WebElement xpath(String using) {
		WebElement elem;
		try {
			elem = driver.findElement(By.xpath(using));
			return elem;
		} catch (NoSuchElementException e) {
			Log4jManager.error("Error finding element with xpath: " + using);
			return null;
		}
	}

	/**
	 * This will find element(s) using the Name attribute
	 *
	 * @param using element's name attribute value
	 * @return WebElement if found, null otherwise
	 */
	public WebElement name(String using) {
		WebElement elem;
		try {
			elem = driver.findElement(By.name(using));
			return elem;
		} catch (NoSuchElementException e) {
			Log4jManager.error("Error finding element with name attribute: " + using);
			return null;
		}
	}

	/**
	 * This will find element(s) using the Class attribute value
	 *
	 * @param using element's class attribute value
	 * @return WebElement if found, null otherwise
	 */
	public WebElement className(String using) {
		WebElement elem;
		try {
			elem = driver.findElement(By.className(using));
			return elem;
		} catch (NoSuchElementException e) {
			Log4jManager.error("Error finding element with class attribute: " + using);
			return null;
		}
	}

	/**
	 * This will find element(s) using its HTML tag
	 *
	 * @param using elements tag name in string
	 * @return WebElement if found, null otherwise
	 */
	public WebElement tagname(String using) {
		WebElement elem;
		try {
			elem = driver.findElement(By.tagName(using));
			return elem;
		} catch (NoSuchElementException e) {
			Log4jManager.error("Error finding element with tag: " + using);
			return null;
		}
	}

	/**
	 * This will find link(s) using its text
	 *
	 * @param using elements link text
	 * @return WebElement if found, null otherwise
	 */
	public WebElement linkText(String using) {
		WebElement elem;
		try {
			elem = driver.findElement(By.linkText(using));
			return elem;
		} catch (NoSuchElementException e) {
			Log4jManager.error("Error finding element with link text: " + using);
			return null;
		}
	}

	/**
	 * This will find link(s) using the link's partial text
	 *
	 * @param using elements partial link text
	 * @return WebElement if found, null otherwise
	 */
	public WebElement partialLinkText(String using) {
		WebElement elem;
		try {
			elem = driver.findElement(By.partialLinkText(using));
			return elem;
		} catch (NoSuchElementException e) {
			Log4jManager.error("Error finding element with partial link: " + using);
			return null;
		}
	}

	/**
	 * This will find element(s) using the CSS selector
	 *
	 * @param using elements CSS pattern
	 * @return WebElement if found, null otherwise
	 */
	public WebElement css(String using) {
		WebElement elem;
		try {
			elem = driver.findElement(By.cssSelector(using));
			return elem;
		} catch (NoSuchElementException e) {
			Log4jManager.error("Error finding element with css pattern: " + using);
			return null;
		}
	}

	/**
	 * Returns the element from the given list that contains the text specified by
	 * the targetText parameter
	 *
	 * @param elems      list of element to be traversed
	 * @param targetText target text
	 * @return
	 */
	public WebElement extractElementWithText(List<WebElement> elems, String targetText) {
		for (int i = 0; i < elems.size(); i++) {

			if (elems.get(i).getText().trim().replaceAll("\\s+", "").contains(targetText)) {
				System.out.println(elems.get(i).getText().trim().replaceAll("\\s+", ""));
				return elems.get(i);
			}
		}
		return null;
	}

	public WebElement extractElementWithText2(List<WebElement> elems, String targetText) {
		for (int i = 0; i < elems.size(); i++) {

			if (elems.get(i).getText().trim().contains(targetText)) {
				System.out.println(elems.get(i).getText().trim());
				return elems.get(i);
			}
		}
		return null;
	}

	public WebElement extractElementWithText3(List<WebElement> elems, String targetText) {
		for (int i = 0; i < elems.size(); i++) {

			if (elems.get(i).getText().trim().equals(targetText)) {
				System.out.println(elems.get(i).getText().trim());
				return elems.get(i);
			}
		}
		return null;
	}

	/**
	 * Returns the child WebElements of given parent WebElemen as a list
	 *
	 * @param parent            WebElement that contains subsequent Child elements
	 * @param locatorExpression example: id=elementIDText,
	 *                          xpath=//ul/li[@name='list']
	 * @return child elements as a list of WebElements
	 */
	public List<WebElement> getChildsWith(WebElement parent, String locatorExpression) {
		List<WebElement> childs;
		String[] parts = locatorExpression.split("=");
		String locatorType = parts[0]; // locator type: id, xpath, tagName, linkText
		String value = parts[1]; // actual value of locators

		if (locatorType.equalsIgnoreCase("id"))
			childs = driver.findElements(By.id("value"));
		else if (locatorType.equalsIgnoreCase("xpath"))
			childs = driver.findElements(By.xpath("value"));
		else if (locatorType.equalsIgnoreCase("tagname"))
			childs = driver.findElements(By.tagName(value));
		else
			return null;

		// if we are here, we found the child element of the parent
		return childs;
	}

	/**
	 * Gets all the element with the given Id
	 *
	 * @param id
	 * @return List of WebElement, null otherwise
	 */
	public List<WebElement> elementsWithId(String id) {
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(By.id(id));
		} catch (Exception e) {
			Log4jManager.error("Error finding elements with id: " + id);
			return null;
		}
		return elements;
	}

	/**
	 * Gets all the element identified by the xpath
	 *
	 * @param xpath
	 * @return List of WebElement, null otherwise
	 */
	public List<WebElement> elementsWithXpath(String xpath) {
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(By.xpath(xpath));
		} catch (Exception e) {
			Log4jManager.error("Error finding elements with xpath: " + xpath);
			return null;
		}
		return elements;
	}

	/**
	 * Gets all the element identified by the name attribute value
	 *
	 * @param name
	 * @return List of WebElement, null otherwise
	 */
	public List<WebElement> elementsWithName(String name) {
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(By.name(name));
		} catch (Exception e) {
			Log4jManager.error("Error finding elements with name attribute: " + name);
			return null;
		}
		return elements;
	}

	/**
	 * Gets all the element identified by the tag name
	 *
	 * @param tag
	 * @return List of WebElement, null otherwise
	 */
	public List<WebElement> elementsWithTagName(String tag) {
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(By.tagName(tag));
		} catch (Exception e) {
			Log4jManager.error("Error finding elements with tag: " + tag);
			return null;
		}
		return elements;
	}

	/**
	 * Gets all the element identified by the element text content
	 *
	 * @param text
	 * @return List of WebElement, null otherwise
	 */
	public List<WebElement> elementsWithLinkText(String text) {
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(By.linkText(text));
		} catch (Exception e) {
			Log4jManager.error("Error finding elements with link text: " + text);
			return null;
		}
		return elements;
	}

	/**
	 * Gets all the element with the class attribute value
	 *
	 * @param className
	 * @return List of WebElement, null otherwise
	 */
	public List<WebElement> elementsWithClassName(String className) {
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(By.className(className));
		} catch (Exception e) {
			Log4jManager.error("Error finding elements with class attribute: " + className);
			return null;
		}
		return elements;
	}
}
