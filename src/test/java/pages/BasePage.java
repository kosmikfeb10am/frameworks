package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BasePage {
	
	
	public static WebDriver driver;
	FileInputStream fis1;
	Properties pr1;
	
	@BeforeClass
	public void setUp() throws IOException
	{
		
		fis1=new FileInputStream("Properties\\config.properties");
		
		pr1=new Properties();
		
		pr1.load(fis1);
		
	}
	
	@BeforeMethod
	public void startTest()
	{
		
		String browserForExecution=pr1.getProperty("browser");
		
		System.out.println("Starting the browser "+browserForExecution);
		
		
		
		
		switch(browserForExecution)
		{
			
			case "chrome":
				
				System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
				
				driver=new ChromeDriver();
				
				break;
				
			case "firefox":
				
				System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\drivers\\geckodriver.exe");
				
				driver=new FirefoxDriver();
				
				break;
				
			default:
				
				throw new RuntimeException("Not a valid browser for execution");
				
				
		}	
		
		
		driver.get(pr1.getProperty("url"));
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(pr1.getProperty("timeToload")), TimeUnit.SECONDS);
		
		
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		
		
		driver.quit();
	}
	


}
