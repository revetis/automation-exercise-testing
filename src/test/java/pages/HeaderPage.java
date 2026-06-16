package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HeaderPage {

    public HeaderPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    public WebElement getHeaderButton(String buttonName){
        return Driver.getDriver().findElement(By.xpath("//a[text()[normalize-space()='"+buttonName+"']]"));
    }


}
