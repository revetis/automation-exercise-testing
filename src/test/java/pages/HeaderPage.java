package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HeaderPage {

    public HeaderPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    public WebElement getHeaderButton(String buttonName){
        Driver.getDriver().findElement(By.xpath("//a[[contains(.,'"+buttonName+"']"));
    }
}
