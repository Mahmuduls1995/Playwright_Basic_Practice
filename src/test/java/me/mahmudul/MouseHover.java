package me.mahmudul;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class MouseHover {
    Playwright playwright ;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;



    @BeforeSuite
    public void startBrowser() {
        playwright = Playwright.create();
        browserType = playwright.chromium();
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @Test
    public void openUrl() throws InterruptedException {
        Thread.sleep(2000);
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/menu.html");
        Thread.sleep(2000);
    }

    // Write playwright Hover Method
    @Test(dependsOnMethods = "openUrl")
    public void hoverOnElement() throws InterruptedException {
        ElementHandle element = page.querySelector("//a[@id='productsDropdown']");
        element.hover();
        Thread.sleep(5000);
        // Categories hover
        ElementHandle categories = page.querySelector("//a[contains(text(),'Categories')]");
        categories.hover();
        Thread.sleep(5000);


    }







    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();

    }

}
