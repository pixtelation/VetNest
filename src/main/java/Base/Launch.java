package Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeClass;

import Pages.Guest.*;
import Pages.Host.*;
import Utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Launch {

    private static WebDriver driver;
    private static boolean isBrowserStarted = false;
    private static boolean isLoggedIn = false;

    // -------------------------------------------------
    // 🔹 STATIC INITIALIZATION
    // -------------------------------------------------
    static {
        // Load config once
        ConfigReader.loadConfig();

        // Setup ChromeDriver (local + CI safe)
        if (System.getProperty("webdriver.chrome.driver") == null) {
            WebDriverManager.chromedriver().setup();
        }
    }

    // -------------------------------------------------
    // 🔹 GETTERS / SETTERS
    // -------------------------------------------------
    public static WebDriver getDriver() {
        return driver;
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void setLoggedIn(boolean value) {
        isLoggedIn = value;
    }

    // -------------------------------------------------
    // 🔹 BROWSER INITIALIZATION (LOCAL + CI)
    // -------------------------------------------------
    @BeforeSuite(alwaysRun = true)
    public void ensureBrowserIsRunning() {

        if (isBrowserStarted) return;

        boolean isCI = System.getenv("CI") != null;

        try {
            ChromeOptions options = new ChromeOptions();

            if (isCI) {
                // 🤖 CI ENVIRONMENT (GitHub Actions / Linux)
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");

                System.out.println("🤖 CI detected → starting HEADLESS Chrome");

            } else {
                // 🧑‍💻 LOCAL MACHINE → attach to persistent browser
                String DEBUG_PORT = ConfigReader.getProperty("port");

                options.setExperimentalOption(
                        "debuggerAddress", "localhost:" + DEBUG_PORT
                );

                System.out.println("🔗 Local run → attaching to persistent Chrome on port " + DEBUG_PORT);
            }

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            isBrowserStarted = true;

        } catch (Exception e) {
            throw new RuntimeException(
                    "❌ Failed to initialize browser. " +
                    (isCI
                            ? "CI environment detected. Chrome failed to start headless."
                            : "Ensure LaunchSessionBrowser is running locally."
                    ),
                    e
            );
        }
    }

    // -------------------------------------------------
    // 🔹 URL NAVIGATION BASED ON PACKAGE
    // -------------------------------------------------
    @BeforeClass(alwaysRun = true)
    public void navigateToCorrectUrl() {

        if (driver == null) {
            ensureBrowserIsRunning();
        }

        String pkg = this.getClass().getPackage().getName();
        String targetUrl;

        if (pkg.contains("HostTest")) {
            targetUrl = ConfigReader.getProperty("HostURL");

        } else if (pkg.contains("GuestTest")) {
            targetUrl = ConfigReader.getProperty("GuestURL");

        } else {
            throw new RuntimeException("❌ No URL configured for package: " + pkg);
        }

        try {
            if (!driver.getCurrentUrl().startsWith(targetUrl)) {
                driver.get(targetUrl);
            }
        } catch (UnreachableBrowserException e) {
            driver.get(targetUrl);
        }

        System.out.println("🌍 Navigated to: " + targetUrl);
    }

    // -------------------------------------------------
    // 🔹 LOGIN HELPERS
    // -------------------------------------------------
    public static void loginAsSuperAdmin() {

        if (isLoggedIn) return;

        HostLogin sa = new HostLogin(driver);
        sa.EnterEmailfx(ConfigReader.getProperty("HostEmail"));
        sa.EnterPasswordfx(ConfigReader.getProperty("HostPass"));
        sa.Loginbtnfx();

        isLoggedIn = true;
        System.out.println("🔑 Logged in as Host");
    }

    public static void loginAsTenentAdmin() {

        if (isLoggedIn) return;

        GuestLogin ta = new GuestLogin(driver);
        ta.EnterEmailfx(ConfigReader.getProperty("GuestEmail"));
        ta.EnterPasswordfx(ConfigReader.getProperty("GuestPass"));
        ta.Submitbtnfx();

        isLoggedIn = true;
        System.out.println("🔑 Logged in as Guest");
    }

    // -------------------------------------------------
    // ❌ NO TEARDOWN (browser stays open locally)
    // -------------------------------------------------
    /*
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    */
}
