package org.familysearch;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FamilySearchWorkClass {
    WebDriver driver;

    FamilySearchWorkClass(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void authorizator(String login, String password) {
        //waiter(10);
        String path = "https://familysearch.org/auth/familysearch/login";
        driver.get(path);
        try {
            TimeUnit.SECONDS.sleep(20);
            WebElement usernameElement = driver.findElement(By.id("userName"));
            WebElement passwordElement = driver.findElement(By.id("password"));
            WebElement submit = driver.findElement(By.id("login"));
            usernameElement.click();
            usernameElement.sendKeys(login);
            passwordElement.click();
            passwordElement.sendKeys(password);
            submit.click();
            waiter(5);
        }
        catch (InterruptedException ie) {
            System.out.println("Error of loading");
        }
    }

    public void pageDiscoverer(String url) {
        driver.get(url);
        waiter(8);

    }
    public void pageSetter (Actions actions, int startPage) {
        WebElement currentPage = ((OperaDriver) driver).findElementByName("currentTileNumber");
        Action settingOfPage = actions
            .moveToElement(currentPage)
            .click()
            .doubleClick()
            .sendKeys(Keys.chord(Keys.CONTROL, "a"))
            .sendKeys(Keys.DELETE)
            .sendKeys(Integer.toString(startPage))
            .sendKeys(Keys.ENTER).build();
        waiter(1);
        settingOfPage.perform();
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

    public void photoLoader( Actions actions, int endPage) {
        WebElement downloadButton = ((OperaDriver) driver).findElementById("saveLi");
        WebElement nextButton = ((OperaDriver) driver).findElementByCssSelector(".next");
        //WebElement currentPage = ((OperaDriver) driver).findElementByCssSelector(".openSDPagerInputText");
        WebElement currentPage = ((OperaDriver) driver).findElementByName("currentTileNumber");
        WebElement lastPage = ((OperaDriver) driver).findElementByCssSelector(".afterInput");

        Integer currentPageNumber = Integer.parseInt(currentPage.getAttribute("value"));
        Integer lastPageNumber = Integer.parseInt(lastPage.getText().split(" ")[1]);

        for (int i = currentPageNumber; ((i <= lastPageNumber) && (i <= endPage)) ; i++ ) {
            actions.moveToElement(downloadButton).click().build().perform();
            waiter(5);
            currentPageNumber = Integer.parseInt(currentPage.getAttribute("value"));
            FilesWorker.fileFinderAndRenamer(currentPageNumber.toString());
            actions.moveToElement(nextButton).click().build().perform();
            waiter(10);
        }
    }

    public int getCurrentPageNumber() {
        WebElement currentPage = ((OperaDriver) driver).findElementByName("currentTileNumber");
        Integer currentPageNumber = Integer.parseInt(currentPage.getAttribute("value"));
        return currentPageNumber;
    }

}
