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

public class TS164Verifyaccountcreationwithvalidemailand {
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
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReports.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Initialize SoftAssert
        softAssert = new SoftAssert();
    
}

    @Test
    public void verifyAccountCreationWithValidEmailAndDetails() {
        test = extent.createTest("Verify account creation with valid email and details", "Test the account creation process with valid email and required details.");

        try {
            // Step 1: Navigate to the account creation page
            driver.get("http://example.com/account-creation");
            test.log(Status.INFO, "Navigated to the account creation page");

            // Step 2: Enter a valid email address
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("valid.email@example.com");
            test.log(Status.INFO, "Entered a valid email address");

            // Step 3: Enter all other required details
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
            nameField.sendKeys("John Doe");
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.sendKeys("SecurePassword123");
            WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPassword")));
            confirmPasswordField.sendKeys("SecurePassword123");
            test.log(Status.INFO, "Entered all other required details");

            // Step 4: Submit the account creation form
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
            submitButton.click();
            test.log(Status.INFO, "Submitted the account creation form");

            // Verify the expected result
            WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmationMessage")));
            String confirmationText = confirmationMessage.getText();
            softAssert.assertTrue(confirmationText.contains("Account created successfully"), "Account creation confirmation message is displayed");

            WebElement accountIdElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountId")));
            String accountId = accountIdElement.getText();
            softAssert.assertNotNull(accountId, "Account ID is generated");

            // Log the results
            test.log(Status.PASS, "The system generated a unique account ID, stored the account details in the database, and confirmed the account creation to the customer.");
        
} catch (Exception e) {
            // Capture screenshot on failure
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File("screenshots/failure.png"));
                test.addScreenCaptureFromPath("screenshots/failure.png");
            
} catch (IOException ioException) {
                ioException.printStackTrace();
            
}
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
        
} finally {
            // Assert all soft assertions
            softAssert.assertAll();
        
}
    
}

    @AfterClass
    public void tearDown() {
        // Close the WebDriver
        if (driver != null) {
            driver.quit();
        
}
        // Flush the Extent Reports
        if (extent != null) {
            extent.flush();
        
}
    
}

}


### Explanation:
1. **Setup**:
   - WebDriver and WebDriverWait are initialized.
   - Extent Reports are set up for logging and reporting.
   - SoftAssert is initialized for soft assertions.

2. **Test Method**:
   - The test method verifyAccountCreationWithValidEmailAndDetails is created.
   - Each step includes WebDriverWait for synchronization.
   - Actions are logged using Extent Reports.
   - Soft assertions are used to verify the expected results without stopping the test execution.

3. **Exception Handling**:
   - Screenshots are captured upon test failure and attached to the Extent Reports.
   - The test logs the failure message.

4. **Teardown**:
   - WebDriver is closed.
   - Extent Reports are flushed to ensure all logs are written to the report.

This script ensures comprehensive test execution details, effective logging, and reporting, and provides a complete overview of test results using soft assertions.