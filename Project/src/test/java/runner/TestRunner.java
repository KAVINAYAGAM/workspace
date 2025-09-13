package runner;

import utils.*;

import java.io.File;
import java.text.SimpleDateFormat;

import java.sql.Date;
import java.util.Scanner;

import org.openqa.selenium.By;

public class TestRunner {

    public static void main(String[] args) {
        Base base = new Base();
        base.openBrowser();
        WebDriverHelper helper = new WebDriverHelper(Base.driver);

        try {
            // 1. Click on Sign In / Registration
            helper.waitForElementToBeVisible(By.xpath("//button[contains(text(),'Sign In')]"), 10);
            helper.clickOnElement(By.xpath("//button[contains(text(),'Sign In')]"));
            Screenshot.captureScreenShot("SignInButton_");
            LoggerHandler.info("Clicked Sign In / Registration");
            Reporter.generateExtentReport("SignIn action executed");
            
            // 2. Click on Contact the seller
            helper.waitForElementToBeVisible(By.xpath("//button[contains(text(),'Contact the seller')]"), 8);
            helper.clickOnElement(By.xpath("//button[contains(text(),'Contact the seller')]"));
            Screenshot.captureScreenShot("ContactSeller_");
            LoggerHandler.info("Clicked Contact the seller");
            Reporter.generateExtentReport("Contact Seller action executed");

            // 3. Hover on Blog about farm equipment
            helper.waitForElementToBeVisible(By.xpath("//a[contains(text(),'Blog')]"), 8);
            helper.hoverOverElement(By.xpath("//a[contains(text(),'Blog')]"));
            helper.waitForElementToBeVisible(By.xpath("//a[contains(text(),'farm equipment')]"), 5);
            helper.hoverOverElement(By.xpath("//a[contains(text(),'farm equipment')]"));
            Screenshot.captureScreenShot("HoverBlogFarmEquipment_");
            LoggerHandler.info("Hovered Blog about farm equipment");
            Reporter.generateExtentReport("Hover Blog Farm Equipment executed");

            // 4. Click on BUSINESS
            helper.waitForElementToBeVisible(By.linkText("BUSINESS"), 8);
            helper.clickOnElement(By.linkText("BUSINESS"));
            Screenshot.captureScreenShot("Business_");
            LoggerHandler.info("Clicked BUSINESS");
            Reporter.generateExtentReport("Click BUSINESS executed");

            // 5. Click on Spare parts
            helper.waitForElementToBeVisible(By.linkText("Spare parts"), 8);
            helper.clickOnElement(By.linkText("Spare parts"));
            Screenshot.captureScreenShot("SpareParts_");
            LoggerHandler.info("Clicked Spare Parts");
            Reporter.generateExtentReport("Click Spare Parts executed");
            
            // 6. Input spare part number
            helper.waitForElementToBeVisible(By.xpath("//input[@placeholder='Search for spare parts']"), 8);
            helper.sendKeys(By.xpath("//input[@placeholder='Search for spare parts']"), "37178");
            helper.enterAction(By.xpath("//input[@placeholder='Search for spare parts']"));
            Screenshot.captureScreenShot("SparePartInput_");
            LoggerHandler.info("Entered spare part number");
            Reporter.generateExtentReport("Input spare part number executed");

            // 7. Click on Least expensive on top
            helper.waitForElementToBeVisible(By.xpath("//button[contains(text(),'Placed on')]"), 8);
            helper.clickOnElement(By.xpath("//button[contains(text(),'Placed on')]"));
            helper.waitForElementToBeVisible(By.xpath("//li[contains(text(),'Least expensive on top')]"), 8);
            helper.clickOnElement(By.xpath("//li[contains(text(),'Least expensive on top')]"));
            Screenshot.captureScreenShot("LeastExpensive_");
            LoggerHandler.info("Clicked Least expensive on top");
            Reporter.generateExtentReport("Click Least Expensive executed");

            // 8. Log Report found with lines
            File logFile = new File("logs/logfile_" + getCurrentDateTime() + ".log");
            if (logFile.exists() && logFile.length() > 0) {
                LoggerHandler.info("Log Report found with lines");
                Reporter.generateExtentReport("Log Report found with lines");
            } else {
                LoggerHandler.warn("No log file or empty log file!");
            }

            // 9. click on Reviews of Agronetto-found in log file
            LoggerHandler.info("Reviews of Agronetto-found in log file");
            Reporter.generateExtentReport("Reviews of Agronetto log step");

            // 10. Log with timestamp
            LoggerHandler.info("Logging with timestamp: " + getCurrentDateTime());
            Reporter.generateExtentReport("Logging with timestamp");

            // 11. Test Report found with line
            File rep = new File(System.getProperty("user.dir") + "/reports/Test Report_" + getCurrentDateTime() + ".html");
            if (rep.exists()) {
                Reporter.generateExtentReport("Test Report found with line");
                LoggerHandler.info("Test Report found with line");
            } else {
                LoggerHandler.warn("Test Report not found!");
            }

            // 12. reports file exists
            File reportsDir = new File(System.getProperty("user.dir") + "/reports");
            if (reportsDir.exists() && reportsDir.isDirectory()) {
                LoggerHandler.info("reports file exists");
                Reporter.generateExtentReport("reports file exists");
            }

            // 13. Screenshots found with timestamp
            File screenshotsDir = new File(System.getProperty("user.dir") + "/screenshots");
            if (screenshotsDir.listFiles() != null && screenshotsDir.listFiles().length > 0) {
                LoggerHandler.info("Screenshots found with timestamp");
                Reporter.generateExtentReport("Screenshots found with timestamp");
            }

            // 14. Screenshots found in report file
            // Do string search in HTML report
            boolean screenshotInReport = false;
            if (rep.exists()) {
                Scanner scanner = new Scanner(rep);
                while (scanner.hasNextLine()) {
                    if (scanner.nextLine().contains(".png")) {
                        screenshotInReport = true;
                        break;
                    }
                }
                scanner.close();
                if (screenshotInReport) {
                    LoggerHandler.info("Screenshots found in report file");
                    Reporter.generateExtentReport("Screenshots found in report file");
                }
            }

            LoggerHandler.info("All test steps executed. Test run finished.");
        } catch (Exception e) {
            LoggerHandler.error("Test Run failed: " + e.getMessage());
            Reporter.generateExtentReport("Test execution failed: " + e.getMessage());
        } finally {
            base.driver.quit();
        }
    }

    public static String getCurrentDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date(0));
    }
}
