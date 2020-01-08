package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import utilities.dataRelated.ExcelManager;
import utilities.dataRelated.JDBCManager;
import utilities.dataRelated.JsonManager;
import utilities.elements.DriverUtil;
import utilities.reportRelated.TestListener;


public class TestBase extends TestListener {

	// protected static WebDriver driver;
	// since we extends testListerner, driver is no longer needed
	// driver is coming from TestListerner

	protected static JDBCManager sqlManager = new JDBCManager();
		
	protected static ExcelManager xlread = new ExcelManager();
//	protected static ExcelEZ ezexcel = new ExcelEZ();
	
	String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/teklabJson.json";
	protected JsonManager json = new JsonManager(filePath);
	

	
	
	
	
	
	// --------------- Before all @Test --------------------
	@BeforeTest
	public void beforeAllTestCase() {

		onExtentSetup();

	}
	
	
	

	// --------------- Before each @Test --------------------

	@BeforeMethod
	@Parameters("browser") // this parameter is used in testNG.xml
	public void beforeEachTestCase(String browser) {

		// driver = DriverUtil.startBrowser(browser);
		// driver = DriverUtil.startBrowserInPrivateMode(browser);
		driver = DriverUtil.startDockerContainerBrowser(browser);

	}
//    public void setUp() {
//
//   	 // setting system property so system would know where our actual driver is
//   	 // located
//   	 String driverpath = System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe";
//   	 System.setProperty("webdriver.chrome.driver", driverpath);
//
//   	 // Launch Chrome
//   	 driver = new ChromeDriver();
//   	 // Maximize the browser window
//   	 driver.manage().window().maximize();
//
//   	 // set up implicit wait
//   	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//
//    }

	@AfterMethod
	public void tearDown() throws Exception {
		// Close the browser
		driver.quit();
	}

	// --------------- After all @Test--------------------
	@AfterTest
	public void afterAllTestCase() {
		// write the report to output view
		extent.flush();

	}
}
