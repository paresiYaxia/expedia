package utilities.reportRelated;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestListener extends TestListenerAdapter {

	// now this driver would be used by both testbase and pagebase
	public static WebDriver driver;
	
	
	

	// Extent Report
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	// we created an extentTest reference as "logger"
	public static ExtentTest logger;
	public static Calendar cal = Calendar.getInstance();
	public static long time = cal.getTimeInMillis();

	public void onExtentSetup() {
		// Extent Report
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/extentReports/myExtentReport" + time + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Windows 10");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Paresi");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setJS("js-string");
		htmlReporter.config().setDocumentTitle("Test Automation Report");
		htmlReporter.config().setReportName("Paresi Extent Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().enableTimeline(true);
		htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
	}

	public void onTestStart(ITestResult result) {
		String TestCaseID = result.getName();
		Log4jManager.info("Test Case: " + TestCaseID + " Started");
	}

	public void onTestSuccess(ITestResult result) {
		String TestCaseID = result.getName();
		Log4jManager.info("Test Case: " + TestCaseID + " Passed");
		logger.getModel().setEndTime(getTime(result.getEndMillis()));

		if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		}
	}

	public void onTestFailure(ITestResult result) {
		String TestCaseID = result.getName();
		Log4jManager.info("Test Case: " + TestCaseID + " Failed");
		logger.getModel().setEndTime(getTime(result.getEndMillis()));

		if (result.getStatus() == ITestResult.FAILURE) {
			// MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			// To add screenshot in the extent report
			String screenshotPath;
			try {
				screenshotPath = getScreenShot(driver, result.getName());
				logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult result) {
		String TestCaseID = result.getName();
		Log4jManager.info("Test Case: " + TestCaseID + " Skipped");

		if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		}
	}

	private String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	private Date getTime(long millis) {
		cal.setTimeInMillis(millis);
		return cal.getTime();

	}

}
