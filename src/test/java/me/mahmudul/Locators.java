package me.mahmudul;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Locators {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeSuite
    public void start() {
        playwright = Playwright.create();
        browserType = playwright.chromium();
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @Test(priority = 0)
    public void openUrl() throws InterruptedException {
        Thread.sleep(2000);
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/registration_form.html");
        Thread.sleep(2000);
    }

    @Test(priority = 1)
    public void locators() throws InterruptedException {
        ElementHandle firstName = page.querySelector("#first-name"); // By ID
        firstName.fill("Mahmudul");
        Thread.sleep(2000);

        ElementHandle lastName = page.querySelector("[name='last-name']"); // By Name
        lastName.fill("Hasan");
        Thread.sleep(2000);

    }


    @AfterSuite
    public void close() {
        page.close();
        browser.close();
        playwright.close();
    }




}
