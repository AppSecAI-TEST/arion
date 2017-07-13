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
import com.thecopia.arion.utils.Utils;

public class CourseNotebook extends LoadableComponent<CourseNotebook> {

	static Logger log = Logger.getLogger(CourseNotebook.class);

	WebDriver driver;

	NavigationPanel navPanel;

	@FindBy(css = ".form-control[name='bookFld']")
	@CacheLookup
	WebElement selAllBooks;
	
	@FindBy (css = ".nav-tabs")
	@CacheLookup
	WebElement navNavTabs;

	@FindBy (css = ".filterSelect")
	@CacheLookup
	WebElement dropNotesFilter;
	
	
	@FindBy(css = ".answer.ng-binding")
	@CacheLookup
	List<WebElement> notesContents;
	
	public CourseNotebook(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.navPanel = new NavigationPanel(driver);
//		navPanel = PageFactory.initElements(driver, NavigationPanel.class);
		log.debug("Loading Course Notebook page ...");
		this.get();
		log.debug("Course Notebook page is loaded");
	}

	public LoginPage logout() {
		return navPanel.logout();
	}

	@Override
	protected void isLoaded() throws Error {
		try {
			Utils.waitPageLoading(driver);
			Assert.assertTrue(dropNotesFilter.isDisplayed());
		} catch (Exception e) {
			log.debug("Course Notebook page Assertion Error");
			throw new AssertionError();
		}
	}

	@Override
	protected void load() {
		Utils.waitForElementVisible(driver, dropNotesFilter);
	}
	
	
	public boolean isNoteExistsInCource (String noteSearchTerm) {
		for (WebElement noteContent : notesContents) {
			if (noteContent.getText().contains(noteSearchTerm)) {
				log.debug("Note with search term '" + noteSearchTerm + "' is exists in the cource notebook");
				return true;
			}
		}
		log.debug("Note with search term '" + noteSearchTerm + "' is NOT exists in the cource notebook");
		return false;
	}


}