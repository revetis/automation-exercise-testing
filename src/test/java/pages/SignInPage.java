package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SignInPage {

    public SignInPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    
}
