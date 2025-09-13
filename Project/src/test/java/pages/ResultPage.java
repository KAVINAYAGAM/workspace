// package pages;

// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import utils.*;

// public class ResultPage {
//     WebDriver driver;

//     public ResultPage(WebDriver driver) {
//         this.driver = driver;
//     }

//     public void sortLeastExpensiveOnTop() {
//         driver.findElement(By.xpath("//button[contains(text(),'Placed on')]")).click();
//         driver.findElement(By.xpath("//li[contains(text(),'Least expensive on top')]")).click();
//         LoggerHandler.logInfo("Sorted by least expensive on top");
//         Reporter.reportStep("Clicked least expensive on top", true);
//         Screenshot.capture(driver, "SortedLeastExp");
//     }
// }
