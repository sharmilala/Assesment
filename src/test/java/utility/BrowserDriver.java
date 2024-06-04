package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserDriver {
    public static WebDriver driver;

    public BrowserDriver(){
        this.driver = driver;
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nithya\\IdeaProjects\\AssessmentCognizant\\src\\test\\resources\\drivers\\chromedriver.exe");
        this.driver = new ChromeDriver();

//        public void close(){
//            this.driver.close();
//        }
    }
}
