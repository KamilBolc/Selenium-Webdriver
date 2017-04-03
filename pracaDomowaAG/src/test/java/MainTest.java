import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import java.util.Random;

/**
 * Created by kamil on 03.04.17.
 */
public class MainTest {
    WebDriver driver = new ChromeDriver();
    LoginPage loginPage = new LoginPage(driver);

    Random r = new Random();
    int nr = r.nextInt(120);

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
