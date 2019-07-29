package org.familysearch;

import org.junit.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class FirstTest {

    private String metricBookUrl = "https://familysearch.org/ark:/61903/3:1:3QSQ-G97B-Q4NN?i=307&wc=M55D-HZ7%3A355832301%2C356477901%2C358107201%2C358107202&cc=2072090";
    private String pathToVpnTxt = ".\\src\\test\\resources\\vpn_login.txt";
    private String pathToFamilysearchTxt = ".\\src\\test\\resources\\familysearch_login.txt";
    private int startPage;
    private int endPage;

    //@Test
    public static void main(String Args[]) {
        GUI app = new GUI();
        app.setVisible(true);
        //app.handler();
    }

    public void variablesSetter(int firstValue, int secondValue) {
        this.startPage = firstValue;
        this.endPage = secondValue;
    }

    public void firstTest() {
        //System.out.println(startPage);
        InputParametersValidation.inputParametersValidator(startPage, endPage);

        FileParser vpnParser = new FileParser();
        vpnParser.readLogPass(pathToVpnTxt);

        FileParser familysearchParser = new FileParser();
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
