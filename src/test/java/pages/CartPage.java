package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    public CartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    WebDriver driver = Driver.getDriver();

    // --- Cart Page ---

    @FindBy(xpath = "//tbody/tr")
    List<WebElement> productInCart;

    /**
     * Returns the names of all products currently in the cart.
     */
    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement row : productInCart) {
            String name = row.findElement(By.cssSelector(".cart_description h4 a")).getText();
            names.add(name);
        }
        return names;
    }

    /**
     * Returns the name of the product at the given index (0-based).
     */
    public String getProductName(int index) {
        return productInCart.get(index)
                .findElement(By.cssSelector(".cart_description h4 a"))
                .getText();
    }

    /**
     * Returns the prices of all products in the cart in "Rs. 500" format.
     */
    public List<String> getProductPrices() {
        List<String> prices = new ArrayList<>();
        for (WebElement row : productInCart) {
            String price = row.findElement(By.cssSelector(".cart_price p")).getText();
            prices.add(price);
        }
        return prices;
    }

    /**
     * Returns the price of the product at the given index.
     */
    public String getProductPrice(int index) {
        return productInCart.get(index)
                .findElement(By.cssSelector(".cart_price p"))
                .getText();
    }

    /**
     * Returns the price of the product at the given index as a plain
     * integer, stripping the "Rs. " prefix.
     * Example: "Rs. 500" -> 500
     */
    public int getProductPriceAsInt(int index) {
        String rawPrice = getProductPrice(index);
        return Integer.parseInt(rawPrice.replaceAll("[^0-9]", ""));
    }

    /**
     * Returns the quantities of all products in the cart.
     */
    public List<String> getProductQuantities() {
        List<String> quantities = new ArrayList<>();
        for (WebElement row : productInCart) {
            String qty = row.findElement(By.cssSelector(".cart_quantity button")).getText();
            quantities.add(qty);
        }
        return quantities;
    }

    /**
     * Returns the quantity of the product at the given index.
     */
    public String getProductQuantity(int index) {
        return productInCart.get(index)
                .findElement(By.cssSelector(".cart_quantity button"))
                .getText();
    }

    /**
     * Returns the quantity of the product at the given index as an int.
     */
    public int getProductQuantityAsInt(int index) {
        return Integer.parseInt(getProductQuantity(index));
    }

    /**
     * Returns the total prices (quantity * price) of all products in the cart.
     */
    public List<String> getProductTotals() {
        List<String> totals = new ArrayList<>();
        for (WebElement row : productInCart) {
            String total = row.findElement(By.cssSelector(".cart_total_price")).getText();
            totals.add(total);
        }
        return totals;
    }

    /**
     * Returns the total price of the product at the given index.
     */
    public String getProductTotal(int index) {
        return productInCart.get(index)
                .findElement(By.cssSelector(".cart_total_price"))
                .getText();
    }

    /**
     * Returns the total price of the product at the given index as a plain integer.
     */
    public int getProductTotalAsInt(int index) {
        String rawTotal = getProductTotal(index);
        return Integer.parseInt(rawTotal.replaceAll("[^0-9]", ""));
    }

    /**
     * Returns the number of products currently in the cart (row count).
     */
    public int getProductCount() {
        return productInCart.size();
    }

    /**
     * Returns the data-product-id value of the product at the given index.
     * Useful when deleting or verifying a product.
     */
    public String getProductId(int index) {
        return productInCart.get(index)
                .findElement(By.cssSelector(".cart_delete a"))
                .getAttribute("data-product-id");
    }

    /**
     * Finds the row index for the given product name.
     * Returns -1 if not found.
     */
    public int getRowIndexByProductName(String productName) {
        List<String> names = getProductNames();
        String normalizedTarget = productName.trim().replaceAll("\\s+", " ");
        for (int i = 0; i < names.size(); i++) {
            String normalizedActual = names.get(i).trim().replaceAll("\\s+", " ");
            if (normalizedActual.equalsIgnoreCase(normalizedTarget)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Clicks the delete ("x") link for the row matching the given product name.
     */
    public void deleteProductByName(String productName) {
        int index = getRowIndexByProductName(productName);
        if (index == -1) {
            throw new RuntimeException("No product named '" + productName + "' was found in the cart.");
        }
        productInCart.get(index)
                .findElement(By.cssSelector(".cart_delete a"))
                .click();
    }

}