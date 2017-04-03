import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by kamil on 03.04.17.
 */
public class SearchNewProject extends MainTest {
    KokpitPage kokpitPage = new KokpitPage(driver);
    AdminPage adminPage = new AdminPage(driver);
    private String nameProject = "KamilBolc"+nr;

    @BeforeMethod
    public void setUp(){

        loginPage.open()
                .setEmail("administrator@testarena.pl")
                .setPassword("sumXQQ72$L")
                .clickLoginButton();
        kokpitPage.clicAdminButton();
        adminPage.clicAddNewProjectButton()
                .setNameProject(nameProject)
                .setInPrefix("KB"+nr)
                .clicSaveButton();
    }


    @Test
    public  void shouldSearchNewProject(){
        adminPage.clicMenuProjectsButton()
                .setSearch(nameProject)
                .clicSearchButton();
        String nameText = driver.findElement(By.xpath("//a[contains(text(),'"+nameProject+"')]")).getText();
        Assert.assertTrue(nameText.contains(nameProject), "Text not faund!!");


    }


}