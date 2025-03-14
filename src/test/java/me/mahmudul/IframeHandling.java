package me.mahmudul;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class IframeHandling {
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
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/iframe.html");
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void iframeTest() throws InterruptedException {
        List<ElementHandle> iframes = page.querySelectorAll("iframe");
        System.out.println("Iframe: " + iframes.size());


        Frame iframe =  page.frame("googleIframe");
        ElementHandle element = iframe.querySelector("//*[contains(text(),'Programmable Search Engine')]");
        System.out.println(element.textContent());

        page.mainFrame();
    }



    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();

    }
}
