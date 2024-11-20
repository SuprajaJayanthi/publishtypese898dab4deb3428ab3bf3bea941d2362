package TestCase;
Below is a Selenium test script in Java that meets the specified criteria. The script uses WebDriverWait for synchronization, captures screenshots upon test failure, and utilizes Extent Reports for logging and reporting. Soft assertions are implemented using TestNG's soft assertions.

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

public class TS102VerifysystemhandleserrorsduringPayPalpa {
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

        // Set up Extent Reports
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Initialize SoftAssert
        softAssert = new SoftAssert();
    
}

    @Test
    public void verifyPayPalPaymentInitiationErrorHandling() {
        test = extent.createTest("Verify system handles errors during PayPal payment initiation");

        try {
            // Step 1: Initiate a PayPal payment with invalid payment details (e.g., invalid currency code)
            driver.get("https://example.com/paypal-payment-initiation");
            WebElement currencyCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currencyCode")));
            currencyCodeInput.sendKeys("INVALID_CODE");
            test.log(Status.INFO, "Entered invalid currency code");

            // Step 2: Submit the payment initiation form
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitPayment")));
            submitButton.click();
            test.log(Status.INFO, "Submitted the payment initiation form");

            // Step 3: Check the system logs for error messages
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
            String errorText = errorMessage.getText();
            softAssert.assertTrue(errorText.contains("Invalid currency code"), "Error message should contain 'Invalid currency code'");
            test.log(Status.INFO, "Checked the system logs for error messages");

            // Step 4: Verify the system does not proceed with the payment initiation
            WebElement paymentStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentStatus")));
            String statusText = paymentStatus.getText();
            softAssert.assertTrue(statusText.equals("Payment initiation failed"), "Payment status should be 'Payment initiation failed'");
            test.log(Status.INFO, "Verified the system does not proceed with the payment initiation");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "PayPalPaymentInitiationError");
        
} finally {
            softAssert.assertAll();
        
}
    
}

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        
}
        // Flush the Extent Reports
        extent.flush();
    
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
1. **Setup and Teardown**:
   - setUp(): Initializes WebDriver, WebDriverWait, Extent Reports, and SoftAssert.
   - tearDown(): Closes the browser and flushes the Extent Reports.

2. **Test Method**:
   - verifyPayPalPaymentInitiationErrorHandling(): Contains the test steps with WebDriverWait for synchronization, soft assertions for validation, and Extent Reports for logging.
   - Each step logs information to Extent Reports and uses soft assertions to validate conditions without stopping the test execution.

3. **Screenshot Capture**:
   - captureScreenshot(): Captures a screenshot upon test failure and attaches it to the Extent Report for enhanced debugging.

This script ensures comprehensive test execution details, effective reporting, and robust error handling.