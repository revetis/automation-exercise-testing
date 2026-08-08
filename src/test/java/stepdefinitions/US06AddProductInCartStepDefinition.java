package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.ProductsPage;
import utilities.Logger4j;
import utilities.ReusableMethods;

import java.util.ArrayList;
import java.util.List;

public class US06AddProductInCartStepDefinition {
    ProductsPage productsPage = new ProductsPage();
    CartPage cartPage = new CartPage();
    List<String> productInCart = new ArrayList<>();

    @When("Hover over first product.")
    public void hover_over_first_product() {
        Logger4j.info("Hovering over the first product in the products list.");
        WebElement firstProduct = productsPage.getProductFromProductsList(0);
        ReusableMethods.waitOfVisibility(firstProduct);
        ReusableMethods.scrollIntoView(firstProduct);
        Logger4j.info("Hovered over the first product successfully.");
    }

    @When("Click continue shopping button.")
    public void click_continue_shopping_button() {
        Logger4j.info("Clicking the 'Continue Shopping' button.");
        ReusableMethods.clickElement(productsPage.continueShoppingButton);
        Logger4j.info("'Continue Shopping' button clicked.");
    }

    @When("Hover over second product.")
    public void hover_over_second_product() {
        Logger4j.info("Hovering over the second product in the products list.");
        WebElement secondProduct = productsPage.getProductFromProductsList(1);
        ReusableMethods.waitOfVisibility(secondProduct);
        ReusableMethods.scrollIntoView(secondProduct);
        Logger4j.info("Hovered over the second product successfully.");
    }

    @When("Click view cart button.")
    public void click_view_cart_button() {
        Logger4j.info("Clicking the 'View Cart' button.");
        ReusableMethods.clickElement(productsPage.viewCartButton);
        Logger4j.info("'View Cart' button clicked, navigated to cart page.");
    }

    @Then("Verify both products are added to Cart.")
    public void verify_both_products_are_added_to_cart() {
        Logger4j.info("Starting verification that all expected products are present in the cart.");
        SoftAssert softAssert = new SoftAssert();

        List<String> actualCartProductNames = cartPage.getProductNames();

        for (String expectedProductNameInCart : productInCart) {
            Logger4j.info("Looking for " + expectedProductNameInCart + " in actual cart.");
            boolean isFound = false;
            String normalizedExpected = expectedProductNameInCart.trim().replaceAll("\\s+", " ");
            for (String actualProductName : actualCartProductNames) {
                String normalizedActual = actualProductName.trim().replaceAll("\\s+", " ");
                if (normalizedExpected.equalsIgnoreCase(normalizedActual)) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                Logger4j.error(expectedProductNameInCart + " was not found in the cart.");
                softAssert.fail("A search for " + expectedProductNameInCart + " was performed in the cart, but it was not found.");
            } else {
                Logger4j.info(expectedProductNameInCart + " was found in the cart as expected.");
            }
        }
        softAssert.assertAll();
        Logger4j.info("Finished verification of products presence in the cart.");
    }

    @Then("Verify their prices, quantity and total price.")
    public void verify_their_prices_quantity_and_total_price() {
        Logger4j.info("Starting verification of price, quantity and total price for each product in the cart.");
        SoftAssert softAssert = new SoftAssert();

        for (String productName : productInCart) {
            int index = cartPage.getRowIndexByProductName(productName);

            if (index == -1) {
                Logger4j.error(productName + " was not found in the cart, skipping price/quantity/total checks for it.");
                softAssert.fail(productName + " was not found in the cart.");
                continue;
            }

            int price = cartPage.getProductPriceAsInt(index);
            int quantity = cartPage.getProductQuantityAsInt(index);
            int total = cartPage.getProductTotalAsInt(index);

            Logger4j.info(productName + " - Price: " + price + ", Quantity: " + quantity + ", Total: " + total);

            if (price <= 0) {
                Logger4j.error(productName + " price is not greater than 0.");
            }
            softAssert.assertTrue(price > 0, productName + " price must be greater than 0.");

            if (quantity != 1) {
                Logger4j.error(productName + " quantity is not as expected.");
            }
            softAssert.assertEquals(quantity, 1, productName + " quantity is not as expected.");

            if (total != price * quantity) {
                Logger4j.error(productName + " total price is calculated incorrectly.");
            }
            softAssert.assertEquals(total, price * quantity, productName + " total price is calculated incorrectly.");
        }
        softAssert.assertAll();
        Logger4j.info("Finished verification of price, quantity and total price for all products.");
    }

    @And("Click add to cart button for the product at {int} index.")
    public void clickAddToCartButtonForTheProductAtIndex(int index) {
        String productName = productsPage.getProductNameFromProductsList(index).getText().trim().replaceAll("\\s+", " ");
        Logger4j.info("Adding product '" + productName + "' at index " + index + " to the cart.");
        productInCart.add(productName);
        ReusableMethods.clickElementWithoutScroll(productsPage.getProductAddToCartButtonByIndex(index));
        Logger4j.info("'Add to Cart' button clicked for '" + productName + "' at index " + index + ".");
    }
}