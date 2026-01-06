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

import java.util.List;
import java.util.Random;

public class TablesPage extends BasePage {

    private final WebDriver driver = TestContext.getDriver();
    private final WebDriverUtils webDriverUtils = new WebDriverUtils();
    private final Logger log = LogManager.getLogger(TablesPage.class);

    public TablesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "newFieldsButton")
    private WebElement newFieldButton;

    @FindBy(xpath = "//div[@class = 'fieldNameInput__input']//input")
    private WebElement newFieldInput;

    @FindBy(xpath = "//button[@data-test-id='dialogOkButton']")
    private WebElement addFieldOrCreateTableButton;

    @FindBy(id = "fieldsTableSearch")
    private WebElement searchField;

    @FindBy(id = "tablesSearch")
    private WebElement tableSearchField;

    @FindBy(xpath = "//div[@class = 'itemPickerV2__input']/input")
    private WebElement tableName;

    @FindBy(xpath = "//button[@data-test-id= 'selectedIconTrigger']")
    private WebElement icon;

    @FindBy(xpath = "//div[contains(@class,'suggestedIconContainer')]" +
            "//div[@class='TableIconPadding']//div")
    private List<WebElement> suggestedIconOnCopyTable;

    @FindBy(xpath = "(//div[@data-test-id= 'icon-picker'])[1]//button")
    private List<WebElement> iconList;

    @FindBy(xpath = "//input[@data-test-id= 'SingleRecordInput']")
    private WebElement singleRecord;

    @FindBy(id = "settingsJumpMenuButton")
    private WebElement jumpMenuButton;

    @FindBy(id = "settingsMenu_list_app")
    private WebElement settingsApp;

    private String searchForTable(String tableName){
        return String.format("//table[@id='appTablesListTable']//a[text() = '%s']",tableName);
    }

    private WebElement deleteBoxForTable(String tableName){
        String xpath = String.format("//table[@id='appTablesListTable']//a[text() = '%s']" +
                "/../following-sibling::" +
                "td[@aria-describedby='appTablesListTable_actions']//a[@class='RowAction Delete Icon']",tableName);
        return driver.findElement(By.xpath(xpath));
    }

    private WebElement copyForTable(String tableName){
        String xpath = String.format("//table[@id='appTablesListTable']//a[text() = '%s']" +
                "/../following-sibling::" +
                "td[@aria-describedby='appTablesListTable_actions']//a[@class='RowAction Copy Icon']",tableName);
        return driver.findElement(By.xpath(xpath));
    }

    @FindBy(name = "createNewTableName")
    private WebElement newTableNameCopy;

    @FindBy(name = "createNewTableNoun")
    private WebElement copyTableSingleRecord;

    @FindBy(xpath = "//button[text()='Delete Table']")
    private WebElement deleteTableButton;

    @FindBy(id = "typeYesField")
    private WebElement yesOnDeleteBox;

    @FindBy(xpath = "//button[text()='Copy']")
    private WebElement copyButton;

    public TablesPage clickOnNewFieldButton() {
        webDriverUtils.click(newFieldButton);
        log.info("Clicked on New Field Button");
        return this;
    }

    public TablesPage enterNewField(String newField) {
        webDriverUtils.setText(newFieldInput, newField);
        log.info("Set Text on New Field Button {}", newField);
        return this;
    }

    public TablesPage clickOnAddFieldOrCreateTableButton() {
        webDriverUtils.click(addFieldOrCreateTableButton);
        log.info("Clicked on Create Table Button");
        return this;
    }

    public <T> T searchNewFieldValue(String value, Class<T> pageClass) {
        log.info("Scrolled and set Text {} ", value);
        webDriverUtils.scrollToElementAndSetText(searchField, driver, value);
        return createInstance(pageClass);
    }

    public TablesPage enterTableName(String tabName) {
        webDriverUtils.setText(tableName, tabName);
        log.info("Set Text in TableName {}", tabName);
        return this;
    }

    public TablesPage enterCopyTableName(String tabName) {
        webDriverUtils.setText(newTableNameCopy, tabName);
        log.info("Set Text in Copy TableName {}", tabName);
        return this;
    }

    public TablesPage selectIcon() {
        webDriverUtils.click(icon);
        webDriverUtils.waitForElement(1000);
        List<WebElement> icons = iconList;
        Random randomIcon = new Random();
        webDriverUtils.click(icons.get(randomIcon.nextInt(14)));
        log.info("Clicked on Random Icon");
        return this;
    }

    public TablesPage selectIconOnSuggestedTray() {
        webDriverUtils.waitForElement(1000);
        List<WebElement> icons = suggestedIconOnCopyTable;
        Random randomIcon = new Random();
        webDriverUtils.click(icons.get(randomIcon.nextInt(8)));
        log.info("Clicked on Random Suggested Icon");
        return this;
    }

    public TablesPage enterSingleRecord(String customSingleRecord) {
        webDriverUtils.setText(singleRecord, customSingleRecord);
        log.info("Set text on Single Record {}", customSingleRecord);
        return this;
    }

    public TablesPage enterSingleRecordOnCopyTable(String customSingleRecord) {
        webDriverUtils.setText(copyTableSingleRecord, customSingleRecord);
        log.info("Set text on record Copy Table {}", customSingleRecord);
        return this;
    }

    public TablesPage clickOnJumpSettingsMenu() {
        webDriverUtils.click(jumpMenuButton);
        log.info("Clicked on Jump Settings Menu");
        return this;
    }

    public AppSettingsPage clickOnAppSettingsMenuItem() {
        webDriverUtils.click(settingsApp);
        log.info("Clicked on App Settings Menu");
        return createInstance(AppSettingsPage.class);
    }

    public TablesPage clickOnDeleteBoxForTableName(String tableName) {
        webDriverUtils.scrollToElementAndClick(deleteBoxForTable(tableName),driver);
        log.info("Clicked on Delete Icon on {} table." , tableName);
        return this;
    }

    public HomePage clickOnCopyButton() {
        webDriverUtils.scrollToElementAndClick(copyButton,driver);
        log.info("Clicked on Copy Button.");
        webDriverUtils.waitForElement(5000);
        return createInstance(HomePage.class);
    }

    public TablesPage clickOnCopyIconTableName(String tableName) {
        webDriverUtils.waitForElement(500).scrollToElementAndClick(copyForTable(tableName),driver);
        log.info("Clicked on Copy Icon on {} table." , tableName);
        return this;
    }

    public TablesPage enterYesOnDeleteBox() {
        webDriverUtils.setText(yesOnDeleteBox, "YES");
        log.info("Set text on Delete Box {}", "YES");
        return this;
    }

    public TablesPage clickOnDeleteTableButton() {
        webDriverUtils.click(deleteTableButton);
        log.info("Clicked on Delete Table button.");
        return this;
    }

    public void validateTableIsDeleted(String tableName){
        log.info("Validating Table is Deleted or not");
        if(webDriverUtils.isElementPresent(searchForTable(tableName))){
            Assert.fail("Table is not deleted.");
        }
    }

}
