package ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.quickbase.utils.WebDriverUtils;
import ui.utils.ApplicationUtils;

public class TwoStepCodePage extends BasePage {
    private final WebDriver driver;
    private final WebDriverUtils driverUtils;

    public TwoStepCodePage(WebDriver driver) {
        this.driver = driver;
        driverUtils = new WebDriverUtils(driver);
        PageFactory.initElements(driver, this);
    }

    private final ApplicationUtils applicationUtils = new ApplicationUtils();
    private final Logger log = LogManager.getLogger(TwoStepCodePage.class);

    @FindBy(id = "TwoStepCode")
    private WebElement twoStepCode;

    public SignInPage enterRealmTwoStepCode() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Getting QB User Real Code");
        String code = applicationUtils.getQBUserRealmCode();
        log.info("Retrieved QB User Real Code {}", code);
        driverUtils.setText(twoStepCode, code);
        log.info("Set Text in Two-Step Code {}", code);
        return createInstance(SignInPage.class,driver);
    }
}
