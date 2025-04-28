package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CustomerPage {
    WebDriver driver;
    WebDriverWait wait;

    public CustomerPage(WebDriver driver){

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void visit_and_accept_cookies()  {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cc-allow-01"))).click();

    }

    public void click_on_try_app_builder() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Try App Builder']"))).click();

    }

    public void input_credentials(){
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hs-form-iframe-0")));
        driver.switchTo().frame(iframe);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='input']/input[@name='email']"))).sendKeys("grigorescualexandru95@yahoo.ro");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='input']/input[@name='0-2/domain']"))).sendKeys("www.qa-challenge.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='dsgvo-ba0fa3b9-901c-4943-a81f-0a70c7cf9b6b']"))).click();
    }

    public void launchApp(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='actions']/input[@type='submit']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href, 'demo-dashboard.helloagain.com')]"))).click();
    }

    public void saveCustomization() throws InterruptedException {
        List<String> handlesList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(handlesList.get(1));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='footer-wrapper']/button[normalize-space(text())='Continue']"))).click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='footer-wrapper']//button[normalize-space(text())='to Dashboard']"))).click();
    }

    public void accessCustomersList(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li//span[text()='Customers']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href, '#/users')]"))).click();
    }

    public void modifyFirstName(String user){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='user-overview']")));
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='user-overview']//tr"));

        String xpath = "//table//tr[td[normalize-space()='" + user + "']]//td[last()]//a";
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        link.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='box-body user-data-box'] button[class='pull-left btn btn-primary edit-button']"))).click();
        WebElement firstNameInputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("first_name")));

        firstNameInputField.sendKeys(Keys.CONTROL + "a");
        firstNameInputField.sendKeys(Keys.DELETE);
        firstNameInputField.sendKeys("Maximilian");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class, 'update-button')]"))).click();
    }

    public void checkFirstName(String name){
        accessCustomersList();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='user-overview']")));
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='user-overview']//tr"));

        String xpath = "//table//tr[td[normalize-space()='" + name + "']]//td[2]";
        String tableName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).getText();
        assertEquals(name, tableName);
    }

}
