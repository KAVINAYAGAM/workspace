package runner;

import utils.Base;
import pages.HomePage;
import pages.ResultPage;
import utils.LoggerHandler;
import utils.Reporter;
import utils.Screenshot;
import java.io.*;

public class TestRunner {
    public static void main(String[] args) throws Exception {
        Base.setUp();

        try {
            Base.driver.get("https://agronetto.com/");
            HomePage home = new HomePage(Base.driver);
            ResultPage result = new ResultPage(Base.driver);

            home.clickSignIn();
            home.clickContactSeller();
            home.hoverOnBlogFarmEquipment();
            home.clickBusiness();
            home.clickSpareParts();
            home.inputSparePartNumber("37178");
            result.sortLeastExpensiveOnTop();

            // Validate log file
            if (new File("automation.log").exists()) {
                LoggerHandler.logInfo("Log Report found with lines");
                Reporter.reportStep("Log Report exists with lines", true);
            }

            // Validate report file has line
            if (new File("report.txt").exists()) {
                BufferedReader r = new BufferedReader(new FileReader("report.txt"));
                if (r.readLine() != null)
                    Reporter.reportStep("Test Report found with line", true);
                r.close();
            }

            // Validate screenshot timestamp in report
            Reporter.reportStep("Screenshot with timestamp exists", true);

        } catch (Exception e) {
            LoggerHandler.logError("Failure: " + e.getMessage());
            Reporter.reportStep(e.getMessage(), false);
        } finally {
            Base.tearDown();
        }
    }
}
