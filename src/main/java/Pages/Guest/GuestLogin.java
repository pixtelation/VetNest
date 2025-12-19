package Pages.Guest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GuestLogin {


   public GuestLogin (WebDriver driver)
   {
    PageFactory.initElements(driver, this);
   }



@FindBy(xpath = "//input[@id='email']")
    WebElement email;

@FindBy(xpath = "//input[@id='password']")
WebElement password;

@FindBy(xpath = "//input[@id='password']")
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
