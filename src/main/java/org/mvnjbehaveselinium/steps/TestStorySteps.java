package org.mvnjbehaveselinium.steps;

import junit.framework.Assert;
import org.jbehave.core.annotations.*;
import org.jbehave.core.reporters.TemplateableViewGenerator;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.Steps;
import org.mvnjbehaveselinium.reusable.BaseWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kedar on 13-01-2018.
 */
public class TestStorySteps extends Steps {

    private WebDriver driver;
    BaseWebDriver browser = null;
    WebDriverWait wait ;

    public void waitForPageLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        final boolean response = js.executeScript("return document.readyState").equals("complete");

        if (response == false) {
            waitForPageLoad();
        }
    }

    @BeforeScenario
    public void run(){
        try {
            browser=new BaseWebDriver();
            browser.openBrowser();
            driver=browser.driver;
            wait=new WebDriverWait(driver,20);
            System.out.println("AUT open in Browser");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Given("User navigates to application login page")

    public void userNaviagtesToApplicationLoginPage()throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//h1[@id='headingText']"))).isDisplayed();
        String signin=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//h1[@id='headingText']"))).getText();
        Assert.assertEquals("Sign in",signin);
        System.out.println("User Opened Application Login Page");
    }
    @When("User validates correct application login page is open")
    public void userValidatesCorrectApplicationLoginPageIsOpen()throws InterruptedException{
        String title=driver.getTitle();
        Assert.assertEquals("Gmail",title);
        System.out.println("User Opened Login Page of Correct Application");
    }
    @Then("User enters his invalid credentials")
    public void userEntersHisValidCredentials()throws InterruptedException{
        driver.findElement(By.xpath(".//input[@id='identifierId']")).sendKeys("Test");
        driver.findElement(By.xpath(".//span[text()='Next']")).click();
        String errmsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='dEOOab RxsGPe']"))).getText();
        Assert.assertEquals("Couldn't find your Google Account",errmsg);
        System.out.println("User's wrong credentials are not accepted");
        driver.quit();
    }

    @AfterScenario
    public void closeBrowser(){
        browser.tearDown();
        System.out.println("Browser Closed");

    }

}
