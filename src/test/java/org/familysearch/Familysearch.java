package org.familysearch;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Familysearch {
    public void authorizator(WebDriver driver, String login, String password) {
        String path = "https://familysearch.org/auth/familysearch/login";
        driver.get(path);
        try {
            TimeUnit.SECONDS.sleep(10);
            WebElement usernameElement = driver.findElement(By.id("userName"));
            WebElement passwordElement = driver.findElement(By.id("password"));
            WebElement submit = driver.findElement(By.id("login"));
            usernameElement.click();
            usernameElement.sendKeys(login);
            passwordElement.click();
            passwordElement.sendKeys(password);
            submit.click();
        }
        catch (InterruptedException ie) {
            System.out.println("Error of loading");
        }
    }

    public void pageDiscoverer(WebDriver driver, String url) {
        driver.get(url);
        try {
            TimeUnit.SECONDS.sleep(20);
        }
        catch (InterruptedException ie) {
            System.out.println("Error of loading");
        }
    }
    public void pageSetter (WebDriver driver, Actions actions, int startPage) {
        waiter(5);
        WebElement currentPage = ((OperaDriver) driver).findElementByName("currentTileNumber");
        System.out.println(currentPage.getText() + " " + currentPage.getTagName() + " " + currentPage.getLocation());
        //WebElement currentPage = ((OperaDriver) driver).findElementByCssSelector(".openSDPagerInputText");
        actions.moveToElement(currentPage).click().build().perform();
        actions.moveToElement(currentPage).doubleClick().build().perform();
        actions.moveToElement(currentPage).sendKeys(Keys.chord(Keys.CONTROL, "a")).build().perform();
        actions.moveToElement(currentPage).sendKeys(Keys.DELETE).build().perform();
        actions.moveToElement(currentPage).sendKeys(Integer.toString(startPage)).build().perform();
        actions.moveToElement(currentPage).sendKeys(Keys.ENTER).build().perform();
        waiter(5);
    }

    public void waiter(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        }
        catch (InterruptedException ie) {
            System.out.println("Error of page loading");
        }
    }

    public void photoLoader(WebDriver driver, Actions actions, int endPage) {
        WebElement downloadButton = ((OperaDriver) driver).findElementById("saveLi");
        WebElement nextButton = ((OperaDriver) driver).findElementByCssSelector(".next");
        //WebElement currentPage = ((OperaDriver) driver).findElementByCssSelector(".openSDPagerInputText");
        WebElement currentPage = ((OperaDriver) driver).findElementByName("currentTileNumber");
        WebElement lastPage = ((OperaDriver) driver).findElementByCssSelector(".afterInput");

        Integer currentPageNumber = Integer.parseInt(currentPage.getAttribute("value"));
        Integer lastPageNumber = Integer.parseInt(lastPage.getText().split(" ")[1]);
        //File downloadedFile = new File("C");

        for (int i = currentPageNumber; (i <= endPage && i != lastPageNumber); i++ ) {

            actions.moveToElement(downloadButton).click().build().perform();
            actions.moveToElement(nextButton).click().build().perform();
            waiter(10);
        }
    }
}
