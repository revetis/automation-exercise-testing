package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.ProductsPage;
import utilities.Logger4j;
import utilities.ReusableMethods;

public class US05SearchProductStepDefinition {
    ProductsPage productsPage = new ProductsPage();

    @And("Enter {string} in search input.")
    public void enterInSearchInput(String searchText) {
        Logger4j.info("The text "+searchText+" is entered into the search box.");

        var input = productsPage.searchProductInput;
        ReusableMethods.clickElement(input);
        ReusableMethods.clearAndSendKeys(input, searchText);
    }

    @And("Click search button.")
    public void clickSearchButton() {
        Logger4j.info("Clicking the search button.");
        ReusableMethods.clickElement(productsPage.submitSearchButton);
    }


    @Then("Verify the {string} product and all the related products is visible.")
    public void verifyTheProductAndAllTheRelatedProductsIsVisible(String searchedProduct) {
        Logger4j.info("Verifying that the searched product related to the results");
        if (productsPage.allProductsList.size() < 2)Assert.fail("Product not found! ERROR");
        for (int i = 0; i < productsPage.allProductsList.size() - 1; i++) {
            Assert.assertTrue(
                    productsPage.getProductNameFromProductsList(i)
                            .getText()
                            .toLowerCase()
                            .contains(searchedProduct.toLowerCase()),"No product were found matching the "+searchedProduct+" text."
            );
        }
    }
}
