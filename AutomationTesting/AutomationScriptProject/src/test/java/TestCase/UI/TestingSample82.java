package TestCase;
java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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

public class AccountCreationFormDisplay {
    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;
    SoftAssert softAssert;

    @BeforeMethod
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
    public void verifyAccountCreationFormDisplay() {
        test = extent.createTest("Account Creation Form Display", "Verify that the system provides a form to input necessary details for account creation.");

        try {
            // Step 1: Navigate to the account creation page
            driver.get("http://example.com/account-creation");
            test.log(Status.INFO, "Navigated to the account creation page.");

            // Step 2: Verify that the form for account creation is displayed
            WebElement accountCreationForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountCreationForm")));
            softAssert.assertTrue(accountCreationForm.isDisplayed(), "Account creation form is not displayed.");
            test.log(Status.INFO, "Verified that the account creation form is displayed.");

            // Verify that necessary fields are present
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPassword")));
            softAssert.assertTrue(emailField.isDisplayed(), "Email field is not displayed.");
            softAssert.assertTrue(passwordField.isDisplayed(), "Password field is not displayed.");
            softAssert.assertTrue(confirmPasswordField.isDisplayed(), "Confirm Password field is not displayed.");
            test.log(Status.INFO, "Verified that necessary fields are present in the account creation form.");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
            captureScreenshot(driver, "verifyAccountCreationFormDisplay");
        
} finally {
            softAssert.assertAll();
        
}
    
}

    @AfterMethod
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


This script includes:
1. Navigation to the account creation page.
2. Verification that the account creation form and necessary fields are displayed.
3. Use of WebDriverWait for synchronization.
4. Soft assertions to allow the test to continue even if some checks fail.
5. Extent Reports for logging and reporting.
6. Screenshot capture on test failure.