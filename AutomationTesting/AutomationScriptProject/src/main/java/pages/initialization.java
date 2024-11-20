package pages;


import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;


public class initialization {

	protected static WebDriver driver;
	
	


	public static double getload()

	{
		final JavascriptExecutor js = (JavascriptExecutor) driver;
		// time of the process of navigation and page load
		double loadTime = (Double) js.executeScript(
				"return (window.performance.timing.loadEventEnd - window.performance.timing.navigationStart) / 1000");
		// System.out.print(loadTime + " seconds");
		return loadTime;
	}

	public WebDriver launchBrowser(String browser, String url, String environment)
		throws InterruptedException, IOException {
		WebDriverWait wait;
		String URL = System.getProperty("URL");
		String userN = System.getProperty("userN");
		String passW = System.getProperty("passW");
		switch (browser) {

		case "firefox":
			System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");

			driver = new FirefoxDriver();
			// driver.get(url);
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			//System.setProperty("webdriver.edge.driver", ".\\msedgedriver.exe");
			EdgeOptions options1 = new EdgeOptions();
			options1.addArguments("--start-maximized");
			options1.addArguments("--InPrivate");
			//driver = new EdgeDriver();
			driver = new EdgeDriver(options1);
			//WebDriverManager.chromedriver().setup();
			//ChromeOptions options = new ChromeOptions();
			// options.addArguments("--headless");
			//options.addArguments("--no-sandbox");
			//options.addArguments("--start-maximized");
			//options.addArguments("--disable-dev-shm-usage");
			//options.addArguments("--incognito");
			//driver = new ChromeDriver(options);
			driver.get(URL);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
			if (environment.equalsIgnoreCase("Stage")) {
				WebDriverWait wait = new WebDriverWait(driver,8000);
				driver .findElement(By.xpath("//*[@id=\"root\"]/div/form/input[1]")).sendKeys(userN);
				WebDriverWait wait = new WebDriverWait(driver,2000);
				driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys(passW);
				WebDriverWait wait = new WebDriverWait(driver,1000);
				driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/button")).click();
				WebDriverWait wait = new WebDriverWait(driver,4000);
			}

			break;

		case "ie":
			System.setProperty("webdriver.chrome.driver", ".\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;

		default:
			driver = new FirefoxDriver();
			break;

		}

		getload();

		return driver;
	}


	public WebDriver start(String browser, String url, String environment) throws Exception {

		return launchBrowser(browser, url, environment);

	}


}
