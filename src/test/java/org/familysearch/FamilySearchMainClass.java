package org.familysearch;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;

public class FamilySearchMainClass {

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

    FamilySearchMainClass(int firstValue, int secondValue) {
        this.startPage = firstValue;
        this.endPage = secondValue;
    }

    public void familySearchDownloader() {
        metricBookUrl = MetricBookUrlConfigurator.metricBookUrlSetter(hashMapOfTitlesAndLinks);

        LoginConfigParser vpnParser = new LoginConfigParser();
        vpnParser.readLogPass(pathToVpnTxt);

        LoginConfigParser familysearchParser = new LoginConfigParser();
        familysearchParser.readLogPass(pathToFamilysearchTxt);

        WebDriver driver = OperaDriverPreparation.runOperaDriver();
        Actions actions = new Actions(driver);
        VPNAuth.surfEasyAuth(driver, vpnParser.getLogin(), vpnParser.getPassword());

        FamilySearchWorkClass downloader = new FamilySearchWorkClass(driver);
        downloader.authorizator(familysearchParser.login, familysearchParser.password);
        downloader.pageDiscoverer(metricBookUrl);
        do {
            downloader.waiter(2);
            downloader.pageSetter(actions, startPage);
        } while (downloader.getCurrentPageNumber() != startPage);
        downloader.photoLoader(actions, endPage);
        System.exit(0);
    }

}
