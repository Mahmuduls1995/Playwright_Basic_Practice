package me.mahmudul;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ScrollHandaling {
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
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/scroll.html");
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void scrollToBottom() throws InterruptedException {
        page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "scrollToBottom")
    public void scrollToTop() throws InterruptedException {
        page.evaluate("window.scrollTo(0, 0)");
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "scrollToTop")
    public void scrollBySpecificLocation() throws InterruptedException {
        ElementHandle element = page.querySelector("//h2[contains(text(),'Frequently Asked Questions')]");
        element.scrollIntoViewIfNeeded();
        Thread.sleep(2000);
    }




    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();

    }
}
