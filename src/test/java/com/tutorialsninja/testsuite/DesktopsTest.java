package com.tutorialsninja.testsuite;

import com.tutorialsninja.pages.CommonHomePage;
import com.tutorialsninja.pages.DesktopsPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.testbase.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends BaseTest {
    HomePage homePage = new HomePage();
    CommonHomePage commonHomePage = new CommonHomePage();
    DesktopsPage desktopsPage = new DesktopsPage();

    //1.Test name verifyProductArrangeInAlphaBeticalOrder()
    @Test
    public void verifyProductArrangeInAlphaBeticalOrder() {
        //1.1 Mouse hover on Desktops Tab.and click
        homePage.mouseHoverOnDesktopsTabAndClick();
        //1.2 Click on “Show All Desktops”
        homePage.clickOnShowAllDesktops();
        //1.3 Select Sort By position "Name: Z to A"
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Name (Z - A)");
        //1.4 Verify the Product will arrange in Descending order
        List<WebElement> products = getListOfElements(By.xpath("//h4/a"));
        ArrayList<String> originalProductsName = new ArrayList<>();
        for (WebElement e : products) {
            originalProductsName.add(e.getText());
        }
        System.out.println(originalProductsName);
        // Sort By Reverse order
        Collections.reverse(originalProductsName);
        System.out.println(originalProductsName);
        //1.3 Select Sort By position "Name: Z to A"
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Name (Z - A)");
        // After filter Z -A Get all the products name and stored into array list
        products = getListOfElements(By.xpath("//h4/a"));
        ArrayList<String> afterSortByZToAProductsName = new ArrayList<>();
        for (WebElement e : products) {
            afterSortByZToAProductsName.add(e.getText());
        }
        System.out.println(afterSortByZToAProductsName);
        Assert.assertEquals(originalProductsName, afterSortByZToAProductsName, "Product not sorted into Z to A order");
    }

    //2. Test name verifyProductAddedToShoppingCartSuccessFully()
    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() {
        //2.1 Mouse hover on Currency Dropdown and click
        homePage.mouseHoverOnCurrencyDropdownAndClick();
        //2.2 Mouse hover on £Pound Sterling and click
        homePage.mouseHoverOnPoundSterlingAndClick();
        //2.3 Mouse hover on Desktops Tab.
       commonHomePage.mouseHoverOnDesktopsTab();
        //2.4 Click on “Show All Desktops”
        homePage.clickOnShowAllDesktops();
        //2.5 Select Sort By position "Name: A to Z"
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Name (A - Z)");
        //2.6 Select product “HP LP3065”
         desktopsPage.selectProductHPLP3065();
        //2.7 Verify the Text "HP LP3065"
        Assert.assertEquals("HP LP3065 Product not display", "HP LP3065",
                getTextFromElement(By.xpath("//h1[contains(text(),'HP LP3065')]")));
        //2.8 Select Delivery Date "2023-11-27"
        String year = "2025";
        String month = "November";
        String date = "30";
        clickOnElement(By.xpath("//div[@class = 'input-group date']//button"));
        while (true) {
            String monthAndYear = driver.findElement(By.xpath("//div[@class = 'datepicker']/div[1]//th[@class='picker-switch']")).getText();
            String[] arr = monthAndYear.split(" ");
            String mon = arr[0];
            String yer = arr[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[@class = 'datepicker']/div[1]//th[@class='next']"));
            }
        }
        List<WebElement> allDates = getListOfElements(By.xpath("//div[@class = 'datepicker']/div[1]//tbody/tr/td[@class = 'day']"));
        for (WebElement e : allDates) {
            if (e.getText().equalsIgnoreCase(date)) {
                e.click();
                break;
            }
        }
        //2.9.Enter Qty "1” using Select class.
        desktopsPage.enterQty1UsingSelectClass();
        //2.10 Click on “Add to Cart” button
        desktopsPage.clickOnAddToCartButton();
        //2.11 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        Assert.assertTrue(
                getTextFromElement(By.cssSelector("body:nth-child(2) div.container:nth-child(4) > div.alert.alert-success.alert-dismissible"))
                        .contains("Success: You have added HP LP3065 to your shopping cart!"),"Product not added to cart");
        //2.12 Click on link “shopping cart” display into success message
        desktopsPage.clickOnLinkShoppingCartDisplayIntoSuccessMessage();
        //2.13 Verify the text "Shopping Cart"
        Assert.assertTrue(getTextFromElement(By.xpath("//div[@id='content']//h1")).contains("Shopping Cart"));
        Assert.assertEquals("Product name not matched", "HP LP3065", getTextFromElement(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[2]/a")));
        //2.13 Verify the Delivery Date "2025-11-30"
        Assert.assertTrue( getTextFromElement(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[2]/small[1]")).contains("2025-11-30"),"Delivery date not matched");
        //2.14 Verify the Model "Product21"
        Assert.assertEquals("Model not matched", "Product 21", getTextFromElement(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[3]")));
        //2.15 Verify the Todat "£74.73"
        Assert.assertEquals("Total not matched", "£74.73", getTextFromElement(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[6]")));

    }
}