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

@FindBy(xpath = "//button[@type='submit']")
WebElement submitbtn;

@FindBy(xpath = "(//span[normalize-space()='Sign In'])[1]")
WebElement menuSignIN;

@FindBy(xpath = "//input[@value and @name='otp.0']")
WebElement Otp1;

@FindBy(xpath = "//span[normalize-space()='Submit']")
WebElement otpsubmitbtn1;



public void MenuSignIn()
{
 menuSignIN.click();
}


public void EnterEmailfx(String useremail)
{
  email.sendKeys(useremail);
}

public void EnterPasswordfx(String userpassword)
{
    password.sendKeys(userpassword);
}

public void Submitbtnfx()
{
 submitbtn.click();
}


public void pasteStaticOTP()
{
  Otp1.sendKeys("123456");
}

public void OTPSubmitbtnfx()
{
 otpsubmitbtn1.click();
}







}
