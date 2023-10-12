package testBasico;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


public class LoginTest {

    private String id = "4130162";
    ChromeDriver chrome;
    @BeforeEach
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chrome/chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        chrome.get("http://todo.ly/");
    }

    @Test
    public void verifySomeThing() throws InterruptedException {
        chrome.findElement(By.xpath("//img[@src=\"/Images/design/pagelogin.png\"]")).click();
        // set email
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("lutyvr02@gmail.com");
        // set password
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("hola1234");
        // click login
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        // verificar si existe el control del logout
        chrome.findElement(By.className("AddProjectLiDiv")).click();
        Thread.sleep(1000);
        chrome.findElement(By.className("InputTextAddProj")).sendKeys("hola1234");
        Thread.sleep(1000);
        chrome.findElement(By.id("NewProjNameButton")).click();
        Thread.sleep(1000);
        chrome.findElement(By.id("ItemId_"+id)).click();
        Thread.sleep(1000);
        WebElement liElement =chrome.findElement(By.cssSelector("li.BaseProjectLi.acceptProject.acceptBoth[id='ItemId_"+id+"']"));
        WebElement tdElement = liElement.findElement(By.cssSelector("td.ItemIndicator"));
        tdElement.click();
        Thread.sleep(1000);
        WebElement contextMenu = chrome.findElement(By.id("projectContextMenu"));
        WebElement editOption = contextMenu.findElement(By.cssSelector("li.edit a"));
        Thread.sleep(1000);
        editOption.click();
        Thread.sleep(1000);
        WebElement editTextbox = chrome.findElement(By.id("ItemEditTextbox"));
        editTextbox.clear();
        editTextbox.sendKeys("Luciano");
        Thread.sleep(1000);
        WebElement saveButton = chrome.findElement(By.id("ItemEditSubmit"));
        saveButton.click();
        Thread.sleep(5000);


    }

    @AfterEach
    public void closeBrowser(){
        chrome.quit();
    }
}
