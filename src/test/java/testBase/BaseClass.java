package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;  //log4j

public class BaseClass {

	public static WebDriver driver;  //so that the same driver can be accessed in throughout the project eg. in ExtentReportManager where we create a new base class object in onFailure method
	public Logger logger;	
	public Properties p;

	@BeforeClass(groups= {"Sanity", "Regression", "Master"})
	@Parameters ({"os", "browser"})
	public void setup(String os, String br) throws IOException
	{
		//loading config.properties file
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		
		p=new Properties();
		p.load(file);
		
		
		logger= LogManager.getLogger(this.getClass());  //will automatically fetch the log4j2.xml in the default src/test/resources folder path
		
		
		//if remote execution environment is enabled on the config.properties file
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities= new DesiredCapabilities();
			
			//os - taken from the xml file
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser - taken from the xml file
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;

			default: System.out.println("No matching browser"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			
		}
		
		
		
		
		//if local execution environment is enabled on the config.properties file
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver(); break;
			case "firefox" : driver =new FirefoxDriver(); break;
			case "edge": driver= new EdgeDriver(); break;
			default : System.out.println("Invalid browser name.."); return;
			}
		
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));  //reading value from properties file
		driver.manage().window().maximize();
	}
	
	//methods to generate random strings and numbers
		public String randomString()
		{
			String generatedstring=RandomStringUtils.randomAlphabetic(5); //5 character string will be randomly generated
			return generatedstring;
		}
		
		public String randomNumber()
		{
			String generatednumber=RandomStringUtils.randomNumeric(10);
			return generatednumber;
		}
		
		
		public String randomAlphaNumeric()
		{
			String generatedstring=RandomStringUtils.randomAlphabetic(3);
			String generatednumber=RandomStringUtils.randomNumeric(3);
			return(generatedstring+"*"+generatednumber);
		}
		
		
		public String captureScreen(String tname) throws IOException{
			
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+ tname +"_" + timeStamp + ".png";
			File targetFile=new File (targetFilePath);
			
			sourceFile.renameTo(targetFile);  //copying or moving the source file to target file
			
			return targetFilePath;  //returning the path of where the target file is
		}
		
		
		@AfterClass(groups= {"Sanity", "Regression", "Master"})
		public void tearDown()
		{
			driver.quit();
		}
	
}
