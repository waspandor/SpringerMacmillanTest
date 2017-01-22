import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by pandorw on 19/01/2017.
 */
public class StepDefinitions {

    AccountPage accountPage;
    Page page;
    HomePage homePage;
    WebDriver driver;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void runDown() {

        AccountPage.userName = null;
        AccountPage.newUser = null;
        driver.quit();
    }

    @Given("^a new user account is created$")
    public void a_new_user_account_is_created() {
        /*
        Using a fixed test account was causing issues as the site I chose did not allow me to click the forgotten password link
        multiple times. So decided to create a new user account for every test case. A correctly configured test environment
        may not need this step
         */
        homePage = new HomePage(driver).navigateToHomePage();
        accountPage = homePage.navigateToAccountPage();
        accountPage.clickRegister();
        accountPage.selectOver18();
        accountPage.enterNewEmailAddress();
        accountPage.enterValidPassword();
        accountPage.enterValidPostcode();
        accountPage.optIn();
        accountPage.clickSubmit();
    }

    @Given("^The user navigates to the BBC homepage$")
    public void the_user_navigates_to_the_BBC_homepage() {
        driver.manage().deleteAllCookies();
        homePage = new HomePage(driver).navigateToHomePage();
    }

    @Then("^clicks on the My Account link$")
    public void clicks_on_the_sign_in_link() {
        accountPage = homePage.navigateToAccountPage();
    }

    @When("^enters a valid username$")
    public void enters_valid_username() {
        accountPage.enterValidUserName();
    }

    @When("^enters a valid password$")
    public void enters_valid_password() {
        accountPage.enterValidPassword();
    }

    @When("^clicks the submit button$")
    public void clicks_the_sign_in_button() {
        homePage = accountPage.clickSubmit();
    }


    @Then("^navigates to the user settings page$")
    public void navigate_to_the_user_settings_page() {
        accountPage.clickOnUserSetting();
    }

    @Then("^verify the correct user is logged in$")
    public void verify_the_correct_user_is_logged_in() {
        accountPage.verifyCorrectUserLoggedIn();
    }

    @Given("^clicks the need help signin in link$")
    public void clicks_the_need_help_signin_in_link() {
        accountPage.needHelpSigninIn();
    }

    @Then("^enters a valid email address$")
    public void enters_a_valid_email_address() {
        accountPage.inputCorrectEmail();
    }

    @Then("^clicks on the forgotten password link$")
    public void clicks_on_the_forgotten_password_link() {
        accountPage.forgottenPasswordLink();
    }

    @Then("^follows the link from within the received email$")
    public void follows_the_link_from_within_the_received_email() {

        /*
        Due to time constraints and not having access to a test environment I have simply made use of a test email client
        I navigate directly to the inbox using the email address that was earlier generated
        The forgotten password email is then opened and the page source is returned
        The Page source contains the forgotten password link (including the relevant token)
        Given the right set of conditions I would normally get the link using an API call if possible
         */

        driver.get("http://putsbox.com/" + accountPage.userName + "/inspect");

        String receivedEmailUrl = driver.findElement(By.xpath("html/body/div[1]/div[1]/div/table/tbody/tr/td[4]/a[2]")).getAttribute("href");
        driver.get(receivedEmailUrl);

        String pageSource = driver.getPageSource();

        //Would normally look to return a josn and us the jsonPath library to extract the relevant url
        String result = pageSource.substring(pageSource.indexOf("token") - 40, pageSource.indexOf("GB") + 2);
        System.out.println(result);
        /*
        The link does not consistently return the correct page (enter new password page)
        In some cases it takes te user to the login page
        if copied from the console and pasted into a browser it does work.
         */
        driver.get(result);
    }

    @Then("^enters the new password$")
    public void enters_the_new_password() {
        accountPage.inputNewPassword();
    }

}
