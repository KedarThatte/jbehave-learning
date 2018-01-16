package org.mvnjbehaveselinium.reusable;

import org.jbehave.core.annotations.*;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Kedar on 13-01-2018.
 */
public class BaseWebDriver {

    public WebDriver driver;
    public WebDriverWait wait;
    int waittime;

    @BeforeStory
    public void openBrowser() throws InterruptedException{
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://gmail.com");
        wait=new WebDriverWait(driver,20);
    }
    /*@BeforeStory
    public WebDriverWait halt(waittime){
        this.driver=driver;

        wait=new WebDriverWait(driver,waittime);
        return wait;
    }*/

    @AfterStory
    public void tearDown(){

        driver.quit();

    }

}
