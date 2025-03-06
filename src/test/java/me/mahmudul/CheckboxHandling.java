package me.mahmudul;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class CheckboxHandling {

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
    public void clickOnCheckbox() throws InterruptedException {
        ElementHandle checkbox = page.querySelector("//input[@id='hobby1']");
        checkbox.check();
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void clickMultipleCheckbox() throws InterruptedException {
        List<ElementHandle> checkbox = page.querySelectorAll("//input[@class=\"form-check-input\"]");
        for (ElementHandle handle : checkbox) {
             if(!handle.isChecked()){
                 handle.check();
             }
            Thread.sleep(2000);
        }

    }

    @Test(dependsOnMethods = "openUrl")
    public void uncheckCheckbox() throws InterruptedException {
       List<ElementHandle> checkbox = page.querySelectorAll("//input[@class=\"form-check-input\"]");
        for (ElementHandle handle : checkbox) {
            if(handle.isChecked()){
                handle.uncheck();
            }
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
    }



    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();

    }
}
