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

public class TS70VerifyDisplayofSupportedCurrenciesandEx {
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
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReports.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Initialize SoftAssert
        softAssert = new SoftAssert();
    
}

    @Test
    public void verifyDisplayOfSupportedCurrenciesAndExchangeRates() {
        test = extent.createTest("Verify Display of Supported Currencies and Exchange Rates", "Ensure that the application displays a list of supported currencies along with their current exchange rates.");

        try {
            // Step 1: Open the application
            driver.get("http://application-url.com");
            test.log(Status.INFO, "Opened the application");

            // Step 2: Navigate to the exchange rate viewing section
            WebElement exchangeRateSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exchangeRateSection")));
            exchangeRateSection.click();
            test.log(Status.INFO, "Navigated to the exchange rate viewing section");

            // Step 3: Check the list of supported currencies displayed
            WebElement currencyList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currencyList")));
            softAssert.assertTrue(currencyList.isDisplayed(), "Currency list is displayed");
            test.log(Status.INFO, "Checked the list of supported currencies displayed");

            // Step 4: Verify that each currency has its current exchange rate displayed
            for (WebElement currency : currencyList.findElements(By.className("currency"))) {
                WebElement exchangeRate = currency.findElement(By.className("exchangeRate"));
                softAssert.assertTrue(exchangeRate.isDisplayed(), "Exchange rate is displayed for currency: " + currency.getText());
            
}
            test.log(Status.INFO, "Verified that each currency has its current exchange rate displayed");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "verifyDisplayOfSupportedCurrenciesAndExchangeRates");
        
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


This script includes the following:
1. WebDriver and WebDriverWait setup.
2. ExtentReports setup for logging and reporting.
3. Soft assertions using TestNG's SoftAssert.
4. Explicit waits before each element action.
5. Screenshot capture on test failure and attaching it to the Extent Report.
6. Comprehensive logging and reporting using Extent Reports.