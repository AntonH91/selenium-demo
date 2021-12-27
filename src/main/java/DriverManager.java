import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    public static FirefoxDriver getFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");

        return new FirefoxDriver();
    }
}
