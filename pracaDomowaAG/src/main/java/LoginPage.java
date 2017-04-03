import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by kamil on 03.04.17.
 */
public class LoginPage {

    WebDriver driver;
    @FindBy(xpath = "//a[@class='button_link']" )
    WebElement button;

    @FindBy(id = "email")
    WebElement inEmail;

    @FindBy(id = "password")
    WebElement inPassword;

    @FindBy(id = "login")
    WebElement loginIn;


    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage open(){
        driver.get("http://demo.testarena.pl/zaloguj");
        return this;
    }

    public LoginPage setEmail(String email){
        inEmail.sendKeys(email);
        return this;
    }

    public LoginPage setPassword(String password){
        inPassword.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton(){
        loginIn.click();
        return this;
    }

    public  void  clearEmail(){
        inEmail.clear();
    }
}
