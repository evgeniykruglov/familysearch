package org.familysearch;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FirstTest {

    private String metricBookUrl;
    private String pathToVpnTxt = ".\\src\\test\\resources\\vpn_login.txt";
    private String pathToFamilysearchTxt = ".\\src\\test\\resources\\familysearch_login.txt";
    private static String pathToBooksTxt = ".\\src\\test\\resources\\books.csv";
    private int startPage;
    private int endPage;
    private static HashMap hashMapOfTitlesAndLinks;

    //@Test
    public static void main(String Args[]) {

        URLConfigParser urlcp = new URLConfigParser();
        hashMapOfTitlesAndLinks = (HashMap)urlcp.getListOfBooks(pathToBooksTxt);
        GUI app = new GUI(hashMapOfTitlesAndLinks);
        app.setVisible(true);
    }

    public void variablesSetter(int firstValue, int secondValue) {
        this.startPage = firstValue;
        this.endPage = secondValue;
    }

    public void firstTest() {
        metricBookUrl = MetricBookUrlConfigurator.metricBookUrlSetter(hashMapOfTitlesAndLinks);

        LoginConfigParser vpnParser = new LoginConfigParser();
        vpnParser.readLogPass(pathToVpnTxt);

        LoginConfigParser familysearchParser = new LoginConfigParser();
        familysearchParser.readLogPass(pathToFamilysearchTxt);

        WebDriver driver = OperaDriverPreparation.runOperaDriver();
        Actions actions = new Actions(driver);
        VPNAuth.surfEasyAuth(driver, vpnParser.getLogin(), vpnParser.getPassword());

        Familysearch downloader = new Familysearch();
        downloader.authorizator(driver, familysearchParser.login, familysearchParser.password);
        downloader.pageDiscoverer(driver, metricBookUrl);
        downloader.pageSetter(driver, actions, startPage);
        downloader.photoLoader(driver, actions, endPage);
    }
}
