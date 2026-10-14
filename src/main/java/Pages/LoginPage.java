package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriverWait wait;
    private WebDriver driver;
    private By usernameFiled = By.id("user-name");
    private By passwordFiled = By.id("password");
    private By loginbutton = By.id("login-button");
    private By errorMessage = By.cssSelector(".error-message-container");
    public LoginPage(WebDriver driver){
     this.driver=driver;
     this.wait =new WebDriverWait(driver, Duration.ofSeconds(5));
 }
 public void enterUsername(String username){
   wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFiled));
    // WebElement filed = driver.findElement(usernameFiled);
     //filed.clear();
     //filed.sendKeys(username);
     driver.findElement(usernameFiled).clear();
     driver.findElement(usernameFiled).sendKeys(username);
 }
 public void enterPassword(String password){
 wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFiled));
 //WebElement filed = driver.findElement(passwordFiled);
 //filed.clear();
 //filed.sendKeys(password);
     driver.findElement(passwordFiled).clear();
     driver.findElement(passwordFiled).sendKeys(password);


 }

 public void clickOnbutton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginbutton));
 //WebElement filed =driver.findElement(loginbutton);
 //filed.click();
     driver.findElement(loginbutton).click();
 }
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage).getText();
    }

    public InventoryPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickOnbutton();
        try {
            Thread.sleep(1500); // إيقاف مؤقت لمدة 1.5 ثانية
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new InventoryPage(driver);
    }
    public boolean isRedirectedToInventory() {
        return driver.getCurrentUrl().contains("/inventory.html");
    }

}
