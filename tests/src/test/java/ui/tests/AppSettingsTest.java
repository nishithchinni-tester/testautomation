package ui.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    HomePage homePage;
    ApplicationUtils applicationUtils;
    Logger log = LogManager.getLogger(AppSettingsTest.class);
    TestData testData;

    /**
     * Pre-Requisite : QB User Should be logged in.
     * Executing BeforeTest for every test method.
     */
    @BeforeMethod(alwaysRun = true)
    public void login() {
        testData = TestContext.getTestData();
        applicationUtils = new ApplicationUtils(driver);
        homePage = applicationUtils.loginQB(testData.getUserName(), testData.getPassword());
    }

    /**
     * QB User should be logged in.
     * Validating As QB user he should be able to change App Name & App Description.
     */
    @Test(groups = {"userSettings"},
            description = "Validate QB user is able to change App Name & App Description")
    public void validateUserIsAbleToChangeAppNameAndAppDescription() {
        if (homePage.isUserOnHomePage()) {
            String appNameChange = applicationUtils.
                    getRandomString("Sample Application_QB_User");
            String appDescChange = applicationUtils.
                    getRandomString("Sample Description_QB_Desc");
            homePage.clickOnMenu()
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
        if (homePage.isUserOnHomePage()) {
            String colorCode = applicationUtils.getRandomColor();
            homePage.clickOnMenu()
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
        if (homePage.isUserOnHomePage()) {
            String fieldValue = applicationUtils.
                    getRandomString("Custom_");
            homePage.clickOnMenu()
                    .clickOnAppsMenu()
                    .clickOnApp()
                    .clickOnAppSettings()
                    .clickOnSettingOptionOrFieldValue
                            (SettingsOptions.TABLES.getValue(), AppSettingsPage.class)
                    .clickOnTableName(testData.getTableName())
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
     * Validating As QB user he should be able to create a new table from scratch.
     */
    @Test(groups = {"userSettings"},
            description = "Validate QB user is able to Add new Table From Scratch")
    public void validateUserIsAbleToAddNewTableFromScratch() {
        if (homePage.isUserOnHomePage()) {
            String tableName = applicationUtils.getRandomString("QB_User_Table");
            String singleRecord = applicationUtils.getRandomString("QB_UsrTbl_Record");
            String fieldValue = applicationUtils.getRandomString("Custom_");
            homePage.clickOnMenu()
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
                            (SettingsOptions.TABLES.getValue(), AppSettingsPage.class)
                    .clickOnTableName(tableName);
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
        if (homePage.isUserOnHomePage()) {
            String roleName = applicationUtils.getRandomString("Team Lead");
            String roleDescription = applicationUtils.getRandomString("Leading the Team");
            homePage.clickOnMenu()
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
        if (homePage.isUserOnHomePage()) {
            String headerCustomRight = applicationUtils.getRandomString("Right_",5);
            String headerCustomLeft = applicationUtils.getRandomString("Left_",4);
            String footerCustomRight = applicationUtils.getRandomString("Right_Footer",4);
            homePage.clickOnMenu()
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
                    .clickOnHeaderDefaultButton();
        } else {
            log.info("User is not on HomePage");
            Assert.fail("User is not on HomePage");
        }
    }
}
