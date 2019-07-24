package org.familysearch;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;

public class VPNAuth {
    public static void surfEasyAuth(WebDriver driver, String login, String password) {
        WebElement link = ((OperaDriver) driver).findElementByCssSelector(".summary .link");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", link);
        Actions actions = new Actions(driver);
        actions.moveToElement(link).click().perform();
        WebElement usernameVPN = ((OperaDriver) driver).findElementByCssSelector(".form-element");
        WebElement submitVPN = ((OperaDriver) driver).findElementByCssSelector(".summary");
        usernameVPN.click();
        actions.moveToElement(usernameVPN).sendKeys(login).build().perform();
        actions.sendKeys(Keys.TAB).build().perform();
        actions.sendKeys(password).build().perform();
        actions.moveToElement(submitVPN).click().build().perform();
    }
}
