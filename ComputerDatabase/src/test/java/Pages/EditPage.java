package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditPage {
    private static WebElement element = null;

    public static WebElement save_button(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/form[1]/div/input"));
        return element;
    }

    public static WebElement cancel_button(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/form[1]/div/a"));
        return element;
    }

    public static WebElement delete_button(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/form[2]/input"));
        return element;
    }

    public static WebElement compName_text(WebDriver driver) {
        element = driver.findElement(By.id("name"));
        return element;
    }

    public static WebElement introduced_date(WebDriver driver) {
        element = driver.findElement(By.id("introduced"));
        return element;
    }

    public static WebElement discontinued_date(WebDriver driver) {
        element = driver.findElement(By.id("discontinued"));
        return element;
    }

    public static WebElement company_dropdown(WebDriver driver) {
        element = driver.findElement(By.id("company"));
        return element;
    }

    public static WebElement compName_container(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/form[1]/fieldset/div[1]"));
        return element;
    }

    public static WebElement intDate_container(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/form[1]/fieldset/div[2]"));
        return element;
    }

    public static WebElement disDate_container(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/form[1]/fieldset/div[3]"));
        return element;
    }
}
