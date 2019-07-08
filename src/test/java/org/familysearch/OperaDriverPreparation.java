package org.familysearch;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import java.util.HashMap;
import java.io.File;

public class OperaDriverPreparation {
    public static WebDriver runOperaDriver () {
        HashMap<String, Object> operaPrefs = new HashMap<String, Object>();
        operaPrefs.put("profile.default_content_settings.popups", 0);
        operaPrefs.put("download.default_directory", "D:\\1");
        OperaOptions options = new OperaOptions();
        options.setBinary("C:\\Program Files (x86)\\Opera\\43.0.2442.806\\opera.exe");
        File file = new File("C:\\Users\\RED ANGEL\\AppData\\Roaming\\Opera Software\\Opera Stable\\Extensions\\ebpielhlnnpkiddeeacoephkilopgblc\\surfeasy-proxy.crx");
        options.addExtensions(file);
        options.setExperimentalOption("prefs", operaPrefs);
        System.setProperty("webdriver.opera.driver", "D:\\PROGRAM\\libs\\operadriver_win64\\operadriver.exe");
        WebDriver driver = new OperaDriver(options);
        return driver;
    }
}
