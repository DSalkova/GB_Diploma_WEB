package WEB;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

public abstract class AbstractTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String loginUrl;
    private static String postsUrl;
    private static String username1;
    private static String password1;
    private static String username2;
    private static String password2;
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final int time = 20;
    @BeforeAll
    static void initTest() throws IOException {
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        loginUrl = prop.getProperty("loginURL");
        postsUrl = prop.getProperty("postsURL");
        username1 = prop.getProperty("userWithPostsLogin");
        password1 = prop.getProperty("userWithPostsPassword");
        username2 = prop.getProperty("userWithoutPostsLogin");
        password2 = prop.getProperty("userWithoutPostsPassword");
    }
    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(time));
    }
    public static String getLoginUrl() {
        return loginUrl;
    }

    public static String getPostsUrl() {
        return postsUrl;
    }

    public static String getUsername1() {
        return username1;
    }

    public static String getPassword1() {
        return password1;
    }

    public static String getUsername2() {
        return username2;
    }

    public static String getPassword2() {
        return password2;
    }
    @AfterEach
    void shutDown() {
        driver.close();
    }
    public static WebDriver getWebDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }
}