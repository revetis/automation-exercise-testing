package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ContactUsPage {
    public ContactUsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    WebDriver driver = Driver.getDriver();

    // --- Contact Form (Contact Us Page) ---

    public WebElement getHeaderText(String text){
        return driver.findElement(By.xpath("//h2[.='"+text+"']"));
    };

    @FindBy(id = "contact-us-form")
    public WebElement contactForm;

    @FindBy(xpath = "//input[@data-qa='name']")
    public WebElement nameInput;

    @FindBy(xpath = "//input[@data-qa='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//input[@data-qa='subject']")
    public WebElement subjectInput;

    @FindBy(xpath = "//textarea[@data-qa='message']")
    public WebElement textArea;

    @FindBy(name = "upload_file")
    public WebElement uploadFile;

    @FindBy(xpath = "//input[@data-qa='submit-button']")
    public WebElement submitButton;


    public WebElement getSuccessMessage(String text){
        return driver.findElement(By.xpath("(//div[.='"+text+"'])[1]"));
    };

    @FindBy(xpath = "//*[@id=\"form-section\"]/a")
    public WebElement homeButton;

}
