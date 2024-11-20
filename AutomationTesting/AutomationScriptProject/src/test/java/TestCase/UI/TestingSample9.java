package TestCase;
Here is a Selenium test script that meets the specified criteria:

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

public class Verifyusercanselectdaterangesforreports {
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
    public void verifyDateRangeSelection() {
        test = extent.createTest("Verify user can select date ranges for reports", "Test to ensure that the application allows the selection of date ranges for generating reports.");

        try {
            // Step 1: Navigate to the user activity reporting section
            driver.get("http://application-url.com/user-activity-reporting");
            test.log(Status.INFO, "Navigated to the user activity reporting section");

            // Step 2: Check for the date range selection option
            WebElement startDateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));
            WebElement endDateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("endDate")));
            softAssert.assertNotNull(startDateInput, "Start date input is not available");
            softAssert.assertNotNull(endDateInput, "End date input is not available");
            test.log(Status.INFO, "Date range selection option is available");

            // Step 3: Select a start date and an end date for the report
            startDateInput.sendKeys("2023-01-01");
            endDateInput.sendKeys("2023-12-31");
            WebElement generateReportButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("generateReport")));
            generateReportButton.click();
            test.log(Status.INFO, "Selected start date and end date for the report");

            // Verify the expected result
            WebElement reportSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reportSection")));
            softAssert.assertNotNull(reportSection, "Report section is not displayed");
            test.log(Status.PASS, "The application allows the user to select a start date and an end date for generating reports");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "verifyDateRangeSelection");
        
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
1. **WebDriver and WebDriverWait**: The script initializes the WebDriver and WebDriverWait to handle browser interactions and wait for elements to be visible or clickable.
2. **Extent Reports**: Extent Reports are set up to log test steps and results. The report is saved as an HTML file.
3. **Soft Assertions**: Soft assertions are used to verify conditions without stopping the test execution upon failure.
4. **Test Steps**: The script follows the test steps provided:
   - Navigates to the user activity reporting section.
   - Checks for the date range selection option.
   - Selects a start date and an end date for the report.
5. **Exception Handling**: If an exception occurs, the script logs the failure and captures a screenshot.
6. **Screenshot Capture**: The captureScreenshot method captures screenshots upon test failure and attaches them to the Extent Report.
7. **Tear Down**: The tearDown method closes the browser and flushes the Extent Reports to ensure all logs are written to the report.