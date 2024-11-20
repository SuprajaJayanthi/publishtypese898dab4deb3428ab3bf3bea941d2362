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

public class Verifyerrorhandlingforinvalidamount {
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
    public void testInvalidAmountErrorHandling() {
        test = extent.createTest("Verify error handling for invalid amount", "Test the application's error handling when an invalid amount is inputted.");

        try {
            // Step 1: Open the application
            driver.get("http://application-url.com");
            test.log(Status.INFO, "Opened the application");

            // Step 2: Navigate to the currency conversion feature
            WebElement currencyConversionFeature = wait.until(ExpectedConditions.elementToBeClickable(By.id("currencyConversionFeature")));
            currencyConversionFeature.click();
            test.log(Status.INFO, "Navigated to the currency conversion feature");

            // Step 3: Input an invalid amount (e.g., non-numeric characters) in the form
            WebElement amountInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amountInput")));
            amountInput.sendKeys("invalidAmount");
            test.log(Status.INFO, "Inputted an invalid amount");

            // Step 4: Select a valid currency from the dropdown menu
            WebElement currencyDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("currencyDropdown")));
            currencyDropdown.click();
            WebElement validCurrencyOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='USD']")));
            validCurrencyOption.click();
            test.log(Status.INFO, "Selected a valid currency");

            // Step 5: Submit the form
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitButton")));
            submitButton.click();
            test.log(Status.INFO, "Submitted the form");

            // Verify the expected result
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
            softAssert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
            softAssert.assertEquals(errorMessage.getText(), "Invalid amount input", "Error message text is incorrect");
            test.log(Status.PASS, "Verified the error message for invalid amount input");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "testInvalidAmountErrorHandling");
        
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


This Selenium test script follows the provided criteria and includes the following features:
1. Maintains the order of test steps.
2. No blank methods without code.
3. Uses WebDriverWait (ExplicitWait) before each element action.
4. Captures screenshots upon test failure and attaches them to Extent Reports.
5. Uses Extent Reports for logging and reporting.
6. Utilizes SoftAssert for soft assertions, allowing the test to continue executing even after encountering failures.