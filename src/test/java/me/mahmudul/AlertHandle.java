package me.mahmudul;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AlertHandle {
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
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/javascript_alerts.html");
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void alertTest() throws InterruptedException {
        page.onceDialog(dialog -> {
            System.out.println("Dialog message: " + dialog.message());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            Thread.sleep(2000);
            dialog.accept();
        });


        ElementHandle alertButton = page.querySelector("//button[contains(text(),'Show Alert')]");
        alertButton.click();
        Thread.sleep(2000);
    }


    @Test(dependsOnMethods = "alertTest")
    public void confirmTest() throws InterruptedException {
        page.onceDialog(dialog -> {
            System.out.println("Dialog message: " + dialog.message());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dialog.accept();
        });

        ElementHandle confirmButton = page.querySelector("//button[contains(text(),'Show Confirm')]");
        confirmButton.click();
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods = "confirmTest")
    public void promptTest() throws InterruptedException {
        page.onceDialog(dialog -> {
            System.out.println("Dialog message: " + dialog.message());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dialog.accept("Mahmudul");
        });

        ElementHandle promptButton = page.querySelector("//button[contains(text(),'Show Prompt')]");
        promptButton.click();
        Thread.sleep(5000);
    }


    public void alert(String msg) throws InterruptedException {
        String value = "accept";
        page.onceDialog(dialog -> {
            System.out.println(dialog.type());
            System.out.println(dialog.message());
            if(dialog.type().equals("alert")) {
                dialog.accept();
                System.out.println("You clicked accept");
            }else if(dialog.type().equals("confirm")) {
                if(value.equals("accept")) {
                    dialog.accept();
                }else{
                    dialog.dismiss();
                }
            } else if(dialog.type().equals("prompt")) {
                if(value.equals("accept")) {
                    dialog.accept(msg);
                }else{
                    dialog.dismiss();
                }
            }
        });
    }





    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();

    }

}
