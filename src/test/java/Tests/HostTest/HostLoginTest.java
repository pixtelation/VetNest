package Tests.HostTest;

import org.testng.annotations.Test;

import org.testng.Assert;

import Base.Launch;
import Pages.Host.HostLogin;
import Utils.ConfigReader;

public class HostLoginTest extends Launch{

  HostLogin hl = new HostLogin(getDriver());

    String email = ConfigReader.getProperty("HostEmail");
    String password = ConfigReader.getProperty("HostPass");



@Test
public void HostBlankLogin()
{
  HostLogin hl = new HostLogin(getDriver());
    hl.Loginbtnfx();
}



@Test
 public void HostInvalidLogin() //InvalidLogin
 {
  HostLogin hl = new HostLogin(getDriver());
    hl.EnterEmailfx("galat@yopmail.com");
    hl.EnterPasswordfx("12345678@Ab");
    hl.Loginbtnfx();

    String Statusmsg = hl.statusmsg.getText();
    System.out.println(Statusmsg);
   // Assert.assertEquals(Statusmsg,"User not found");
 }



 @Test
  public void HostValidlogin() //Valid Login
  {
    HostLogin hl = new HostLogin(getDriver());
    hl.EnterEmailfx(email);
    hl.EnterPasswordfx(password);
    hl.Loginbtnfx();
    hl.SendOtp();
    hl.Verifybtn();
  }



}
