package ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.quickbase.utils.WebDriverUtils;
import org.testng.Assert;

public class AppSettingsPage extends BasePage {
    private final WebDriver driver;
    private final WebDriverUtils webDriverUtils;
    private final Logger log = LogManager.getLogger(AppSettingsPage.class);

    public AppSettingsPage(WebDriver driver) {
        this.driver = driver;
        webDriverUtils = new WebDriverUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Edit']")
    private WebElement edit;

    @FindBy(xpath = "//input[contains(@class,'settingsAppName')]")
    private WebElement appName;

    @FindBy(xpath = "//textarea[contains(@class,'settingsAppDescription')]")
    private WebElement appDescription;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement save;

    @FindBy(xpath = "//button[@data-test-id='dialogFinishButton']")
    private WebElement saveButtonEditBox;

    @FindBy(xpath = "//input[@data-test-id = 'customColorPickerInputField']")
    private WebElement chaneColorCode;

    @FindBy(id = "newTableMenuButtonAppHome")
    private WebElement newTable;

    @FindBy(id = "btnNewTableAppHome")
    private WebElement fromScratchButton;

    private WebElement settingOptionsAndFieldValues(String option) {
        return driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]", option)));
    }

    private WebElement tableName(String tableName) {
        return driver.findElement(By.xpath(String.format
                ("//table[@id='appTablesListTable']//a[text() = '%s']", tableName)));
    }

    public AppSettingsPage clickOnEdit() {
        webDriverUtils.click(edit);
        log.info("Clicked on Edit Button");
        return this;
    }

    public AppSettingsPage changeAppName(String app) {
        webDriverUtils.setText(appName, app);
        log.info("Set Text for AppName {}", app);
        return this;
    }

    public AppSettingsPage changeAppDescription(String desc) {
        webDriverUtils.setText(appDescription, desc);
        log.info("Set Text for AppDescription {}", desc);
        return this;
    }

    public AppSettingsPage changeColorCode(String code) {
        webDriverUtils.setText(chaneColorCode, code);
        log.info("Set Text for App Color code {}", code);
        return this;
    }

    public <T> T clickOnSaveButton(Class<T> pageClass) {
        webDriverUtils.click(save);
        log.info("Clicked on Save Button");
        return createInstance(pageClass,driver);
    }

    public <T> T clickOnSaveButtonEditBox(Class<T> pageClass) {
        webDriverUtils.click(saveButtonEditBox);
        log.info("Clicked on Save Button on EditBox");
        return createInstance(pageClass,driver);
    }

    public void validateColorCodeIsChanged(String code) {
        log.info("Validating & Asserting Color Code {}", code);
        if (!(webDriverUtils.getValueAttribute(chaneColorCode).equalsIgnoreCase(code))) {
            log.error("Color Code Mismatched");
            Assert.fail("Color Code Mismatch");
        }
    }

    public <T> T clickOnSettingOptionOrFieldValue(String option, Class<T> pageClass) {
        webDriverUtils.scrollToElementAndClick(settingOptionsAndFieldValues(option), driver);
        log.info("Scrolling and Clicked on {}", option);
        return createInstance(pageClass,driver);
    }

    public <T> T clickOnTableName(String tableName,Class<T> pageClass) {
        webDriverUtils.scrollToElementAndClick(tableName(tableName), driver);
        log.info("Clicked on Table Name");
        return createInstance(pageClass,driver);
    }

    public AppSettingsPage clickOnNewTable() {
        webDriverUtils.scrollToElementAndClick(newTable, driver);
        log.info("Clicked on New Table Button");
        return this;
    }

    public TablesPage clickOnFromScratchButton() {
        webDriverUtils.click(fromScratchButton);
        log.info("Clicked on From Scratch Button");
        return createInstance(TablesPage.class,driver);
    }
}
