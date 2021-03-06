package com.thecopia.arion.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.thecopia.arion.utils.Utils;

public class CourseNotebookPage extends LoadableComponent<CourseNotebookPage> {

	static Logger log = Logger.getLogger(CourseNotebookPage.class);

	WebDriver driver;

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
	
	public CourseNotebookPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.get();
		log.debug("Course Notebook page is loaded");
	}

	@Override
	protected void isLoaded() throws Error {
		try {
			Utils.waitPageLoading(driver);
			Assert.assertTrue(dropNotesFilter.isDisplayed());
		} catch (Exception e) {
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
				return true;
			}
		}
		return false;
	}


}
