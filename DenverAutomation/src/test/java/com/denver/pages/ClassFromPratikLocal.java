package test1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ClassFromPratikLocal {
@Test
	public static void openGoogle() {
		 WebDriver driver = new ChromeDriver();
		 driver.get("https://google.com");
		 System.out.println(driver.getTitle());
		 driver.quit();

	}

}
