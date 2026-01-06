package ui.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.TestContext;
import ui.enums.SettingsOptions;
import ui.models.TestData;
import ui.pages.*;
import ui.utils.ApplicationUtils;
import ui.BaseTest;

public class AppSettingsTest extends BaseTest {

    private final ThreadLocal<HomePage> homePage = new ThreadLocal<>();
    private final ThreadLocal<ApplicationUtils> applicationUtils = new ThreadLocal<>();
    private final ThreadLocal<TestData> testData = new ThreadLocal<>();

    Logger log = LogManager.getLogger(AppSettingsTest.class);

    /**
     * Pre-Requisite : QB User Should be logged in.
     * Executing BeforeTest for every test method.
     */
    @BeforeMethod(alwaysRun = true)
    public void login() {
        WebDriver driver = getDriver();
        TestData data = TestContext.getTestData();
        this.testData.set(data);
        ApplicationUtils utils = new ApplicationUtils(driver);
        this.applicationUtils.set(utils);
        HomePage hp = utils.loginQB(data.getUserName(), data.getPassword());
        this.homePage.set(hp);
    }

    /**
     * QB User should be logged in.
     * Validating As QB user he should be able to change App Name & App Description.
     */
    @Test(groups = {"userSettings"},
            description = "Validate QB user is able to change App Name & App Description")
    public void validateUserIsAbleToChangeAppNameAndAppDescription() {
        HomePage currentHomePage = homePage.get();
        ApplicationUtils utils = applicationUtils.get();
        if (currentHomePage.isUserOnHomePage()) {
            String appNameChange = utils.getRandomString("Sample Application_QB_User");
            String appDescChange = utils.getRandomString("Sample Description_QB_Desc");
            homePage.get().clickOnMenu()
                    .clickOnAppsMenu()
                    .clickOnApp()
                    .clickOnAppSettings()
                    .changeAppName(appNameChange)
                    .changeAppDescription(appDescChange)
                    .clickOnSaveButton(HomePage.class)
                    .clickOnMenu()
                    .clickOnAppsMenu()
                    .validateAppName(appNameChange);
        } else {
            log.info("User is not on HomePage");
            Assert.fail("User is not on HomePage");
        }
    }

    /**
     * QB User should be logged in.
     * Validating As QB user he should be able to change App Color.
     */
    @Test(groups = {"userSettings"},
            description = "Validate QB user is able to change App Icon Color")
    public void validateUserIsAbleToChangeAppColor() {
        HomePage currentHomePage = homePage.get();
        ApplicationUtils utils = applicationUtils.get();
        if (currentHomePage.isUserOnHomePage()) {
            String colorCode = utils.getRandomColor();
            currentHomePage.clickOnMenu()
                    .clickOnAppsMenu()
                    .clickOnApp()
                    .clickOnAppSettings()
                    .clickOnEdit()
                    .changeColorCode(colorCode)
                    .clickOnSaveButtonEditBox(AppSettingsPage.class)
                    .clickOnEdit()
                    .validateColorCodeIsChanged(colorCode);
        } else {
            log.info("User is not on HomePage");
            Assert.fail("User is not on HomePage");
        }
    }

    /**
     * QB User should be logged in.
     * Validating As QB user he should be able to Add New Field in existing User Table.
     */
    @Test(groups = {"userSettings"},
            description = "Validate QB user is able to Add new Field in existing table")
    public void validateUserIsAbleToVerifyExistingTableAndAddNewField() {
        HomePage currentHomePage = homePage.get();
        ApplicationUtils utils = applicationUtils.get();
        TestData data = testData.get();
        if (currentHomePage.isUserOnHomePage()) {
            String fieldValue = utils.getRandomString("Custom_");
            currentHomePage.clickOnMenu()
                    .clickOnAppsMenu()
                    .clickOnApp()
                    .clickOnAppSettings()
                    .clickOnSettingOptionOrFieldValue
                            (SettingsOptions.TABLES.getValue(), AppSettingsPage.class)
                    .clickOnTableName(data.getTableName(), AppSettingsPage.class)
                    .clickOnSettingOptionOrFieldValue
                            (SettingsOptions.FIELDS.getValue(), TablesPage.class)
                    .clickOnNewFieldButton()
                    .enterNewField(fieldValue)
                    .clickOnAddFieldOrCreateTableButton()
                    .searchNewFieldValue(fieldValue, AppSettingsPage.class)
                    .clickOnSettingOptionOrFieldValue(fieldValue, AppSettingsPage.class);
        } else {
            log.info("User is not on HomePage");
            Assert.fail("User is not on HomePage");
        }
    }

    /**
     * QB User should be logged in.
     * Validating As QB user he should be able to Add New Field in existing User Table.
     */
    @Test(groups = {"userSettings"},
            description = "Validate QB user is able to copy the existing table")
    public void validateUserIsAbleToVerifyCopyExistingTable() {
        HomePage currentHomePage = homePage.get();
        ApplicationUtils utils = applicationUtils.get();
        TestData data = testData.get();
        if (currentHomePage.isUserOnHomePage()) {
            String tableName = utils.getRandomString("Copy_Of_QB_Tbl_");
            String singleRecord = utils.getRandomString("Copy_Of_QB_Record_");
            currentHomePage.clickOnMenu()
                    .clickOnAppsMenu()
                    .clickOnApp()
                    .clickOnAppSettings()
                    .clickOnSettingOptionOrFieldValue
                            (SettingsOptions.TABLES.getValue(), TablesPage.class)
                    .clickOnCopyIconTableName(data.getTableName())
                    .enterCopyTableName(tableName)
                    .enterSingleRecordOnCopyTable(singleRecord)
                    .clickOnCopyButton()
                    .clickOnAppSettings()
                    .clickOnSettingOptionOrFieldValue
                            (SettingsOptions.TABLES.getValue(), AppSettingsPage.class)
                    .clickOnTableName(tableName, TablesPage.class);
        } else {
            log.info("User is not on HomePage");
            Assert.fail("User is not on HomePage");
        }
    }

