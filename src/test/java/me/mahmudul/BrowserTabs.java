package me.mahmudul;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BrowserTabs {
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

//    @Test
    public void openUrl() throws InterruptedException {
        Page fistTab = browserContext.newPage();
        fistTab.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/browser_windows.html");
        Thread.sleep(2000);

        Page secondTab = browserContext.newPage();
        secondTab.bringToFront();
        secondTab.navigate("https://testing-and-learning-hub.vercel.app");
        Thread.sleep(2000);

        fistTab.bringToFront();
        Thread.sleep(2000);

    }

    @Test
    public void windowHandle() throws InterruptedException {
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/browser_windows.html");
        Thread.sleep(3000);

        Page secondWindow = browser.newContext().newPage();
        secondWindow.bringToFront();
        secondWindow.navigate("https://testing-and-learning-hub.vercel.app");
        Thread.sleep(4000);

        page.bringToFront();
        Thread.sleep(4000);
    }



    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();

    }

}
