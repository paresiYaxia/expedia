package testCase.sysE2E;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import base.TestBase;
import pageObjects.HomePageHeader;
public class ExpediaAccountRegisterTest extends TestBase {
	
	@Test
	public void registerAttemptTest() {
		
		logger = extent.createTest("expedia_TestCase001");
		
		//---------------- test data: -------------------
//				String 	firstName = "paresi",
//						lastName = "yaxia",
//						email 	= "paresiTests@gmail.com",
//						password = "Email@test";
				
		ArrayList<Map<String, String>> keyValueContents = xlread.parse(1);
		ArrayList<Map<String, String>> testData = xlread.getdata(keyValueContents, "TestCaseID", "TestCase_003");
		String firstName = testData.get(0).get("FirstName");
		String lastName = testData.get(0).get("LastName");
		String email = testData.get(0).get("email or phone");
		String password = testData.get(0).get("Password");
		
		
	
				
		// test execution/ test steps

		HomePageHeader h1 = new HomePageHeader();
		h1.createAnAccount(firstName, lastName, email, password).assertResgisterImageSelect();

	}

}
/* ============= End ================= */
	
	
	
	


