package me.mahmudul;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class KeyboardaAction {
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
    public void keyboardAction() throws InterruptedException {
        ElementHandle firstName = page.querySelector("#first-name");
        firstName.click();
        Thread.sleep(2000);
        firstName.fill("Mahmudul");
        Thread.sleep(2000);

        //select
        page.keyboard().down("Control");
        page.keyboard().press("a");
        page.keyboard().up("Control");
        Thread.sleep(2000);
        // copy
        page.keyboard().down("Control");
        page.keyboard().press("c");
        page.keyboard().up("Control");
        Thread.sleep(2000);

        //Tab
        page.keyboard().press("Tab");

        //paste
        page.keyboard().down("Control");
        page.keyboard().press("v");
        page.keyboard().up("Control");
        Thread.sleep(2000);
    }


    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();

    }

}
