package ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.quickbase.utils.WebDriverUtils;
import org.testng.Assert;
import ui.TestContext;

public class BrandingPage extends BasePage {
    private final WebDriver driver = TestContext.getDriver();
    private final WebDriverUtils webDriverUtils = new WebDriverUtils();
    private final Logger log = LogManager.getLogger(BrandingPage.class);

    public BrandingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(@href,'Header')]")
    private WebElement header;

    @FindBy(xpath = "//a[contains(@href,'Footer')]")
    private WebElement footer;

    @FindBy(id = "uiCustomTopLeft")
    private WebElement leftElement;

    @FindBy(id = "uiCustomTopRight")
    private WebElement rightElement;

    @FindBy(id = "saveButton")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@data-test-id = 'brandingContentLeft']")
    private WebElement leftHeaderContent;

    @FindBy(xpath = "//div[@data-test-id = 'brandingContentRight']")
    private WebElement rightHeaderContent;

    @FindBy(xpath = "(//label[text()='Basic'])[2]/../input")
    private WebElement footerBasicRadioButton;

    @FindBy(xpath = "(//label[text()='Basic'])[1]/../input")
    private WebElement headerBasicRadioButton;

    @FindBy(xpath = "(//label[text()='Default'])[1]/../input")
    private WebElement footerDefaultRadioButton;

    @FindBy(xpath = "(//label[contains(text(),'Default')])[1]/../input")
    private WebElement headerDefaultRadioButton;

    @FindBy(id = "uiCustomBotRight")
    private WebElement footerRightElement;

    @FindBy(className = "CustomBrandText")
    private WebElement footerRightContent;

    public BrandingPage clickOnFooter(){
        webDriverUtils.waitForElement(500).click(footer);
        log.info("Clicked on Footer Button.");
        return this;
    }

    public BrandingPage clickOnHeader(){
        webDriverUtils.click(header);
        log.info("Clicked on Header Button.");
        return this;
    }

    public BrandingPage enterValueInRightElement(String text){
        webDriverUtils.setText(rightElement, text);
        log.info("Set Text in Right Element.");
        return this;
    }

    public BrandingPage enterValueInLeftElement(String text){
        webDriverUtils.setText(leftElement, text);
        log.info("Set Text in Left Element.");
        return this;
    }

    public BrandingPage clickOnSaveButton(){
        webDriverUtils.click(saveButton);
        log.info("Clicked on Save Button.");
        return this;
    }

    public BrandingPage validateNewTextIsUpdatedOnLeftHeader(String leftText){
        webDriverUtils.waitForElement(1500);
        log.info("Validating New Left header text is set as Default {}", leftText);
        Assert.assertEquals(webDriverUtils.getText(leftHeaderContent), leftText);
        return this;
    }

    public BrandingPage validateNewTextIsUpdatedOnRightHeader(String rightText){
        webDriverUtils.waitForElement(1500);
        log.info("Validating New Right header text is set as Default {}", rightText);
        Assert.assertEquals(webDriverUtils.getText(rightHeaderContent), rightText);
        return this;
    }

    public BrandingPage clickOnFooterBasicButton(){
        webDriverUtils.click(footerBasicRadioButton);
        log.info("Clicked on Footer Basic Button.");
        return this;
    }

    public BrandingPage clickOnFooterDefaultButton(){
        webDriverUtils.click(footerDefaultRadioButton);
        log.info("Clicked on Footer Default Button.");
        return this;
    }

    public BrandingPage clickOnHeaderDefaultButton(){
        webDriverUtils.click(headerDefaultRadioButton);
        log.info("Clicked on Header Default Button.");
        return this;
    }

    public BrandingPage clickOnHeaderBasicButton(){
        webDriverUtils.click(headerBasicRadioButton);
        log.info("Clicked on Header Basic Button.");
        return this;
    }

    public BrandingPage enterValueInRightFooterElement(String text){
        webDriverUtils.setText(footerRightElement, text);
        log.info("Set Text in Right Footer Element.");
        return this;
    }

    public BrandingPage validateNewTextIsUpdatedOnRightFooter(String rightText){
        webDriverUtils.waitForElement(1500);
        log.info("Validating New Right Footer text is set as Default {}", rightText);
        Assert.assertEquals(webDriverUtils.getText(footerRightContent), rightText);
        return this;
    }

    public BrandingPage validateFooterIsNotDisplayed(){
        webDriverUtils.waitForElement(1500);
        log.info("Validating New Right Footer text is invisible when footer is set to default");
        if(webDriverUtils.isElementPresent(footerRightContent)){
            Assert.fail("Footer should be invisible when changed to default.");
        }
        return this;
    }
}
