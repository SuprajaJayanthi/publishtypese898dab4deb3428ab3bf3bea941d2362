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

public class VerifyReal-TimeUpdateofExchangeRates {
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
        test = extent.createTest("Verify Real-Time Update of Exchange Rates", "Ensure that the exchange rates are updated in real-time or at regular intervals.");

        // Set up SoftAssert
        softAssert = new SoftAssert();
    
}

    @Test
    public void verifyRealTimeUpdateOfExchangeRates() {
        try {
            // Step 1: Open the application
            driver.get("http://example.com");
            test.log(Status.INFO, "Opened the application");

            // Step 2: Navigate to the exchange rate viewing section
            WebElement exchangeRateSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exchangeRateSection")));
            exchangeRateSection.click();
            test.log(Status.INFO, "Navigated to the exchange rate viewing section");

            // Step 3: Observe the exchange rates for a few minutes
            WebElement exchangeRate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exchangeRate")));
            String initialRate = exchangeRate.getText();
            Thread.sleep(120000); // Wait for 2 minutes to observe changes
            String updatedRate = exchangeRate.getText();
            test.log(Status.INFO, "Observed the exchange rates for a few minutes");

            // Step 4: Verify that the exchange rates are updated in real-time or at regular intervals
            softAssert.assertNotEquals(initialRate, updatedRate, "Exchange rates should be updated in real-time or at regular intervals.");
            test.log(Status.INFO, "Verified that the exchange rates are updated in real-time or at regular intervals");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "verifyRealTimeUpdateOfExchangeRates");
        
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
        // Write the report
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


This script includes:
1. WebDriver and WebDriverWait setup.
2. ExtentReports for logging and reporting.
3. Soft assertions using TestNG's SoftAssert.
4. Screenshot capture on test failure.
5. Detailed logging for each step of the test case.