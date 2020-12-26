package com.qa.opencart.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * 
 * @author sangambharadia
 *
 */


public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method is used to initialize the browser on the basis of given browser
	 * @param browser
	 * @return
	 */
	
	public WebDriver init_driver(String browser,String browserversion) {
		
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		System.out.println("Browser value is :"+browser);
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if(Boolean.parseBoolean(prop.getProperty("remote"))){
				init_remotedriver("chrome",browserversion);
				
			}else {
				
			
//			driver=new ChromeDriver();
			tlDriver.set(new ChromeDriver());
			}
		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if(Boolean.parseBoolean(prop.getProperty("remote"))){
				init_remotedriver("firefox",browserversion);
				
			}else {

			tlDriver.set(new FirefoxDriver());
			}
		}else if(browser.equalsIgnoreCase("safari")) {
//			driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
		}else {
			System.out.println("Please pass the correct browser value"+browser);
			driver=null;
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		
		
		return getDriver();
	}
	public void init_remotedriver(String browser, String browserversion) {
		
		System.out.println("Running test on remote grid: " + browser);
		if(browser.equals("chrome")) {
		DesiredCapabilities cap=DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
		cap.setCapability("browserName", "chrome");
		cap.setCapability("browserVersion", browserversion);
		cap.setCapability("enableVNC", true);
		try {
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		}
		else if(browser.equals("firefox")) {
			DesiredCapabilities cap=DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());
			cap.setCapability("browserName", "firefox");
			cap.setCapability("browserVersion", browserversion);
			cap.setCapability("enableVNC", true);
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	/**
	 * getDriver using ThreadLocal
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	/**
	 * This method is used to load the properties from config.properties file
	 * @return It returns properties prop reference
	 */
	public Properties init_prop() {
		 prop=new Properties();
		 try {
			FileInputStream ip=new FileInputStream("./src/main/java/com/qa/opencart/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		return prop;
		
	}
	
	/**
	 * This method is used to take the screenshot
	 * @return Path of the screenshot
	 */
	
	public String getScreenShot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}

}
