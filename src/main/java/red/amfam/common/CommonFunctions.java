package red.amfam.common;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import red.amfam.reporting.Loggers;

public class CommonFunctions {

	CommonWaits waits;

	public CommonFunctions(CommonWaits waits) {
		this.waits = waits;
	}

	public void click(WebElement element) {
		try {
			waits.waitUntilClickable(element);
			element.click();
			Loggers.getLog(element + " : This element has been clicked");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + " : This element not found");
			Assert.fail();
		}
	}

	public void checkBox(WebElement element) {
		try {
			element.click();
			Loggers.getLog(element + " : This element has been clicked");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + " : This element not found");
			Assert.fail();
		}
	}

	public String getText(WebElement element) {
		String valueString = null;
		try {
			valueString = element.getText();
			Loggers.getLog(element + " : has value ---> " + valueString);
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.getLog(element + " : This element not found");
			Assert.fail();
		}
		return valueString;
	}

	public String getCurrentUrl(WebDriver driver) {
		Loggers.getLog("Current url is : " + driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}

	public void inputValues(WebElement element, String value) {
		try {
			element.sendKeys(value);
			Loggers.getLog(value + " : This values has been passed into ---> " + element);
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + " : This element Not Found");
			Assert.fail();
		}
	}

	public void hoverAction(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		try {
			actions.moveToElement(element).build().perform();
			Loggers.getLog(element + " : This element has been found");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + " : This element not found");
			Assert.fail();
		}
	}

	public void clear(WebElement element) {
		try {
			element.clear();
			Loggers.getLog("Value has been cleared from this element ---> " + element);
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + " : This element Not Found");
			Assert.fail();
		}
	}

	public void selectDropdown(WebElement element, String value) {
		try {
			Select select = new Select(element);
			select.selectByValue(value);
			Loggers.getLog(value + " : This value has been passed into this element ---> " + element);
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + " : This element Not Found");
			Assert.fail();
		}
	}
}
