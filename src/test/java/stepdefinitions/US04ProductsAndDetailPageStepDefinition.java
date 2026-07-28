package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ProductDetailPage;
import pages.ProductsPage;
import utilities.Driver;
import utilities.Logger4j;
import utilities.ReusableMethods;

public class US04ProductsAndDetailPageStepDefinition {
    ProductsPage productsPage = new ProductsPage();
    ProductDetailPage productDetailPage = new ProductDetailPage();
    WebDriver driver = Driver.getDriver();

    @Then("Verify that user is navigated to All Products page successfully.")
    public void verify_that_user_is_navigated_to_all_products_page_successfully() {
        Logger4j.info("Verifying that user is navigated to All Products page successfully.");
        Assert.assertTrue(ReusableMethods.waitOfVisibility(productsPage.allProductsTitle).isDisplayed(), "The user was not redirected to the All Product Page! ERROR");
    }

    @Then("Verify that the products list is visible.")
    public void verify_that_the_products_list_is_visible() {
        Logger4j.info("Verifying that the products list is visible.");
        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(ReusableMethods.waitOfVisibility(productsPage.getProductFromProductsList(i)).isDisplayed());
        }
    }

    @When("Click on View Product button of first product.")
    public void click_on_view_product_button_of_first_product() {
        Logger4j.info("Clicking the View Product button of the first product.");
        ReusableMethods.clickElement(productsPage.viewProductButtonOfFirstProduct);
    }

    @Then("Verify that user is landed to product detail page.")
    public void verify_that_user_is_landed_to_product_detail_page() {
        Logger4j.info("Verifying that user is landed to product detail page.");
        String currentUrl = driver.getCurrentUrl();

        assert currentUrl != null;
        Assert.assertTrue(currentUrl.toLowerCase().contains("product_details"));
    }

    @Then("Verify that product name is visible.")
    public void verify_that_product_name_is_visible() {
        Logger4j.info("Verifying that product name is visible.");
        Assert.assertTrue(ReusableMethods.waitOfVisibility(productDetailPage.productName).isDisplayed());
    }

    @Then("Verify that category is visible.")
    public void verify_that_category_is_visible() {
        Logger4j.info("Verifying that category is visible.");
        Assert.assertTrue(ReusableMethods.waitOfVisibility(productDetailPage.productCategory).isDisplayed());
    }

    @Then("Verify that price is visible.")
    public void verify_that_price_is_visible() {
        Logger4j.info("Verifying that price is visible.");
        Assert.assertTrue(ReusableMethods.waitOfVisibility(productDetailPage.productPrice).isDisplayed());
    }

    @Then("Verify that availability is visible.")
    public void verify_that_availability_is_visible() {
        Logger4j.info("Verifying that availability is visible.");
        Assert.assertTrue(ReusableMethods.waitOfVisibility(productDetailPage.productAvailability).isDisplayed());
    }

    @Then("Verify that condition is visible.")
    public void verify_that_condition_is_visible() {
        Logger4j.info("Verifying that condition is visible.");
        Assert.assertTrue(ReusableMethods.waitOfVisibility(productDetailPage.productCondition).isDisplayed());
    }

    @Then("Verify that brand is visible.")
    public void verify_that_brand_is_visible() {
        Logger4j.info("Verifying that brand is visible.");
        Assert.assertTrue(ReusableMethods.waitOfVisibility(productDetailPage.productBrand).isDisplayed());
    }
}