import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by kamil on 13.03.17.
 */
public class LoginNewUserTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void before()throws IOException{
        Properties obj = getProperties();
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 45);
//        driver = new ChromeDriver();              // zakomentowane z powodu pracy na Firefox
//        wait = new WebDriverWait(driver, 10);
        driver.get("https://10minutemail.com");
        String GenerateEmail = driver.findElement(By.id("mailAddress")).getAttribute("value");
        driver.get("http://demo.testarena.pl/zaloguj");
        driver.findElement(By.id("email")).sendKeys("administrator@testarena.pl");
        driver.findElement(By.id("password")).sendKeys("sumXQQ72$L");
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/a[@href='http://demo.testarena.pl/administration']")));
        driver.findElement(By.xpath("//div/a[@href='http://demo.testarena.pl/administration']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://demo.testarena.pl/administration/users']")));
        driver.findElement(By.xpath("//a[@href='http://demo.testarena.pl/administration/users']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://demo.testarena.pl/administration/add_user']")));
        driver.findElement(By.xpath("//a[@href='http://demo.testarena.pl/administration/add_user']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("firstname")));
        driver.findElement(By.id("firstname")).sendKeys(obj.getProperty("Name"));
        driver.findElement(By.id("lastname")).sendKeys(obj.getProperty("Lastname"));
        driver.findElement(By.id("organization")).sendKeys(obj.getProperty("Organization"));
        driver.findElement(By.id("email")).sendKeys(GenerateEmail);
        driver.findElement(By.id("department")).sendKeys(obj.getProperty("Departament"));
        driver.findElement(By.id("phoneNumber")).sendKeys(obj.getProperty("Numberphone"));
        driver.findElement(By.name("save")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='j_info_box']")));
    }
    @Test
    public void shouldLoginNewUserCorrectly() throws IOException, InterruptedException {
        Properties obj = getProperties();
        driver.get("https://10minutemail.com");
        refreshPageAndWaitForEmail();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/h3/span[@class='inc-mail-address']")));
        driver.findElement(By.xpath("//div/h3/span[@class='inc-mail-address']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[3]/a")));
        driver.findElement(By.xpath("//p[3]/a")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(obj.getProperty("NewUserPassword"));
        driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys(obj.getProperty("NewUserPassword"));
        driver.findElement(By.xpath("//input[@id='new']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='j_info_box']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='j_info_box']")).isDisplayed());
        }

    @After
    public void after(){
        driver.findElement(By.xpath("//div/a[@href='http://demo.testarena.pl/wyloguj']")).click();
        driver.quit();
    }

    private Properties getProperties() throws IOException {
        Properties obj = new Properties();
        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/user_properties.properties");
        obj.load(objfile);
        return obj;
    }

    private void refreshPageAndWaitForEmail() throws InterruptedException {
        for (int i = 0; i<10; i++)
        {
            if (driver.findElements(By.xpath("//div/h3/span[@class='inc-mail-address']")).isEmpty()) {
                driver.navigate().refresh();
                Thread.sleep(1500);
            }

        }
    }
}
