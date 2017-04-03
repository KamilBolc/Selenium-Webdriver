import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by student on 12.03.2017.
 */
public class LoginTest {
    protected WebDriver  driver;
    protected WebDriverWait  wait;


    @Before
    public void before(){
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
//      driver = new ChromeDriver();              // zakomentowane z powodu pracy na Firefox
//      wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void shouldLoginCorrectly(){
        driver.get("http://demo.testarena.pl/zaloguj");
        driver.findElement(By.id("email")).sendKeys("administrator@testarena.pl");
        driver.findElement(By.id("password")).sendKeys("sumXQQ72$L");
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/a[@href='http://demo.testarena.pl/wyloguj']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div/a[@href='http://demo.testarena.pl/wyloguj']")).isDisplayed());
    }

    @After
    public void after(){
        driver.quit();
    }

}
