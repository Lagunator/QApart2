package tareaChrome;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class actualizarCrear {
    private ChromeDriver chrome;

    @BeforeEach
    public void setUp() {
        initializeWebDriver();
        loginToTodoLy("lagu@gmail.com", "12345678");
    }

    @Test
    public void verifyCreateProjectTest() {
        List<WebElement> projectsBefore = getProjectsByName("lago");
        createProject("lagugu");
        List<WebElement> projectsAfter = getProjectsByName("laguuuu");
        Assertions.assertFalse(projectsBefore.size() < projectsAfter.size(), "ERROR: No es posible crearlo");
    }

    @Test
    public void verifyEditProjectTest() {
        List<WebElement> projectsBefore = getProjectsByName("nuevo laguu");
        editLastProject("lagugu");
        List<WebElement> projectsAfter = getProjectsByName("edit laguna");
        Assertions.assertFalse(projectsBefore.size() > projectsAfter.size(), "ERROR: No es posible");
    }

    @AfterEach
    public void tearDown() {
        closeBrowser();
    }

    private void initializeWebDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chrome/chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        chrome.get("http://todo.ly/");
    }

    private void loginToTodoLy(String email, String password) {
        chrome.findElement(By.xpath("//img[@src=\"/Images/design/pagelogin.png\"]")).click();
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys(email);
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys(password);
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
    }

    private void createProject(String projectName) {
        chrome.findElement(By.xpath("//div[@class=\"AddProjectLiDiv\"]")).click();
        chrome.findElement(By.id("NewProjNameInput")).sendKeys(projectName);
        chrome.findElement(By.id("NewProjNameButton")).click();
    }

    private List<WebElement> getProjectsByName(String projectName) {
        return chrome.findElements(By.xpath(String.format("//td[text()='%s']", projectName)));
    }

    private void editLastProject(String projectName) {
        List<WebElement> projects = getProjectsByName(projectName);
        WebElement lastProject = projects.get(projects.size() - 1);
        lastProject.click();
        lastProject.findElement(By.xpath(".//div[@class='ProjItemMenu']/img")).click();
        chrome.findElement(By.xpath("//ul[@id=\"projectContextMenu\"]/li[@class='edit']")).click();
        chrome.findElement(By.id("ItemEditTextbox")).sendKeys(" edit");
        chrome.findElement(By.id("ItemEditSubmit")).click();
    }

    private void closeBrowser() {
        chrome.quit();
    }
}