    /**
     * QB User should be logged in.
     * Validating As QB user he should be able to create a new table from scratch and Delete the Table.
     */
    @Test(groups = {"userSettings"},
            description = "Validate QB user is able to Add new Table From Scratch And Delete the Table")
    public void validateUserIsAbleToAddNewTableFromScratchAndDeleteTheTable() {
        HomePage currentHomePage = homePage.get();
        ApplicationUtils utils = applicationUtils.get();
        if (currentHomePage.isUserOnHomePage()) {
            String tableName = utils.getRandomString("QB_User_Table");
            String singleRecord = utils.getRandomString("QB_UsrTbl_Record");
            String fieldValue = utils.getRandomString("Custom_");
            currentHomePage.clickOnMenu()
                    .clickOnAppsMenu()
                    .clickOnApp()
                    .clickOnAppSettings()
                    .clickOnNewTable()
                    .clickOnFromScratchButton()
                    .enterTableName(tableName)
                    .selectIcon()
                    .enterSingleRecord(singleRecord)
                    .clickOnAddFieldOrCreateTableButton()
                    .enterNewField(fieldValue)
                    .clickOnAddFieldOrCreateTableButton()
                    .clickOnJumpSettingsMenu()
                    .clickOnAppSettingsMenuItem()
                    .clickOnSettingOptionOrFieldValue
                            (SettingsOptions.TABLES.getValue(), TablesPage.class)
                    .clickOnDeleteBoxForTableName(tableName)
                    .enterYesOnDeleteBox()
                    .clickOnDeleteTableButton()
                    .validateTableIsDeleted(tableName);
        } else {
            log.info("User is not on HomePage");
            Assert.fail("User is not on HomePage");
        }
    }

    /**
     * QB User should be logged in.
     * Validating As QB Admin user he should be able to create a new role and make it default and delete the user role.
     */
    @Test(groups = {"userSettings"},
            description = "Validate QB user is able to add new role and set it as default and delete the user")
    public void validateUserIsAbleToAddNewRoleAndSetItAsDefaultAndDeleteRole() {
        HomePage currentHomePage = homePage.get();
        ApplicationUtils utils = applicationUtils.get();
        if (currentHomePage.isUserOnHomePage()) {
            String roleName = utils.getRandomString("Team Lead");
            String roleDescription = utils.getRandomString("Leading the Team");
            currentHomePage.clickOnMenu()
                    .clickOnAppsMenu()
                    .clickOnApp()
                    .clickOnAppSettings()
                    .clickOnSettingOptionOrFieldValue
                            (SettingsOptions.ROLES.getValue(), RolesPage.class)
                    .clickOnNewRole()
                    .enterRoleName(roleName)
                    .enterRoleDescription(roleDescription)
                    .clickOnOKButton()
                    .clickOnProperties()
                    .clickOnSaveButton()
                    .clickOnRoles()
                    .clickOnRoleCheckBox(roleName)
                    .clickOnSetDefault()
                    .validateRoleIsSetAsDefault(roleName)
                    .clickOnRoleCheckBox("Administrator")
                    .clickOnSetDefault()
                    .clickOnRoleCheckBox(roleName)
                    .clickOnDelete()
                    .clickOnConfirmDelete();
        } else {
            log.info("User is not on HomePage");
            Assert.fail("User is not on HomePage");
        }
    }

    /**
     * QB User should be logged in.
     * Validating As QB Admin user he should be able to Add Custom Header & Footer Text in Branding Section.
     */
    @Test(groups = {"userSettings"},
            description = "Validate QB user is able to add custom header & footer text in branding section")
    public void validateUserIsAbleToAddHeaderAndFooterCustomTextInBranding() {
        HomePage currentHomePage = homePage.get();
        ApplicationUtils utils = applicationUtils.get();
        if (currentHomePage.isUserOnHomePage()) {
            String headerCustomRight = utils.getRandomString("Right_",5);
            String headerCustomLeft = utils.getRandomString("Left_",4);
            String footerCustomRight = utils.getRandomString("Right_Footer",4);
            currentHomePage.clickOnMenu()
                    .clickOnAppsMenu()
                    .clickOnApp()
                    .clickOnAppSettings()
                    .clickOnSettingOptionOrFieldValue
                            (SettingsOptions.BRANDING.getValue(), BrandingPage.class)
                    .clickOnHeader()
                    .clickOnHeaderBasicButton()
                    .enterValueInLeftElement(headerCustomLeft)
                    .enterValueInRightElement(headerCustomRight)
                    .clickOnSaveButton()
                    .validateNewTextIsUpdatedOnLeftHeader(headerCustomLeft)
                    .validateNewTextIsUpdatedOnRightHeader(headerCustomRight)
                    .clickOnFooter()
                    .clickOnFooterBasicButton()
                    .enterValueInRightFooterElement(footerCustomRight)
                    .clickOnSaveButton()
                    .validateNewTextIsUpdatedOnRightFooter(footerCustomRight)
                    .clickOnFooter()
                    .clickOnFooterDefaultButton()
                    .clickOnSaveButton()
                    .validateFooterIsNotDisplayed()
                    .clickOnHeader()
                    .clickOnHeaderDefaultButton()
                    .clickOnSaveButton();
        } else {
            log.info("User is not on HomePage");
            Assert.fail("User is not on HomePage");
        }
    }
}
