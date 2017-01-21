package pageobjects;


import org.openqa.selenium.By;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class AccountPage extends Page {

    public static String userName;
    public static String newUser;
    String validPassword = "password321";

    public AccountPage(WebDriver driver) {
        super(driver);
        //Will need to add a wait for this to work reliably
        //assertPage();
    }


    public void assertPage() {

        assertTrue(driver.getTitle().equals("BBC – Sign in"));
    }

    public void clickRegister() {
        driver.findElement(By.linkText("Register now")).click();
    }

    public void selectOver18() {
        driver.findElement(By.linkText("18 or over")).click();
    }

    public void enterNewEmailAddress() {
        Random rand = new Random();
        int value = rand.nextInt(9999);
        userName = value +"macmillan";
        newUser = userName + "@putsbox.com";
        driver.findElement(By.id("email-input")).sendKeys(newUser);
    }

    public void optIn() {
        driver.findElement(By.xpath("//*[@id='marketingOptIn']/div[1]/div[1]/label/div/div")).click();
    }

    public void enterValidUserName() {
        driver.findElement(By.id("username-input")).sendKeys(newUser);
    }

    public void enterValidPassword() {
        driver.findElement(By.id("password-input")).sendKeys(validPassword);
    }

    public void enterValidPostcode() {

        driver.findElement(By.id("postcode-input")).sendKeys("n12qp");
    }

    public HomePage clickSubmit() {
        driver.findElement(By.id("submit-button")).click();
        return new HomePage(driver);
    }

    public void clickOnUserSetting() {
        driver.findElement(By.linkText("Continue to settings")).click();
    }

    public void verifyCorrectUserLoggedIn() {
        /*
        Although this assertion does work, it is extremely primitive and not something I would normally use
        Given more time I would look to implement something more stable and reliable
         */
        assertTrue(driver.getPageSource().contains(newUser));
    }

    public void needHelpSigninIn() {
        driver.findElement(By.linkText("Need help signing in?")).click();
    }

    public void forgottenPasswordLink() {
        driver.findElement(By.linkText("I've forgotten my password")).click();
    }

    public void inputCorrectEmail() {
        driver.findElement(By.id("email-input")).sendKeys(newUser);
    }

    public void inputNewPassword() {
        driver.findElement(By.id("password-input")).sendKeys("Password123");
        driver.findElement(By.id("submit-button")).click();
    }

}