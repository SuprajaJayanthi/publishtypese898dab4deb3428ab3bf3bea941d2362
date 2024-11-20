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

public class SavePreferences-NotificationSettings {
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
    public void testSaveNotificationSettings() {
        test = extent.createTest("Save Preferences - Notification Settings", "Verify that the customer can select and save their notification settings.");

        try {
            // Step 1: Log in to the application
            driver.get("http://application-url.com/login");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("testuser");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("password");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton"))).click();
            test.log(Status.PASS, "Logged in to the application");

            // Step 2: Navigate to the preferences/settings page
            wait.until(ExpectedConditions.elementToBeClickable(By.id("preferencesLink"))).click();
            test.log(Status.PASS, "Navigated to the preferences/settings page");

            // Step 3: Select notification settings from the available options
            WebElement notificationCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("notificationCheckbox")));
            if (!notificationCheckbox.isSelected()) {
                notificationCheckbox.click();
            
}
            test.log(Status.PASS, "Selected notification settings");

            // Step 4: Save the preferences
            wait.until(ExpectedConditions.elementToBeClickable(By.id("saveButton"))).click();
            test.log(Status.PASS, "Saved the preferences");

            // Step 5: Log out and log back in
            wait.until(ExpectedConditions.elementToBeClickable(By.id("logoutButton"))).click();
            test.log(Status.PASS, "Logged out of the application");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("testuser");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("password");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton"))).click();
            test.log(Status.PASS, "Logged back in to the application");

            // Verify that the selected notification settings are saved and applied upon subsequent logins
            wait.until(ExpectedConditions.elementToBeClickable(By.id("preferencesLink"))).click();
            notificationCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("notificationCheckbox")));
            softAssert.assertTrue(notificationCheckbox.isSelected(), "Notification settings should be saved and applied upon subsequent logins");
            test.log(Status.PASS, "Verified that the selected notification settings are saved and applied upon subsequent logins");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "testSaveNotificationSettings");
        
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


### Explanation:
1. **WebDriver and WebDriverWait**: The script initializes WebDriver and WebDriverWait to handle browser interactions and synchronization.
2. **Extent Reports**: Extent Reports are set up to log and report test execution details.
3. **Soft Assertions**: TestNG's SoftAssert is used to perform soft assertions, allowing the test to continue execution even after encountering failures.
4. **Test Steps**: Each test step is implemented with WebDriverWait to ensure synchronization with the application's state.
5. **Exception Handling and Screenshots**: The script captures screenshots upon test failure and attaches them to Extent Reports for enhanced debugging and reporting.
6. **Tear Down**: The tearDown method closes the browser and flushes the Extent Reports.

This script ensures comprehensive test execution details and effective organization of test results, meeting all the specified criteria.