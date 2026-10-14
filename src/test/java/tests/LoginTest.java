package tests;

import base.BaseTest;
import org.json.simple.JSONObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Pages.InventoryPage;
import Utils.DataDriven;

import java.time.Duration;

public class LoginTest extends BaseTest {
    private JSONObject testData;

    @BeforeClass
    public void loadData() {
        testData = DataDriven.jsonReader("testData/testData.json");
    }

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        JSONObject validUser = (JSONObject)testData.get("validUser");
        String username = (String)validUser.get("username");
        String password = (String)validUser.get("password");

        InventoryPage inventoryPage = loginpage.login(username, password);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isRedirected = wait.until(ExpectedConditions.urlContains("/inventory.html"));
        Assert.assertTrue(inventoryPage.getCurrentUrl().contains("/inventory.html"),
                "User was not redirected to the Inventory page!");
    }

    @Test (priority = 2)
    public void testInvalidLogin() {
        JSONObject invalidUser = (JSONObject) testData.get("invalidUser");
        String username = (String) invalidUser.get("username");
        String password = (String) invalidUser.get("password");

        loginpage.login(username, password);

        String error = loginpage.getErrorMessage();
        Assert.assertTrue(error.contains("Username and password do not match"),
                "Error message does not match expected text!");
    }
    @Test (priority = 3)
    public void testLoginWithoutPassword() {
        JSONObject noPassUser = (JSONObject) testData.get("userWithoutPassword");
        String username = (String) noPassUser.get("username");
        String password = (String) noPassUser.get("password");

        loginpage.login(username, password);

        String error = loginpage.getErrorMessage();
        Assert.assertTrue(error.contains("Password is required"),
                "Error message does not match expected text!");
    }
}
