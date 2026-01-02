package org.quickbase.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class WebDriverUtils {

    public static Logger log = LogManager.getLogger(WebDriverUtils.class);

    public WebDriver driver;

    public WebDriverUtils() {
    }

    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        String title = null;
        try {
            title = driver.getTitle();
            return title;
        } catch (Exception e) {
            log.error("Could not get the title of the page :: " + e);
        }
        return title;
    }

    /**
     * return driver object
     *
     * @return driver
     */
    public WebDriver getDriver() {
        return driver;
    }


    /**
     * checks if the provided element is present, if not returns false
     *
     * @param el
     * @return boolean true/false
     */

    public boolean isElementPresent(WebElement el) {
        try {
            waitForElementToBePresent(el, 10);
            el.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Overloaded method , takes String xpath as parameter
     *
     * @param xpathOfEl
     * @return
     */

    public boolean isElementPresent(String xpathOfEl) {
        try {
            driver.findElement(By.xpath(xpathOfEl)).isDisplayed();
            //Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementClickable(WebElement el) {
        try {
            waitForElementToBeClickable(el, 10);
            return true;
        } catch (Exception e) {
            log.error("Could not click on the element::" + e);

        }
        return false;
    }

    public boolean isElementEnabled(WebElement el) {
        try {
            boolean res = el.isEnabled();
            return res;
        } catch (Exception e) {
            log.error("Could not click on the element::" + e);
        }
        return false;
    }

    public boolean isElementEditable(WebElement el) {

        boolean res = false;
        try {
            res = ((el.getAttribute("disabled") == null) || (el.getAttribute("readonly") == null));
            return res;
        } catch (Exception e) {
            log.error("Could not check if the element is editable ::" + e);
        }
        return res;
    }


    /**
     * checks if the provided element is visible, if not returns false
     *
     * @param
     * @return boolean true/false
     */

    public boolean isElementVisible(WebElement el) {

        try {
            el.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * checks if the provided element is visible, if not returns false
     * Overlaoded method, takes xpath as input
     *
     * @param
     * @return boolean true/false
     */

    public boolean isElementVisible(String xpath) {

        try {
            ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * checks if the provided element is not present, if not returns false
     *
     * @param
     * @return boolean true/false
     */

    public boolean isElementInvisible(By by) {
        try {
            ExpectedConditions.invisibilityOfElementLocated(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clicks on an item, waits for the specified amount of time waits for the item
     * to be validated
     *
     * @param clickItem
     * @param itemToValidate
     * @param timeout
     */

    public void clickAndWait(WebElement clickItem, String itemToValidate, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            clickItem.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(itemToValidate)));
        } catch (Exception e) {
            log.error("Could not click on the element::" + e);
        }

    }

    /**
     * Clicks on the provided web element
     *
     * @param el
     */

    public void click(WebElement el) {
        try {
            waitForElementToBePresent(el, 20);
            el.click();
        } catch (Exception e) {
            log.error("Couldn't click the element specified ::" + e);
        }
    }

    public void click(WebElement el, int timeOut) {
        try {
            waitForElementToBePresent(el, timeOut);
            el.click();
        } catch (Exception e) {
            log.error("Couldn't click the element specified ::" + e);
        }
    }

    public WebDriverUtils waitForElement(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    /**
     * @param xpath of the element to be clicked.
     */
    public void click(String xpath) {
        try {
            driver.findElement(By.xpath(xpath)).click();
        } catch (Exception e) {
            log.error("Unable to perform click ::" + e.getMessage());

        }
    }


    public void click_js(WebElement el) {
        try {
            JavascriptExecutor ex = (JavascriptExecutor) driver;
            ex.executeScript("arguments[0].click()", el);

        } catch (Exception e) {
            log.error("Unable to perform key Enter ::" + e.getMessage());

        }
    }

    public void actionsClick(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    /**
     * Waits for the element to be visible ,waits till the condition condition is
     * fulfilled.
     *
     * @param by
     * @param timeout
     */

    public void waitForElementToBeVisible(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            log.error("Element is not visible :: " + e);
        }
    }

    /**
     * Waits for the element to be present ,waits till the condition condition is
     * fulfilled.
     *
     * @param by
     * @param timeout
     */

    public void waitForElementToBePresent(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            log.error("Element is not present :: " + e);
        }
    }

    /**
     * Overloaded method , waitForElementToBePresent
     *
     * @param el
     * @param timeout
     */

    public void waitForElementToBePresent(WebElement el, int timeout) {
        try {
            log.info("Waiting for element to be displayed !!");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOf(el));
        } catch (Exception e) {
            log.error("Element is not present :: " + e);
        }
    }

    public void scrollToElementAndClick(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        waitForElement(1500);
        actions.scrollToElement(element)
                .click()
                .perform();
        waitForElement(500).click(element);
    }

    public void scrollToElementAndSetText(WebElement element, WebDriver driver, String text) {
        Actions actions = new Actions(driver);
        waitForElement(1500);
        actions.scrollToElement(element)
                .click()
                .perform();
        waitForElement(500).setText(element, text);
    }

    public void waitForElementToBeClickable(By by, int timeout) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            log.error("Element is not clickable :: " + e.getMessage());
        }
    }

    public void waitForElementToBeClickable(WebElement el, int timeout) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeClickable(el));
        } catch (Exception e) {
            log.error("Element is not clickable :: " + e.getMessage());
        }
    }

    /**
     * Wait for element to be invisible
     *
     * @param el
     * @param timeout in seconds
     */

    public void waitForElementToBeInvisible(WebElement el, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.invisibilityOf(el));
        } catch (Exception e) {
            log.error("Element is still present :: " + e);
        }
    }

    public void waitForElementToBeInvisible(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            log.error("Element is still present :: " + e);
        }
    }

    /**
     * Selects a value from a select list, pointed by the locator provided by the
     * user.
     *
     * @param value
     */

    public void selectValueFromList(By by, String value) {
        try {
            Select select = new Select(driver.findElement(by));
            select.selectByValue(value);
        } catch (Exception e) {

            log.error("Could not select element ::" + e);
        }
    }

    /**
     * Overloaded method to select item
     *
     * @param value
     */
    public void selectValueFromList(WebElement el, String value) {
        try {
            Select select = new Select(el);
            select.selectByValue(value);
        } catch (Exception e) {

            log.error("Could not select element ::" + e);
        }
    }

    public void selectValueFromList_js(WebElement el, String value) {
        try {
            click(el);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        } catch (Exception e) {

            log.error("Could not select element ::" + e);
        }
    }

    /**
     * Move the cursor to the provided locator and click, Action class is used.
     *
     * @param xpath
     * @throws InterruptedException
     */

    public void clickAt(String xpath) throws InterruptedException {
        WebElement ele = driver.findElement(By.xpath(xpath));
        Actions act = new Actions(driver);
        act.moveToElement(ele).click().build().perform();

    }

    /**
     * Waits for the specified amount of time for the page to be loaded.
     *
     * @param timeout
     * @throws Exception
     */

    public void waitForPageToBeLoaded(int timeout) throws Exception {

        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };

        try {
            implicitlyWait(timeout);


        } catch (Exception e) {
            log.error("Page load time expired ::" + e);
        }

    }

    /**
     * Switches from current frame to specified frame
     *
     * @param frameName
     */

    public void switchToFrame(String frameName) {

        try {

            driver.switchTo().frame(frameName);

        } catch (Exception e) {
            log.error("Couldn't switch to frame ::" + e);
        }
    }

    /**
     * Sets the specified text in a textfield.
     *
     * @param el
     * @param value
     */

    public void setText(String el, String value) {

        try {
            driver.findElement(By.xpath(el)).sendKeys(value);

        } catch (Exception e) {
            log.error("Unable to set text ::" + e);
        }

    }

    /**
     * Sets the specified text in a textfield.
     *
     * @param el
     * @param value
     */

    public void setText(WebElement el, String value) {
        try {
            waitForElementToBePresent(el, 20);
            waitForElement(1500);
            clearAndFill(el, value);
        } catch (Exception e) {
            log.error("Unable to set text ::" + e);
        }
    }

    public void clearAndFill(WebElement element, String text) {
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        element.sendKeys(text);
    }

    public void setText_js(WebElement el, String value) {

        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].value='" + value + "';", el);

        } catch (Exception e) {
            log.error("Unable to set text ::" + e);
        }

    }

    public String getText(WebElement el) {

        String text = "";
        try {
            waitForElementToBePresent(el, 20);
            text = el.getText();

        } catch (Exception e) {
            log.error("Unable to get text ::" + e);
        }
        return text;
    }

    public String getValueAttribute(WebElement el) {
        String text = "";
        try {
            waitForElementToBePresent(el, 20);
            text = el.getAttribute("value");

        } catch (Exception e) {
            log.error("Unable to get text ::" + e);
        }
        return text;
    }


    public String getText(String xpath) {

        String text = "";
        try {
            text = driver.findElement(By.xpath(xpath)).getText();

        } catch (Exception e) {
            log.error("Unable to get text ::" + e);
        }
        return text;
    }


    public String getCssAttribute(WebElement el, String attribute) {

        String res = "";
        try {
            res = el.getCssValue(attribute);

        } catch (Exception e) {
            log.error("Unable to set text ::" + e);
        }
        return res;
    }


    public void keyDown(WebElement srcElement) {

        try {
            Actions action = new Actions(driver);

            action.keyDown(Keys.ARROW_DOWN).build().perform();
            action.keyUp(Keys.ARROW_DOWN).build().perform();

        } catch (Exception e) {
            log.error("Unable to perform keydown ::" + e);
        }
    }

    public void keyEnter() {

        try {

            Actions action = new Actions(driver);
            action.keyDown(Keys.ENTER).build().perform();
            action.keyUp(Keys.ENTER).build().perform();

        } catch (Exception e) {
            log.error("Unable to perform key Enter ::" + e.getMessage());
        }
    }

    public void hoverOnWebElementAndClick(WebElement el, WebElement topwear) {

        try {

            Actions action = new Actions(driver);
            action.moveToElement(el).perform();
            waitForElementToBePresent(topwear, 10);
            action.moveToElement(topwear).click().perform();

        } catch (Exception e) {
            log.error("Unable to perform key Enter ::" + e.getMessage());
        }

    }

    /**
     * Implicity wait for mentioned seconds
     *
     * @time it is the time you want to wait , in seconds
     */
    public void implicitlyWait(int time) {

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));

        } catch (Exception e) {
            log.error("Unable to perform Implicit wait ::" + e.getMessage());
        }

    }

    public void reloadPage() {

        try {
            driver.navigate().refresh();

        } catch (Exception e) {
            log.error("Unable to perform page reload ::" + e.getMessage());
        }

    }

    public String getCurentWindow() {

        String winHandle = "";
        try {

            winHandle = driver.getWindowHandle();
            return winHandle;

        } catch (Exception e) {
            log.error("Unable to perform window handle::" + e.getMessage());
        }
        return winHandle;
    }

    public void switchToWindow() {

        try {

            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }

        } catch (Exception e) {
            log.error("Unable to perform window switch::" + e.getMessage());
        }

    }

    public void openURL(String URL) {
        try {
            driver.get(URL);
            log.info("Navigated to {}", URL);
        } catch (Exception e) {
            log.error("Unable to perform window switch::" + e.getMessage());
        }

    }


}