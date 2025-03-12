package me.mahmudul;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class NavigationCommands {
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
    public void navigationCommands() throws InterruptedException {
        ElementHandle home = page.querySelector("//header/div[1]/nav[1]/a[1]");
        home.click();
        Thread.sleep(2000);

        // Go back
        page.goBack();
        Thread.sleep(2000);
        // Go refresh
        page.reload();
        Thread.sleep(2000);
        // Go forward
        page.goForward();
        Thread.sleep(2000);


    }

    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();

    }

}
