import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

public class Svt {
    WebDriver driver;
    PrintStream ps;

    public static void main(String[] args) {
        Svt svt = new Svt();

        try {
            svt.runSvtQuery();
        } catch (InterruptedException e) {
            e.printStackTrace();
            svt.terminateExecution();
        }

    }

    public void runSvtQuery() throws InterruptedException {
        ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);


        driver = DriverManager.getFirefoxDriver();
        navigateToPage();


        List<WebElement> headlines = getHeadlines();

        printHeadlines(headlines);

        visitRandomHeadline(headlines);

        Thread.sleep(5000);

        terminateExecution();

    }


    private void navigateToPage() {
        driver.get("https://svt.se");
    }

    private List<WebElement> getHeadlines() {

        return driver.findElements(By.xpath("//span[contains('nyh_teaser__heading-title', @class)]"));
    }

    private void printHeadlines(List<WebElement> headlines) {
        for (WebElement we : headlines) {
            ps.println(we.getText());
        }
    }

    private void visitRandomHeadline(List<WebElement> headlines) {
        // Initialize classes
        Random rng = new Random();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Get a random headline
        WebElement headlineToVisit = headlines.get(rng.nextInt(headlines.size()));

        // Scroll to it
        js.executeScript("arguments[0].scrollIntoView(true)", headlineToVisit);
        wait.until(ExpectedConditions.elementToBeClickable(headlineToVisit));

        // Click the headline
        headlineToVisit.click();

    }

    private void terminateExecution() {
        driver.quit();
        ps.close();
    }

}
