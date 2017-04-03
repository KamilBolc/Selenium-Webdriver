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
import java.util.Random;

/**
 * Created by student on 12.03.2017.
 */
public class NewAddUserTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void before(){
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
//        driver = new ChromeDriver();                // zakomentowane z powodu pracy na Firefox
        driver.get("http://demo.testarena.pl/zaloguj");
        driver.findElement(By.id("email")).sendKeys("administrator@testarena.pl");
        driver.findElement(By.id("password")).sendKeys("sumXQQ72$L");
        driver.findElement(By.id("login")).click();
    }

    @Test
    public void shouldLoginCorrectly() throws IOException{
        Properties obj = getProperties();
        int numerEmail = randomNumber();
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
        driver.findElement(By.id("email")).sendKeys(numerEmail + (obj.getProperty("Email")));
        driver.findElement(By.id("department")).sendKeys(obj.getProperty("Departament"));
        driver.findElement(By.id("phoneNumber")).sendKeys(obj.getProperty("Numberphone"));
        driver.findElement(By.name("save")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='j_info_box']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='j_info_box']")).isDisplayed());
    }

    @After
    public void after(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='action_icon']")));
        driver.findElement(By.xpath("//a[@id='action_icon']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Dezaktywuj')]")));
        driver.findElement(By.xpath("//a[contains(text(),'Dezaktywuj')]")).click();
        driver.quit();
    }

    private Properties getProperties() throws IOException {
        Properties obj = new Properties();
        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/user_properties.properties");
        obj.load(objfile);
        return obj;
    }

    private int randomNumber() {
        Random r = new Random();
        return r.nextInt(1001);
    }
}
