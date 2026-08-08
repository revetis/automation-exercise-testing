package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Collection of static reusable Selenium helper methods used across
 * step definition classes (wait, click, type, dropdown, checkbox/radio,
 * HTML5 form validation and test data generation).
 *
 * @author Revetis (Samet)
 */
public class ReusableMethods {

    /**
     * Builds a fresh {@link WebDriverWait} bound to the current driver
     * instance, using the explicitWaitTimeout defined in the config file.
     *
     * @return a configured WebDriverWait
     */
    private static WebDriverWait getWait() {
        return new WebDriverWait(Driver.getDriver(),
                Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("explicitWaitTimeout"))));
    }

    // ---------------- WAITS ----------------

    /**
     * Waits until the given element is visible.
     *
     * @param element the WebElement to wait for
     * @return the same element once visible
     */
    public static WebElement waitOfVisibility(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until an element located by the given locator is visible.
     *
     * @param locator the By locator
     * @return the located WebElement once visible
     */
    public static WebElement waitOfVisibility(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the given element is clickable.
     *
     * @param element the WebElement to wait for
     * @return the same element once clickable
     */
    public static WebElement waitOfClickable(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until an element located by the given locator is clickable.
     *
     * @param locator the By locator
     * @return the located WebElement once clickable
     */
    public static WebElement waitOfClickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ---------------- SCROLL HELPER ----------------

    /**
     * Scrolls the given element into the center of the viewport via
     * JavaScript. Used before click/type actions to reduce the chance
     * of overlapping elements (e.g. ad iframes) intercepting the action.
     *
     * @param element the WebElement to scroll into view
     */
    public static void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    // ---------------- CLICK / TYPE ----------------

    /**
     * Waits until the element is clickable, scrolls it into view, then
     * clicks it via JavaScript to bypass any overlapping elements
     * (e.g. ad iframes intercepting the click point).
     *
     * @param element the WebElement to click
     */
    public static void clickElement(WebElement element) {
        waitOfClickable(element);
        scrollIntoView(element);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Clicks the given element without scrolling it into view first.
     * Use this for elements that only appear on :hover (e.g. an
     * "Add to Cart" overlay revealed by {@link #hover(WebElement)}),
     * since a scroll performed after the hover can move the page under
     * a stationary mouse, causing the browser to drop the :hover state
     * on the intended card and reveal/hide the wrong overlay before the
     * click lands.
     *
     * @param element the WebElement to click
     */
    public static void clickElementWithoutScroll(WebElement element) {
        waitOfClickable(element);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Waits until the element is visible, scrolls it into view, focuses it
     * via JavaScript (bypassing any overlapping elements like ad iframes),
     * then sends the given text.
     *
     * @param element the target input element
     * @param text    the text to type
     */
    public static void sendKeysToElement(WebElement element, String text) {
        waitOfVisibility(element);
        scrollIntoView(element);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].focus();", element);
        element.sendKeys(text);
    }

    /**
     * Waits until the element is visible, scrolls it into view, focuses it
     * via JavaScript, clears any existing value, then sends the given text.
     *
     * @param element the target input element
     * @param text    the text to type
     */
    public static void clearAndSendKeys(WebElement element, String text) {
        waitOfVisibility(element);
        scrollIntoView(element);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].focus();", element);
        element.clear();
        element.sendKeys(text);
    }

    // ---------------- DROPDOWN ----------------

    /**
     * Selects a dropdown option by its visible text.
     *
     * @param dropdown    the select element
     * @param visibleText the visible option text to select
     */
    public static void selectByVisibleText(WebElement dropdown, String visibleText) {
        scrollIntoView(dropdown);
        Select select = new Select(waitOfVisibility(dropdown));
        select.selectByVisibleText(visibleText);
    }

    /**
     * Selects a dropdown option by its underlying value attribute.
     *
     * @param dropdown the select element
     * @param value    the option value to select
     */
    public static void selectByValue(WebElement dropdown, String value) {
        scrollIntoView(dropdown);
        Select select = new Select(waitOfVisibility(dropdown));
        select.selectByValue(value);
    }

    // ---------------- CHECKBOX / RADIO ----------------

    /**
     * Clicks the given checkbox or radio element only if it is not
     * already selected, avoiding an accidental toggle-off.
     *
     * @param checkboxOrRadio the checkbox or radio WebElement
     */
    public static void checkIfNotSelected(WebElement checkboxOrRadio) {
        WebElement el = waitOfVisibility(checkboxOrRadio);
        scrollIntoView(el);
        if (!el.isSelected()) {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].click();", el);
        }
    }

    // ---------------- HTML5 VALIDATION ----------------

    /**
     * Disables native HTML5 form validation by setting the
     * {@code novalidate} attribute via JavaScript.
     *
     * @param form the form element to disable validation on
     */
    public static void disableFormValidation(WebElement form) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('novalidate', 'true');", form);
    }

    /**
     * Checks whether the given form currently satisfies HTML5 validation
     * constraints, without submitting it.
     *
     * @param form the form element to check
     * @return true if all constraints are satisfied, false otherwise
     */
    public static boolean isFormValid(WebElement form) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        return (Boolean) js.executeScript("return arguments[0].checkValidity();", form);
    }

    // ---------------- DATA GENERATION ----------------

    /**
     * Generates a unique email address by appending the current
     * timestamp to the given prefix and domain.
     *
     * @param prefix the local-part prefix (before the timestamp)
     * @param domain the email domain (e.g. "testmail.com")
     * @return a unique email address
     */
    public static String generateUniqueEmail(String prefix, String domain) {
        return prefix + System.currentTimeMillis() + "@" + domain;
    }

    // ---------------- MISC ----------------

    /**
     * Safely checks whether an element is displayed, returning false
     * instead of throwing if the element cannot be found or waited on.
     *
     * @param element the WebElement to check
     * @return true if displayed, false otherwise
     */
    public static boolean isElementDisplayed(WebElement element) {
        try {
            return waitOfVisibility(element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ---------------- HOVER ----------------

    /**
     * Waits until the given element is visible, scrolls it into view,
     * and performs a mouse hover action using Actions.
     *
     * @param element the WebElement to hover over
     */
    public static void hover(WebElement element) {
        waitOfVisibility(element);
        scrollIntoView(element);
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }
}