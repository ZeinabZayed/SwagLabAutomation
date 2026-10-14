package tests;

import Pages.LoginPage;
import base.BaseTest;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Pages.InventoryPage;
import Utils.DataDriven;

public class InventoryTest extends BaseTest {
    private JSONObject testData;

    @BeforeClass
    public void loadData() {
        testData = DataDriven.jsonReader("testData/testData.json");
    }

    @Test
    public void verifyInventoryPageElements() {
        JSONObject validUser = (JSONObject) testData.get("validUser");
        String username = (String) validUser.get("username");
        String password = (String) validUser.get("password");

        InventoryPage inventoryPage = loginpage.login(username, password);

        Assert.assertEquals(inventoryPage.getPageTitle(), "Swag Labs", "Page title does not match!");
        Assert.assertTrue(inventoryPage.isCartIconDisplayed(), "Cart icon is not displayed!");
        Assert.assertEquals(inventoryPage.getProductsCount(), 6, "Product count is not 6!");
    }
}