package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.google.common.io.Files;

public class Screenshot extends Base {

	public static TakesScreenshot ts;

	public static void captureScreenShot(String filename) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String name = filename + timestamp + ".png";
		ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);

		// Create the screenshots directory if it doesn't exist
		File screenshotsDir = new File(System.getProperty("user.dir") + "/screenshots");
		if (!screenshotsDir.exists()) {
			screenshotsDir.mkdirs();
		}

		File target = new File(screenshotsDir, name);
		try {
			Files.copy(file, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}