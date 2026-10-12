package com.denver.utilities;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

	// WaitUtil.java — explicit wait helper.

	private final WebDriverWait wait;

	public WaitUtil(WebDriver driver) {
		int seconds = ConfigReader.getInt("explicitWait");
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
	}

	public WebElement visible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement clickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public boolean urlContains(String value) {
		return wait.until(ExpectedConditions.urlContains(value));
	}
}
