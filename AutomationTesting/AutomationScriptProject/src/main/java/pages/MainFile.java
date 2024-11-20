package pages;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;

public class MainFile extends initialization 
{
	
	initialization init = new initialization();
	//String  randomString = init.generateRandomString();

	public static ExtentReports report;
	public static ExtentTest logger;

	

	public void LaunchApplication() throws Exception
	{
		
//for (int i=1;i<=6;i++) {}
String itemXpath = "(//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/button)";
driver.findElement(By.xpath(itemXpath)).click();
WebDriverWait wait = new WebDriverWait(driver,3000);
 init.getScreenshot(driver);
}
	public void ProceedToCheckOut() throws Exception
	{
		
String itemXpath = "(//*[@id=\"root\"]/div/div[2]/div[3]/button)";
driver.findElement(By.xpath(itemXpath)).click();
WebDriverWait wait = new WebDriverWait(driver,3000);
 init.getScreenshot(driver);
}
	
	

	
	
}