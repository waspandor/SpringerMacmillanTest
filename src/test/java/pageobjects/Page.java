package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

/**
 * Created by Waseem on 8/21/2016.
 */
public class Page {

    protected WebDriver driver;


    public Page(WebDriver driver) {
        this.driver = driver;
    }


    public HomePage navigateToHomePage() {
        driver.get("http://www.bbc.co.uk");
        return new HomePage(driver);
    }


    public AccountPage navigateToAccountPage() {
        driver.findElement(By.id("idcta-link")).click();
        return new AccountPage(driver);
    }


}
