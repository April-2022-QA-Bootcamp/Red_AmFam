package red.amfam.base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import red.amfam.common.CommonFunctions;
import red.amfam.common.CommonWaits;
import red.amfam.utils.Configuration;

public class BaseClass {

	public Configuration configuration = new Configuration(null);

	protected WebDriver driver;
	WebDriverWait wait;

	protected CommonFunctions commons;
	CommonWaits waits;

	@BeforeMethod
	public void setup() {
		driver = localDriver("chrome");
		driver.manage().window().maximize();
		driver.get(configuration.getConfig("url"));
		driver.manage().timeouts()
				.pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(configuration.getConfig("pageloadWait"))));
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(configuration.getConfig("implicitWait"))));
		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(configuration.getConfig("explicitWait"))));

		initClasses();
	}

	private WebDriver localDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		return driver;
	}

	protected WebDriver getDriver() {
		return driver;
	}

	private void initClasses() {
		waits = new CommonWaits(wait);
		commons = new CommonFunctions(waits);
	}

	@AfterMethod
	public void terminate() {
		driver.quit();
	}
}
