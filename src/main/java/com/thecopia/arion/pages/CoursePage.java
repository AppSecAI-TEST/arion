package com.thecopia.arion.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.thecopia.arion.components.NavigationPanel;

public class CoursePage extends LoadableComponent<CoursePage> {

	static Logger log = Logger.getLogger(CoursePage.class);

	WebDriver driver;

	NavigationPanel navPanel;

	@FindBy(css = "[key='course.library']")
	@CacheLookup
	WebElement lblCourseLibrary;

	@FindBy(id = "courseTitle")
	@CacheLookup
	WebElement lblCourceTitle;

	@FindBy(css = ".library-item")
	@CacheLookup
	List<WebElement> elmLibraryItem;
	
	@FindBy (css = "a[href*='curriculum']")
	@CacheLookup
	WebElement mnuLibrary;

	@FindBy (css = "a[href*='questionsAnswers']")
	@CacheLookup
	WebElement mnuAssessments;

	@FindBy (css = "a[href*='notes']")
	@CacheLookup
	WebElement mnuNotebook;
	
	
	public CoursePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.get();
		navPanel = PageFactory.initElements(driver, NavigationPanel.class);
	}

	public LoginPage logout() {
		return navPanel.logout();
	}

	@Override
	protected void isLoaded() throws Error {
		try {
			Assert.assertTrue(lblCourceTitle.isDisplayed());
			log.debug("Course page is loaded");
		} catch (Exception e) {
			log.debug("Course page Assertion Error");
			throw new AssertionError();
		}
	}

	@Override
	protected void load() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(lblCourceTitle));
		log.debug("Course page load()");
	}
	
	public CourseNotebook openCourseNotebook() {
		mnuNotebook.click();
		return new CourseNotebook(driver);
	}

}
