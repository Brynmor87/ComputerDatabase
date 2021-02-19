package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private static WebElement element = null;

    public static WebElement computer_found(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/h1"));
        return element;
    }

    public static WebElement filter_text(WebDriver driver) {
        element = driver.findElement(By.id("searchbox"));
        return element;
    }

    public static WebElement filter_button(WebDriver driver) {
        element = driver.findElement(By.id("searchsubmit"));
        return element;
    }

    public static WebElement add_button(WebDriver driver) {
        element = driver.findElement(By.id("add"));
        return element;
    }

    public static WebElement alert_message(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/div[1]"));
        return element;
    }

    public static WebElement compName_row1(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/table/tbody/tr[1]/td[1]"));
        return element;
    }

    public static WebElement compName_button(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/table/tbody/tr[1]/td[1]/a"));
        return element;
    }//*[@id='main']/table/tbody/tr[1]/td[1]/a

    public static WebElement intDate_row1(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/table/tbody/tr[1]/td[2]"));
        return element;
    }

    public static WebElement disDate_row1(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/table/tbody/tr[1]/td[3]"));
        return element;
    }

    public static WebElement company_row1(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/table/tbody/tr[1]/td[4]"));
        return element;
    }

    public static WebElement empty_row(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='main']/div[2]"));
        return element;
    }

}
