package com.denver.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotUtil {	
	
	//ScreenshotUtil.java and TestListener.java — failure screenshots and reporting hooks

    private ScreenshotUtil() {
    }

    public static void capture(WebDriver driver, String testName) {
        if (driver == null) {
            return;
        }

        try {
            Path directory = Paths.get("test-output", "screenshots");
            Files.createDirectories(directory);

            String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            String safeName = testName.replaceAll(
                "[^a-zA-Z0-9_-]", "_");

            File source = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

            Path destination = directory.resolve(
                safeName + "_" + timestamp + ".png");

            Files.copy(source.toPath(), destination);

            System.out.println("Screenshot saved: " +
                destination.toAbsolutePath());

        } catch (IOException | RuntimeException e) {
            System.err.println("Screenshot capture failed: " +
                e.getMessage());
        }
    }
}
