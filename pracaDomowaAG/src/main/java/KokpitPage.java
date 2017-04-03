import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by kamil on 03.04.17.
 */
public class KokpitPage {
    WebDriver driver;

    @FindBy(xpath = "//a[@href='http://demo.testarena.pl/administration']")
    WebElement clicAdmin;

    public KokpitPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public  void clicAdminButton(){
        clicAdmin.click();
    }

}
