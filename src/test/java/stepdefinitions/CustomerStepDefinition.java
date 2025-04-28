package stepdefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import hooks.Hooks;
import pages.CustomerPage;

public class CustomerStepDefinition {
    WebDriver driver = Hooks.driver;
    CustomerPage customerPage = new CustomerPage(driver);

    @Given("user visits the website and accept the cookies")
    public void user_visits_the_website() throws InterruptedException {
        driver.get("https://www.helloagain.com/en");
        customerPage.visit_and_accept_cookies();
    }

    @And("user clicks on Try App Builder")
    public void user_accepts_cookies(){
        customerPage.click_on_try_app_builder();
    }

    @When("user input the credentials")
    public void user_input_credentials(){
        customerPage.input_credentials();
    }

    @And("launch the app builder")
    public void user_launch_app_builder(){
        customerPage.launchApp();
    }

    @And("saves all customizations")
    public void user_saves_customizations() throws InterruptedException {
        customerPage.saveCustomization();
    }

    @Then("user is accessing the customers list")
    public void user_access_customers_list(){
        customerPage.accessCustomersList();
    }

    @And("modifies the first name of {string}")
    public void modify_first_name(String user){
        customerPage.modifyFirstName(user);
    }

    @Then("first name has been successfully modified to {string}")
    public void check_if_first_name_has_been_modified(String name){
        customerPage.checkFirstName(name);
    }
}
