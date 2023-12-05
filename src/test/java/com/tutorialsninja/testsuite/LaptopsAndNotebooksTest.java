package com.tutorialsninja.testsuite;

import com.tutorialsninja.pages.CheckOutPage;
import com.tutorialsninja.pages.DesktopsPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.LaptopsAndNoteBookPage;
import com.tutorialsninja.testbase.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaptopsAndNotebooksTest extends BaseTest {
    HomePage homePage = new HomePage();
    LaptopsAndNoteBookPage laptopsAndNoteBookPage = new LaptopsAndNoteBookPage();
    DesktopsPage desktopsPage = new DesktopsPage();
    CheckOutPage checkOutPage = new CheckOutPage();

    //1. Test name verifyProductsPriceDisplayHighToLowSuccessfully()
    @Test
    public void TestNameVerifyProductsPriceDisplayHighToLowSuccessfully() {
        //1.1 Mouse hover on Laptops & Notebooks Tab.and click
        homePage.mouseHoverOnLaptopsAndNotebooksTabAndClick();
        //1.2 Click on “Show All Laptops & Notebooks”
        homePage.clickOnShowAllDesktops();
        //1.3 Select Sort By "Price (High > Low)"
        List<WebElement> products = getListOfElements(By.xpath("//p[@class ='price']"));
        List<Double> originalProductsPrice = new ArrayList<>();
        for (WebElement e : products) {
            System.out.println(e.getText());
            String[] arr = e.getText().split("Ex Tax:");
            originalProductsPrice.add(Double.valueOf(arr[0].substring(1).replaceAll(",", "")));
        }
        System.out.println(originalProductsPrice);
        // Sort By Reverse order
        Collections.sort(originalProductsPrice, Collections.reverseOrder());
        System.out.println(originalProductsPrice);
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Price (High > Low)");
        //1.4 Verify the Product price will arrange in High to Low order.
        products = getListOfElements(By.xpath("//p[@class ='price']"));
        ArrayList<Double> afterSortByPrice = new ArrayList<>();
        for (WebElement e : products) {
            String[] arr = e.getText().split("Ex Tax:");
            afterSortByPrice.add(Double.valueOf(arr[0].substring(1).replaceAll(",", "")));
        }
        System.out.println(afterSortByPrice);
        //1.4 Verify the Product price will arrange in High to Low order.
        Assert.assertEquals(originalProductsPrice, afterSortByPrice, "Product not sorted by price High to Low");
    }

    @Test
    //2. Test name verifyThatUserPlaceOrderSuccessfully()
    public void testNameVerifyThatUserPlaceOrderSuccessfully() {
        //2.1 Mouse hover on Laptops & Notebooks Tab and click
        homePage.mouseHoverOnDesktopsTabAndClick();
        //2.2 Click on “Show All Laptops & Notebooks”
        homePage.clickOnShowAllDesktops();
        //2.3 Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Price (High > Low)");
        //2.4 Select Product “MacBook”
        laptopsAndNoteBookPage.selectProductMacBook();
        //2.5 Verify the text “MacBook”
        //2.5 Verify the text “MacBook”
        Assert.assertEquals("MacBook Product not display", "MacBook",
                getTextFromElement(By.xpath("//h1[contains(text(),'MacBook')]")));
        //2.6 Click on ‘Add To Cart’ button
        desktopsPage.clickOnAddToCartButton();
        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        Assert.assertTrue(
                getTextFromElement(By.cssSelector("body:nth-child(2) div.container:nth-child(4) > div.alert.alert-success.alert-dismissible"))
                        .contains("Success: You have added MacBook to your shopping cart!"), "Product not added to cart");
        //2.8 Click on link “shopping cart” display into success message
        desktopsPage.clickOnLinkShoppingCartDisplayIntoSuccessMessage();
        //2.9 Verify the text "Shopping Cart"
        Assert.assertTrue(getTextFromElement(By.xpath("//div[@id='content']//h1")).contains("Shopping Cart"));
        //2.10 Verify the Product name "MacBook"
        Assert.assertEquals("Product name not matched", "MacBook", getTextFromElement(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[2]/a")));
        //2.11 Change Quantity "2"
        sendTextToElement(By.xpath("//input[contains(@name, 'quantity')]"), "2");
        //2.12 Click on “Update” Tab
        //2.13 Verify the message “Success: You have modified your shopping cart!”
        Assert.assertTrue(getTextFromElement(By.xpath("//div[@id='checkout-cart']/div[1]")).contains("Success: You have modified your shopping cart!"), "Cart not modified");
        //2.14 Verify the Total £737.45
        Assert.assertEquals("Total not matched", "£737.45", getTextFromElement(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[6]")));
        //2.15 Click on “Checkout” button
        checkOutPage.clickOnCheckoutButton();
        //2.16 Verify the text “Checkout”
        Assert.assertEquals("Checkout", getTextFromElement(By.xpath("//h1[normalize-space()='Checkout']")));
        //2.17 Verify the Text “New Customer”
        Assert.assertEquals("New Customer", getTextFromElement(By.xpath("//h2[normalize-space()='New Customer']")));
        //2.18 Click on “Guest Checkout” radio button
        checkOutPage.clickOnGuestCheckoutRadioButton();
        //2.19 Click on “Continue” tab
        checkOutPage.clickOnContinueTab();
        //2.20 Fill the mandatory fields
        checkOutPage.fillTheMandatoryFields();
        //2.21 Click on “Continue” Button
        checkOutPage.clickOnContinueTab();
        //2.22 Add Comments About your order into text area
        sendTextToElement(By.xpath("//textarea[@name='comment']"), "Nothing");
        //2.23 Check the Terms & Conditions check box
        checkOutPage.clickOnAgreeToTermsAndConditions();
        //2.24 Click on “Continue” button
        clickOnElement(By.xpath("//input[@id='button-payment-method']"));

        //2.25 Verify the message “Warning: Payment method required!”;
        Assert.assertEquals("Warning: Payment method required!",getListOfElements(By.xpath("//div[@class='alert alert-danger alert-dismissible']")));
        }
    }
