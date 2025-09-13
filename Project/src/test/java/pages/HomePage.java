// package pages;

// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import utils.*;

// public class HomePage {
//     WebDriver driver;

//     public HomePage(WebDriver driver) {
//         this.driver = driver;
//     }

//     public void clickSignIn() {
//         driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
//         LoggerHandler.logInfo("Clicked Sign In");
//         Reporter.reportStep("Clicked Sign In button", true);
//         Screenshot.capture(driver, "SignInButton");
//     }

//     public void clickContactSeller() {
//         driver.findElement(By.xpath("//button[contains(text(),'Contact the seller')]")).click();
//         LoggerHandler.logInfo("Clicked Contact Seller");
//         Reporter.reportStep("Clicked Contact Seller", true);
//         Screenshot.capture(driver, "ContactSellerBtn");
//     }

//     public void hoverOnBlogFarmEquipment() {
//         // Add Actions hover code to Blog menu and select Farm Equipment
//         LoggerHandler.logInfo("Hovered Blog -> Farm Equipment");
//         Reporter.reportStep("Hovered blog farm equipment", true);
//         Screenshot.capture(driver, "HoverBlogFarmEq");
//     }

//     public void clickBusiness() {
//         driver.findElement(By.linkText("BUSINESS")).click();
//         LoggerHandler.logInfo("Clicked BUSINESS");
//         Reporter.reportStep("Clicked BUSINESS link", true);
//         Screenshot.capture(driver, "BusinessClick");
//     }

//     public void clickSpareParts() {
//         driver.findElement(By.linkText("Spare parts")).click();
//         LoggerHandler.logInfo("Clicked Spare Parts");
//         Reporter.reportStep("Clicked Spare Parts", true);
//         Screenshot.capture(driver, "SparePartsClick");
//     }

//     public void inputSparePartNumber(String num) {
//         driver.findElement(By.xpath("//input[@placeholder='Search for spare parts']")).sendKeys(num + "\n");
//         LoggerHandler.logInfo("Entered spare part number: " + num);
//         Reporter.reportStep("Entered spare part number", true);
//         Screenshot.capture(driver, "SparePartInput");
//     }
// }
