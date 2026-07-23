package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ProductDetailPage {
    public ProductDetailPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    WebDriver driver = Driver.getDriver();

    // --- Product Detail Page (product detail page) ---

    @FindBy(xpath = "//div[@class='product-information']/h2")
    public WebElement productName;

    @FindBy(xpath = "//div[@class='product-information']/p")
    public WebElement productCategory;

    @FindBy(xpath = "//div[@class='product-information']/span/span")
    public WebElement productPrice;

    @FindBy(xpath = "(//div[@class='product-information']/p/b)[1]/ancestor::p")
    public WebElement productAvailability;

    @FindBy(xpath = "(//div[@class='product-information']/p/b)[2]/ancestor::p")
    public WebElement productCondition;

    @FindBy(xpath = "(//div[@class='product-information']/p/b)[3]/ancestor::p")
    public WebElement productBrand;
}
