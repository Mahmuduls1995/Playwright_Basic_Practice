package me.mahmudul;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class TableHandling {

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
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/web_table.html");
        Thread.sleep(2000);
    }
//    @Test(dependsOnMethods ="openUrl")
    public void fetchOnTableData() throws InterruptedException {
        List <ElementHandle> rows = page.querySelectorAll("//table[@id='table1']/thead/tr/th");
        System.out.println("Total number of rows: "+rows.size());

        for (ElementHandle row : rows) {
            String rowText = row.innerText();
            System.out.println( "Heading Value: " + rowText);
        }


    }

    @Test(dependsOnMethods ="openUrl")
    public void fetchSpecificRowData() throws InterruptedException {
        List<ElementHandle> table_rows = page.querySelectorAll("//table[@id='table1']/tbody/tr");

        for (int i = 1; i <= table_rows.size(); i++) {
          ElementHandle emailLocator = page.querySelector("//table[@id='table1']/tbody/tr["+i+"]/td[5]");
                String email = emailLocator.innerText();
            System.out.println("Email: "+email);
        }
    }


    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();

    }
}
