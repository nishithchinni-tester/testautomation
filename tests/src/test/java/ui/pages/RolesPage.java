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

public class RolesPage extends BasePage{
    public WebDriver driver = TestContext.getDriver();
    WebDriverUtils webDriverUtils = new WebDriverUtils();
    Logger log = LogManager.getLogger(RolesPage.class);
    public RolesPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "newRoleButton")
    public WebElement newRole;

    @FindBy(id = "addRoleName")
    public WebElement roleName;

    @FindBy(id = "addRoleDesc")
    public WebElement roleDescription;

    @FindBy(xpath = "//button[text()='OK']")
    public WebElement okButton;

    @FindBy(xpath = "//a[contains(@href,'Properties')]")
    public WebElement properties;

    @FindBy(id = "saveButton")
    public WebElement save;

    @FindBy(xpath = "//a[text()='Roles']")
    public WebElement roles;

    public WebElement clickOnCheckBox(String role){
        String xpath = String.
                format("//a[text()='%s']/../../preceding-sibling::td[2]",role);
        return driver.findElement(By.xpath(xpath));
    }

    @FindBy(id = "defaultRoleButton")
    public WebElement setAsDefault;

    @FindBy(xpath = "(//span[@class = 'DefaultRole Icon'])[2]/preceding-sibling::a")
    public WebElement defaultText;

    @FindBy(xpath = "//span[@id='roleDeleteSpan']/button")
    public WebElement deleteButton;

    @FindBy(xpath = "(//button[text()='Delete'])[2]")
    public WebElement confirmDeleteButton;

    public RolesPage clickOnNewRole(){
        webDriverUtils.waitForElement(500).click(newRole);
        log.info("Clicked on New Role");
        return this;
    }
    public RolesPage enterRoleName(String role){
        webDriverUtils.setText(roleName,role);
        log.info("Set Text in Role Name {}", role);
        return this;
    }
    public RolesPage enterRoleDescription(String role){
        webDriverUtils.setText(roleDescription,role);
        log.info("Set Text in Role Description {}", role);
        return this;
    }
    public RolesPage clickOnOKButton(){
        webDriverUtils.click(okButton);
        log.info("Clicked on OK Button");
        return this;
    }
    public RolesPage clickOnProperties(){
        webDriverUtils.click(properties);
        log.info("Clicked on Properties Button");
        return this;
    }
    public RolesPage clickOnSaveButton(){
        webDriverUtils.click(save);
        log.info("Clicked on Save Button");
        return this;
    }
    public RolesPage clickOnRoles(){
        webDriverUtils.waitForElement(200).click(roles);
        log.info("Clicked on Roles Link");
        return this;
    }
    public RolesPage clickOnRoleCheckBox(String role){
        webDriverUtils.waitForElement(1000).click(clickOnCheckBox(role));
        log.info("Clicked on Role CheckBox {}", role);
        return this;
    }
    public RolesPage clickOnSetDefault(){
        webDriverUtils.waitForElement(1000).click(setAsDefault);
        log.info("Clicked on set Default");
        return this;
    }
    public RolesPage validateRoleIsSetAsDefault(String role){
        webDriverUtils.waitForElement(1500);
        log.info("Validating Role is set as Default {}", role);
        Assert.assertEquals(webDriverUtils.getText(defaultText),role);
        return this;
    }
    public RolesPage clickOnDelete(){
        webDriverUtils.click(deleteButton);
        log.info("Clicked on Delete Button");
        return this;
    }
    public void clickOnConfirmDelete(){
        webDriverUtils.click(confirmDeleteButton);
        log.info("Clicked on Confirm Delete Button");
    }



}
