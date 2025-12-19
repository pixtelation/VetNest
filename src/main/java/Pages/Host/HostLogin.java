package Pages.Host;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HostLogin {

    public HostLogin(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "")
    WebElement email;

    @FindBy(xpath = "")
    WebElement password;

    @FindBy(xpath = "")
    WebElement loginbtn;


    public void EnterEmailfx(String useremail)
    {
       email.sendKeys(useremail); 
    }

    public void EnterPasswordfx(String userpassword)
    {
        password.sendKeys(userpassword);
    }

    public void Loginbtnfx()
    {
        loginbtn.click();
    }



}
