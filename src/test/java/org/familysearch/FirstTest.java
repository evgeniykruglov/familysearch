package org.familysearch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.io.File;

public class FirstTest {
    @Test
    public void firstTest() {
        FileParser vpnLogPassReader = new FileParser();
        String[] vpnLogPass = vpnLogPassReader.getLogPass(".\\src\\test\\resources\\vpn_login.txt");
        String[] familysearchLogPass = vpnLogPassReader.getLogPass(".\\src\\test\\resources\\familysearch_login.txt");

        WebDriver driver = OperaDriverPreparation.runOperaDriver();
        Actions actions = new Actions(driver);
        VPNAuth.surfEasyAuth(driver, vpnLogPass);


//        WebElement link = ((OperaDriver) driver).findElementByCssSelector(".summary .link");
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        jse.executeScript("arguments[0].scrollIntoView()", link);
//        Actions actions = new Actions(driver);
//        actions.moveToElement(link).click().perform();
//        WebElement usernameVPN = ((OperaDriver) driver).findElementByCssSelector(".form-element");
//        WebElement submitVPN = ((OperaDriver) driver).findElementByCssSelector(".summary");
//        usernameVPN.click();
//        actions.moveToElement(usernameVPN).sendKeys(vpnLogPass[0]).build().perform();
//        actions.sendKeys(Keys.TAB).build().perform();
//        actions.sendKeys(vpnLogPass[1]).build().perform();
//        actions.moveToElement(submitVPN).click().build().perform();

//        try {
//            TimeUnit.SECONDS.sleep(5);
//        }
//        catch (InterruptedException ie) {
//            System.out.println("Error of loading");
//        }

        //Main part of code. Should be Uncommented

        String path = "https://familysearch.org/auth/familysearch/login";
        driver.get(path);
        try {
            TimeUnit.SECONDS.sleep(10);
            WebElement username = driver.findElement(By.id("userName"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement submit = driver.findElement(By.id("login"));
            username.click();
            username.sendKeys(familysearchLogPass[0]);
            password.click();
            password.sendKeys(familysearchLogPass[1]);
            submit.click();
        }
        catch (InterruptedException ie) {
            System.out.println("Error of loading");
        }


        driver.get("https://familysearch.org/ark:/61903/3:1:3QSQ-G97B-Q4NN?i=307&wc=M55D-HZ7%3A355832301%2C356477901%2C358107201%2C358107202&cc=2072090");
/*        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        WebElement canvas = ((OperaDriver) driver).findElementByCssSelector(".openseadragon-canvas");
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(canvas));*/

        //WebElement downloadButton = ((OperaDriver) driver).findElementByCssSelector(".actionToolbarSaveButton");


        try {
            TimeUnit.SECONDS.sleep(20);
        }
        catch (InterruptedException ie) {
            System.out.println("Error of loading");
        }

        WebElement downloadButton = ((OperaDriver) driver).findElementById("saveLi");
        WebElement nextButton = ((OperaDriver) driver).findElementByCssSelector(".next");
        WebElement currentPage = ((OperaDriver) driver).findElementByCssSelector(".openSDPagerInputText");
        WebElement lastPage = ((OperaDriver) driver).findElementByCssSelector(".afterInput");

        Integer currentPageNumber = Integer.parseInt(currentPage.getAttribute("value"));
        Integer lastPageNumber = Integer.parseInt(lastPage.getText().split(" ")[1]);
        File downloadedFile = new File("C");

        for (int i = currentPageNumber; i <= 401; i++ ) {

            actions.moveToElement(downloadButton).click().build().perform();
            actions.moveToElement(nextButton).click().build().perform();
            try {
                TimeUnit.SECONDS.sleep(10);
            }
            catch (InterruptedException ie) {
                System.out.println("Error of page loading");
            }

        }


    }
}
