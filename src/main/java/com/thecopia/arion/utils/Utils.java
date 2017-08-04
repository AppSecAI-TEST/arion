package com.thecopia.arion.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	static Logger log = Logger.getLogger(Utils.class);

	public static void waitPageLoading(final WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				JavascriptExecutor js = (JavascriptExecutor) d;
				return (Boolean) js.executeScript("return (document.readyState === 'complete') && (window.jQuery != null) && (jQuery.active === 0);");
			}
		});
	}

	public static void clickOn(WebDriver driver, WebElement element) {
		Utils.waitForElementVisible(driver, element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public static void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static WebDriver setBrowserUnderTest(String browser) throws MalformedURLException {
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver","C://automation//drivers//chromedriver.exe");
			
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			WebDriver driver = new RemoteWebDriver(new URL("http://danielg:4444/wd/hub"), capability);
			return driver;

			//			return new ChromeDriver();
		} else {
			System.setProperty("webdriver.gecko.driver", "C://automation//drivers//geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary("C://Program Files (x86)//Mozilla Firefox//firefox.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("moz:firefoxOptions", options);
			return new FirefoxDriver(capabilities);
		} 
	}
}
