package TestCase;
Below is a Selenium test script in Java that meets the specified criteria for the given test case:

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

public class TS128VerifyAccesstoTransactionHistoryfromMai {
    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;
    SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        // Set up WebDriver and ExtentReports
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("Verify Access to Transaction History from Main Menu");
        softAssert = new SoftAssert();
    
}

    @Test
    public void verifyTransactionHistoryAccess() {
        try {
            // Step 1: Log in to the application
            driver.get("http://application-url.com/login");
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
            username.sendKeys("validUsername");
            password.sendKeys("validPassword");
            loginButton.click();
            test.log(Status.INFO, "Logged in to the application");

            // Step 2: Check the main menu for an option to access the transaction history
            WebElement mainMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mainMenu")));
            WebElement transactionHistoryOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("transactionHistory")));
            softAssert.assertNotNull(transactionHistoryOption, "Transaction history option is present in the main menu");
            test.log(Status.INFO, "Checked the main menu for transaction history option");

            // Step 3: Click on the transaction history option
            transactionHistoryOption.click();
            WebElement transactionHistoryPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("transactionHistoryPage")));
            softAssert.assertNotNull(transactionHistoryPage, "Transaction history page is accessible from the main menu");
            test.log(Status.INFO, "Clicked on the transaction history option and verified access");

        
} catch (Exception e) {
            captureScreenshot(driver, "verifyTransactionHistoryAccess");
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
        
} finally {
            softAssert.assertAll();
        
}
    
}

    @AfterClass
    public void tearDown() {
        // Close the browser and generate the report
        driver.quit();
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
1. **WebDriver and ExtentReports Setup**: The setUp method initializes the WebDriver, WebDriverWait, ExtentReports, and SoftAssert.
2. **Test Steps**:
   - **Step 1**: Logs in to the application by entering the username and password and clicking the login button.
   - **Step 2**: Checks the main menu for the transaction history option and verifies its presence using soft assertions.
   - **Step 3**: Clicks on the transaction history option and verifies that the transaction history page is accessible.
3. **Exception Handling**: Captures a screenshot and logs the failure in ExtentReports if an exception occurs.
4. **Soft Assertions**: Uses SoftAssert to ensure that the test continues executing even if some assertions fail.
5. **Tear Down**: Closes the browser and generates the ExtentReports at the end of the test run.
6. **Screenshot Capture**: Captures screenshots upon test failure and attaches them to the ExtentReports for enhanced debugging and reporting.