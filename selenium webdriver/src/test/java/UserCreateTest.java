import org.apache.commons.lang3.RandomStringUtils;
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
 * Created by pawel on 12.03.2017.
 */
public class UserCreateTest {
    WebDriver driver;
    WebDriverWait wait;

    public String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(10);
    }

    @Before
    public void beforTest() {
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 20);

        driver.get("http://demo.testarena.pl");
        driver.findElement(By.id("email")).sendKeys("administrator@testarena.pl");
        driver.findElement(By.id("password")).sendKeys("sumXQQ72$L");
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='header_logout']/a")));
    }
    @After
    public void afterTest () {
    driver.close();
    }

    @Test
    public void shouldCreateUserCorrectly(){
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='header_admin']/a")));
        driver.findElement(By.xpath("//div[@class='header_admin']/a")).click();
        driver.findElement(By.xpath("//a[@href='http://demo.testarena.pl/administration/users']")).click();
        driver.findElement(By.xpath("//a[@href='http://demo.testarena.pl/administration/add_user']")).click();
        driver.findElement(By.id("firstname")).sendKeys(generateRandomString(8));
        driver.findElement(By.id("lastname")).sendKeys(generateRandomString(8));
        driver.findElement(By.id("email")).sendKeys(generateRandomString(6) + "@wp.pl" );
        driver.findElement(By.id("save")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='j_info_box']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='j_info_box']")).isDisplayed());

    }

}
