package me.mahmudul;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DropdownHandaling {

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
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/registration_form.html");
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void selectByIndex() throws InterruptedException {
        ElementHandle gender = page.querySelector("#gender");
        gender.selectOption(new SelectOption().setIndex(1));
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void selectByValue() throws InterruptedException {
        ElementHandle gender = page.querySelector("#gender");
        gender.selectOption(new SelectOption().setValue("male"));
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void selectByVisibleText() throws InterruptedException {
        ElementHandle gender = page.querySelector("#gender");
        gender.selectOption(new SelectOption().setLabel("Other"));
        Thread.sleep(2000);
    }


    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();

    }
}
