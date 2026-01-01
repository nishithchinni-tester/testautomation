package ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.quickbase.utils.WebDriverUtils;
import ui.TestContext;
import java.util.List;
import java.util.Random;

public class TablesPage extends BasePage {

    public WebDriver driver = TestContext.getDriver();
    WebDriverUtils webDriverUtils = new WebDriverUtils();
    Logger log = LogManager.getLogger(TablesPage.class);
    public TablesPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "newFieldsButton")
    public WebElement newFieldButton;

    @FindBy(xpath = "//div[@class = 'fieldNameInput__input']//input")
    public WebElement newFieldInput;

    @FindBy(xpath = "//button[@data-test-id='dialogOkButton']")
    public WebElement addFieldOrCreateTableButton;

    @FindBy(id = "fieldsTableSearch")
    public WebElement searchField;

    @FindBy(xpath = "//div[@class = 'itemPickerV2__input']/input")
    public WebElement tableName;

    @FindBy(xpath = "//button[@data-test-id= 'selectedIconTrigger']")
    public WebElement icon;

    @FindBy(xpath = "(//div[@data-test-id= 'icon-picker'])[1]//button")
    public List<WebElement> iconList;

    @FindBy(xpath = "//input[@data-test-id= 'SingleRecordInput']")
    public WebElement singleRecord;

    @FindBy(id = "settingsJumpMenuButton")
    public WebElement jumpMenuButton;

    @FindBy(id = "settingsMenu_list_app")
    public WebElement settingsApp;

    public TablesPage clickOnNewFieldButton(){
        webDriverUtils.click(newFieldButton);
        log.info("Clicked on New Field Button");
        return this;
    }
    public TablesPage enterNewField(String newField){
        webDriverUtils.setText(newFieldInput,newField);
        log.info("Set Text on New Field Button {}",newField);
        return this;
    }
    public TablesPage clickOnAddFieldOrCreateTableButton(){
        webDriverUtils.click(addFieldOrCreateTableButton);
        log.info("Clicked on Create Table Button");
        return this;
    }
    public <T> T searchNewFieldValue(String value,Class<T> pageClass){
        log.info("Scrolled and set Text {} ", value);
        webDriverUtils.scrollToElementAndSetText(searchField,driver,value);
        return createInstance(pageClass,driver);
    }
    public TablesPage enterTableName(String tabName){
        webDriverUtils.setText(tableName,tabName);
        log.info("Set Text in TableName {}", tabName);
        return this;
    }
    public TablesPage selectIcon(){
        webDriverUtils.click(icon);
        webDriverUtils.waitForElement(1000);
        List<WebElement> icons = iconList;
        Random randomIcon = new Random();
        webDriverUtils.click(icons.get(randomIcon.nextInt(14)));
        log.info("Clicked on Random Icon");
        return this;
    }
    public TablesPage enterSingleRecord(String customSingleRecord){
        webDriverUtils.setText(singleRecord,customSingleRecord);
        log.info("Set text on Single Record {}", customSingleRecord);
        return this;
    }
    public TablesPage clickOnJumpSettingsMenu(){
        webDriverUtils.click(jumpMenuButton);
        log.info("Clicked on Jump Settings Menu");
        return this;
    }
    public AppSettingsPage clickOnAppSettingsMenuItem(){
        webDriverUtils.click(settingsApp);
        log.info("Clicked on App Settings Menu");
        return createInstance(AppSettingsPage.class,driver);
    }

}
