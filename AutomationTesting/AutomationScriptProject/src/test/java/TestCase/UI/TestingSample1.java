package TestCase;
Here is a Selenium test script in Java that meets the specified criteria:

java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TS10Verifysystemconfirmationmessageonsuccess {
    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;
    SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        // Set up WebDriver and WebDriverWait
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        // Set up ExtentReports
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Initialize SoftAssert
        softAssert = new SoftAssert();
    
}

    @Test
    public void verifySystemConfirmationMessage() {
        test = extent.createTest("Verify system confirmation message on successful record storage");

        try {
            // Step 1: Navigate to the system activity logging interface
            driver.get("http://example.com/system-activity-logging");
            test.log(Status.INFO, "Navigated to the system activity logging interface");

            // Step 2: Input a valid text string representing a system record
            WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recordInput")));
            inputField.sendKeys("Test system record");
            test.log(Status.INFO, "Entered a valid text string representing a system record");

            // Step 3: Submit the text string to store the record in the journal
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitRecord")));
            submitButton.click();
            test.log(Status.INFO, "Submitted the text string to store the record in the journal");

            // Step 4: Observe the system's response
            WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmationMessage")));
            String actualMessage = confirmationMessage.getText();
            String expectedMessage = "The system record has been stored successfully.";
            softAssert.assertEquals(actualMessage, expectedMessage, "Confirmation message mismatch");
            test.log(Status.INFO, "Observed the system's response");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "verifySystemConfirmationMessage");
        
}

        // Assert all soft assertions
        softAssert.assertAll();
    
}

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        
}

        // Flush the ExtentReports
        if (extent != null) {
            extent.flush();
        
}
    
}

    public void captureScreenshot(WebDriver driver, String screenshotName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("screenshots/" + screenshotName + ".png"));
            test.addScreenCaptureFromPath("screenshots/" + screenshotName + ".png");
        
} catch (IOException e) {
            e.printStackTrace();
        
}
    
}

}


### Explanation:
1. **WebDriver and WebDriverWait**: The script initializes the WebDriver and WebDriverWait to handle browser interactions and wait for elements to be ready.
2. **ExtentReports**: ExtentReports is used for logging and reporting. It provides detailed test execution reports.
3. **SoftAssert**: Soft assertions are used to allow the test to continue executing even after encountering failures.
4. **Test Steps**: Each test step is implemented with WebDriverWait to ensure synchronization with the application's state.
5. **Exception Handling and Screenshots**: The script captures screenshots upon test failure and attaches them to the Extent Reports for enhanced debugging.
6. **TearDown**: The tearDown method closes the browser and flushes the ExtentReports.

This script ensures comprehensive test execution details and effective organization of test results.