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
import ui.TestContext;

public class AppSettingsPage extends BasePage {
    public WebDriver driver = TestContext.getDriver();
    WebDriverUtils webDriverUtils = new WebDriverUtils();
    Logger log = LogManager.getLogger(AppSettingsPage.class);

    public AppSettingsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Edit']")
    public WebElement edit;

    @FindBy(xpath = "//input[contains(@class,'settingsAppName')]")
    public WebElement appName;

    @FindBy(xpath = "//textarea[contains(@class,'settingsAppDescription')]")
    public WebElement appDescription;

    @FindBy(xpath = "//button[text()='Save']")
    public WebElement save;

    @FindBy(xpath = "//button[@data-test-id='dialogFinishButton']")
    public WebElement saveButtonEditBox;

    @FindBy(xpath = "//input[@data-test-id = 'customColorPickerInputField']")
    public WebElement chaneColorCode;

    @FindBy(id = "newTableMenuButtonAppHome")
    public WebElement newTable;

    @FindBy(id = "btnNewTableAppHome")
    public WebElement fromScratchButton;

    public WebElement settingOptionsAndFieldValues(String option) {
        return driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]", option)));
    }

    public WebElement tableName(String tableName) {
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
        return createInstance(pageClass, driver);
    }

    public <T> T clickOnSaveButtonEditBox(Class<T> pageClass) {
        webDriverUtils.click(saveButtonEditBox);
        log.info("Clicked on Save Button on EditBox");
        return createInstance(pageClass, driver);
    }

    public void validateColorCodeIsChanged(String code) {
        log.info("Validating & Asserting Color Code {}", code);
        if (!(webDriverUtils.getValueAttribute(chaneColorCode).equalsIgnoreCase(code))) {
            log.error("Color Code Mismatched");
            Assert.fail("Color Code Mismatch");
        }
    }

    public <T> T clickOnSettingOptionOrFieldValue(String option, Class<T> pageClass) {
        log.info("Scrolling to {} and Clicked on {}", option);
        webDriverUtils.scrollToElementAndClick(settingOptionsAndFieldValues(option), driver);
        return createInstance(pageClass, driver);
    }

    public AppSettingsPage clickOnTableName(String tableName) {
        log.info("Clicked on Table Name");
        webDriverUtils.scrollToElementAndClick(tableName(tableName), driver);
        return this;
    }

    public AppSettingsPage clickOnNewTable() {
        log.info("Clicked on New Table Button");
        webDriverUtils.scrollToElementAndClick(newTable, driver);
        return this;
    }

    public TablesPage clickOnFromScratchButton() {
        log.info("Clicked on From Scratch Button");
        webDriverUtils.click(fromScratchButton);
        return createInstance(TablesPage.class, driver);
    }
}
