package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class ProductsPage {
    public ProductsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    WebDriver driver = Driver.getDriver();

    // --- Products Page (products page) ---

    @FindBy(xpath = "//h2[.='All Products']")
    public WebElement allProductsTitle;

    //    The first two must be cropped.
    @FindBy(xpath = "//div[@class='col-sm-4']")
    public List<WebElement> allProductsList;

    public WebElement getProductFromProductsList(int index) {
        return driver.findElement(By.xpath("(//div[@class='col-sm-4'])[" + (index + 2) + "]"));
    }

    public WebElement getProductNameFromProductsList(int index) {
        return driver.findElement(By.xpath("((//div[@class='col-sm-4'])[" + (index + 2) + "]//h2)[1]"));
    }

    @FindBy(xpath = "//a[@href='/product_details/1']")
    public WebElement viewProductButtonOfFirstProduct;

    //    Search Elements
    @FindBy(id = "search_product")
    public WebElement searchProductInput;

    @FindBy(id = "submit_search")
    public WebElement submitSearchButton;
}
