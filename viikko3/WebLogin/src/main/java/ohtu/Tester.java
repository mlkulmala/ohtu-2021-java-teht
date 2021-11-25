package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");

        System.out.println(driver.getPageSource());
        
        //sleep(2);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        System.out.println(driver.getPageSource());

        //sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pappa");
        element = driver.findElement(By.name("password"));
        element.sendKeys("pappa1rainen");
        element = driver.findElement(By.name("password"));
        element.sendKeys("pappa1rainen");
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();

        sleep(3);
        WebElement element2 = driver.findElement(By.linkText("continue to application mainpage"));
        element2.click();

        sleep(4);
        WebElement element3 = driver.findElement(By.linkText("logout"));
        element3.click();
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
