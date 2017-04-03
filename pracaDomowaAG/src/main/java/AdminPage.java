import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by kamil on 03.04.17.
 */
public class AdminPage {
    WebDriver driver;

    @FindBy(xpath = "//a[@href='http://demo.testarena.pl/administration/add_project']")
    WebElement clicAddNewProject;

    @FindBy(xpath = "//a[@href='http://demo.testarena.pl/administration/projects']")
    WebElement clicMenuProjects;

    @FindBy(id = "search")
    WebElement inSearch;

    @FindBy(id = "j_searchButton")
    WebElement clicSearch;

    @FindBy(id = "save")
    WebElement clicSave;

    @FindBy(id = "name")
    WebElement inNameProject;

    @FindBy(id = "prefix")
    WebElement inPrefix;



    public AdminPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public  AdminPage clicAddNewProjectButton(){
        clicAddNewProject.click();
        return this;
    }

    public AdminPage clicMenuProjectsButton(){
        clicMenuProjects.click();
        return this;
    }

    public AdminPage setNameProject(String nameProject){
        inNameProject.sendKeys(nameProject);
        return this;
    }

    public AdminPage setInPrefix(String prefix){
       inPrefix.sendKeys(prefix);
        return this;
    }

    public AdminPage setSearch(String nameProject){
        inSearch.sendKeys(nameProject);
        return this;
    }

    public AdminPage clicSearchButton(){
        clicSearch.click();
        return this;
    }

    public AdminPage clicSaveButton(){
        clicSave.click();
        return this;
    }
}
