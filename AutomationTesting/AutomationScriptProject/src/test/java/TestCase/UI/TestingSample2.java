package TestCase;
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

public class Verifysystembehaviorwhenfilecannotbeset {
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
    public void verifyFileCannotBeSet() {
        test = extent.createTest("Verify system behavior when file cannot be set", "Test to ensure that the system provides an appropriate error message when the file cannot be set.");

        try {
            // Step 1: Navigate to the journal settings page
            driver.get("http://example.com/journal-settings");
            test.log(Status.INFO, "Navigated to the journal settings page");

            // Step 2: Enter a valid file name in the file name input field
            WebElement fileNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileNameInput")));
            fileNameInput.sendKeys("validFileName.txt");
            test.log(Status.INFO, "Entered a valid file name in the file name input field");

            // Step 3: Simulate a condition where the file cannot be set (e.g., file system permissions issue)
            // This step would be specific to the application and might require backend setup or specific test environment configuration
            // For demonstration, we assume this step is done

            // Step 4: Click on the 'Set File' button
            WebElement setFileButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("setFileButton")));
            setFileButton.click();
            test.log(Status.INFO, "Clicked on the 'Set File' button");

            // Expected Result: The system should display an error message indicating that the file cannot be set
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
            String actualErrorMessage = errorMessage.getText();
            String expectedErrorMessage = "The file cannot be set due to a permissions issue.";
            softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match the expected result");
            test.log(Status.INFO, "Verified the error message indicating that the file cannot be set");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
            captureScreenshot(driver, "verifyFileCannotBeSet");
        
} finally {
            softAssert.assertAll();
        
}
    
}

    @AfterClass
    public void tearDown() {
        // Close the WebDriver
        if (driver != null) {
            driver.quit();
        
}

        // Flush the ExtentReports
        if (extent != null) {
            extent.flush();
        
}
    
}

    private void captureScreenshot(WebDriver driver, String screenshotName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("screenshots/" + screenshotName + ".png"));
            test.addScreenCaptureFromPath("screenshots/" + screenshotName + ".png");
        
} catch (IOException e) {
            test.log(Status.FAIL, "Failed to capture screenshot: " + e.getMessage());
        
}
    
}

}


This script includes the following:
1. WebDriver and WebDriverWait setup.
2. ExtentReports setup for logging and reporting.
3. Soft assertions using TestNG's SoftAssert.
4. Explicit waits before each element action.
5. Screenshot capture on test failure and attaching it to the Extent Report.
6. Comprehensive logging and reporting using Extent Reports.