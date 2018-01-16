package org.mvnjbehaveselinium.steps;

import junit.framework.Assert;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Kedar on 13-01-2018.
 */
public class TestStorySteps extends Steps {

    private WebDriver driver;
    WebDriverWait wait;

    public void TestStories(WebDriver driver){

        this.driver=driver;
        wait=new WebDriverWait(driver,20);

    }

    @Given("User navigates to application login page")

    public void userNaviagtesToApplicationLoginPage()throws InterruptedException{
        driver=new FirefoxDriver();
        driver.get("http://www.gmail.com");
        driver.manage().window().maximize();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@id='identifierId']")));
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
        String errmsg=driver.findElement(By.xpath(".//div[@class='dEOOab RxsGPe']")).getText();
        Assert.assertEquals("Couldn't find your Google Account",errmsg);
        System.out.println("User's wrong credentials are not accepted");
        driver.quit();
    }

}
