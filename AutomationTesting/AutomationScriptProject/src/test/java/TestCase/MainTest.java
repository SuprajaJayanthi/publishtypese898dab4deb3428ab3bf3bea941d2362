package TestCase;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.gherkin.model.And;

import pages.MainFile;

import pages.initialization;

public class MainTest extends MainFile
{
	
	initialization init = new initialization();
	
	
		@Parameters({"browser", "url","environment"})
	@BeforeClass
	public void startup(String browser, String url,String environment) throws Exception{
		
		init.initProperties();
		init.reports();
	
		init.start(browser,url,environment);
		WebDriverWait wait = new WebDriverWait(driver,10000);
	}
	
		@Test(priority=0)
		public void Launch_Application() throws Exception  
		
		{
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName(); 
			logger=initialization.report.createTest(nameofCurrMethod);
			logger.info(nameofCurrMethod+" - Validation started");
			
			LaunchApplication();
				
	   }
		@Test(priority=1)
		public void Proceed_Checkout() throws Exception  
		
		{
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName(); 
			logger=initialization.report.createTest(nameofCurrMethod);
			logger.info(nameofCurrMethod+" - Validation started");
			ProceedToCheckOut();
			
	   }
		
		

	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException
	{
	if(result.getStatus() == ITestResult.FAILURE) {
		//getScreenshot(driver);
		logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
	}
	else if (result.getStatus() == ITestResult.SUCCESS) {
		//getScreenshot(driver);
		logger.pass("Test Pass");
	}
	}

@SuppressWarnings("static-access")
 @AfterClass
 public void tearDown() throws IOException{
	
	 init.report.flush();
	 init.stop();
	 driver.close();
 }

}

