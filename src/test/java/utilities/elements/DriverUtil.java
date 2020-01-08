package utilities.elements;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.PageBase;
import utilities.reportRelated.Log4jManager;

public class DriverUtil extends PageBase {
		
	// constructor   like default 
//	public DriverUtil() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	
	

	public static WebDriver startBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("ie"))
				return startIEBrowser();
			else if (browserName.equalsIgnoreCase("chrome"))
				return startChromeBrowser();
			else if (browserName.equalsIgnoreCase("firefox"))
				return startFirefoxBrowser();
			else if (browserName.equalsIgnoreCase("edge"))
				return startEdgeBrowser();
			else
				throw new Exception("ERROR!! You choose '" + browserName
						+ "', Currently supporting browsers are 'IE, Chrome, Firefox and Edge browser'");
		} catch (Exception ex) {
			Log4jManager.error("something went wrong at startLocalBrowser()");
			ex.printStackTrace();
		}
		return null;
	}

	public static WebDriver startBrowserInPrivateMode(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("ie"))
				return startIEBrowser();
			else if (browserName.equalsIgnoreCase("chrome"))
				return startChromeBrowserInPrivateMode();
			else if (browserName.equalsIgnoreCase("firefox"))
				return startFirefoxBrowserInPrivateMode();
			else
				throw new Exception("ERROR!! You choose '" + browserName
						+ "', Currently supporting browsers are 'IE, Chrome and Firefox'");
		} catch (Exception ex) {
			Log4jManager.error("something went wrong at startBrowserInPrivateMode()");
			ex.printStackTrace();
		}
		return null;
	}

	public static WebDriver startDockerContainerBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("ie"))
				return startIEBrowser();
			else if (browserName.equalsIgnoreCase("edge"))
				return startEdgeBrowser();
			else if (browserName.equalsIgnoreCase("chrome"))
				return startDockerContainerChromeBrowser();
			else if (browserName.equalsIgnoreCase("firefox"))
				return startDockerContainerFirefoxBrowser();
			else
				throw new Exception("ERROR!! You choose '" + browserName
						+ "', Currently supporting browsers are 'IE, Chrome and Firefox'");
		} catch (Exception ex) {
			Log4jManager.error("something went wrong at startDockerContainerBrowser()");
			ex.printStackTrace();
		}
		return null;
	}

	private static WebDriver startChromeBrowser() {
		System.out.println("Initiating Chrome Driver");
		try {
			String driverPath = System.getProperty("user.dir") + getLocalDriverPath("chromedriver");
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated Chrome Driver");
		return driver;
	}

	private static WebDriver startChromeBrowserInPrivateMode() {
		System.out.println("Initiating Chrome Driver");
		try {
			String driverPath = System.getProperty("user.dir") + getLocalDriverPath("chromedriver");
			System.setProperty("webdriver.chrome.driver", driverPath);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("incognito");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated Chrome Driver in private mode");
		return driver;
	}

	private static WebDriver startFirefoxBrowser() {
		System.out.println("Initiating Firefox Driver");
		try {
			String driverPath = System.getProperty("user.dir") + getLocalDriverPath("geckodriver");
			System.setProperty("webdriver.gecko.driver", driverPath);
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated Firefox Driver");
		return driver;
	}

	private static WebDriver startFirefoxBrowserInPrivateMode() {
		System.out.println("Initiating Firefox Driver");
		try {
			String driverPath = System.getProperty("user.dir") + getLocalDriverPath("geckodriver");
			System.setProperty("webdriver.gecko.driver", driverPath);
			DesiredCapabilities caps = new DesiredCapabilities();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-private");
			caps.setCapability("moz:firefoxOptions", options);
			driver = new FirefoxDriver(caps);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated Firefox Driver in private mode");
		return driver;
	}

	private static WebDriver startDockerContainerChromeBrowser() {
		System.out.println("Initiating Chrome Driver in Docker Container");
		try {
//   		 String driverPath = System.getProperty("user.dir") + "/src/test/resources/driversDocker/chromedriver";
//   		 System.setProperty("webdriver.chrome.driver", driverPath);
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName(BrowserType.CHROME);
			cap.setPlatform(Platform.LINUX);
			cap.setVersion("");
			cap.setCapability("timeoutInSeconds", 600);
			cap.setCapability("keep_alive", true);
			// http://localhost:4444/wd/hub
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			// driver = new RemoteWebDriver(new URL("http://'your local ip':4444/wd/hub"),
			// cap);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated Chrome Driver in Docker Container");
		return driver;
	}

	private static WebDriver startDockerContainerFirefoxBrowser() {
		System.out.println("Initiating Firefox Driver in Docker Container");
		try {
//   		 String driverPath = System.getProperty("user.dir") + "/src/test/resources/driversDocker/geckodriver-v0.26.0-linux64.tar.gz";
//   		 System.setProperty("webdriver.gecko.driver", driverPath);
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setPlatform(Platform.LINUX);
			cap.setVersion("");
			cap.setCapability("timeoutInSeconds", 120);
			cap.setCapability("keep_alive", true);

			driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), cap);
			// driver = new RemoteWebDriver(new URL("http://10.10.173.34:4444/wd/hub"),cap);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated Firefox Driver in Docker Container");
		return driver;
	}

	private static WebDriver startEdgeBrowser() {
		System.out.println("Initiating Edge Driver");
		try {
			String driverPath = System.getProperty("user.dir") + getLocalDriverPath("msedgedriver");
			System.setProperty("webdriver.edge.driver", driverPath);
			driver = new EdgeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated Edge Driver");
		return driver;
	}

	private static WebDriver startIEBrowser() {
		System.out.println("Initiating IE Driver");
		try {
			String driverPath = System.getProperty("user.dir") + getLocalDriverPath("IEDriverServer");
			System.setProperty("webdriver.ie.driver", driverPath);
			driver = new InternetExplorerDriver();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated IE Driver");
		return driver;
	}

	private static String getLocalDriverPath(String driverinfo) {

		return "/src/test/resources/drivers/" + driverinfo;
//  if using windows  + ".exe"
	}

//    private static String getDockerDriverPath(String driverinfo) {
//
//   	 return "/src/main/resources/driversDocker/" + driverinfo + ".exe";
//
//    }
}
