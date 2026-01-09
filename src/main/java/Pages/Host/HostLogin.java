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

    @FindBy(xpath = "//input[@id='email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='password']")
    WebElement password;

    @FindBy(xpath = "//button[normalize-space()='Login Now']")
    WebElement loginbtn;

    @FindBy(xpath = "//div[contains(text(), 'User not found')]")
    WebElement statusmsg;

    private String Actualtext = statusmsg.getText();


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

    public String getData()
    {
        return Actualtext;
    }




}
