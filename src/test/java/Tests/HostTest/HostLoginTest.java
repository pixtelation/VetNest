package Tests.HostTest;

import org.testng.annotations.Test;

import Base.Launch;
import Pages.Host.HostLogin;
import Utils.ConfigReader;

public class HostLoginTest extends Launch{


    String email = ConfigReader.getProperty("HostEmail");
    String password = ConfigReader.getProperty("HostPass");

    HostLogin hl = new HostLogin(getDriver());



@Test
public void HostBlankLogin()
{
    hl.Loginbtnfx();
}



@Test
 public void HostInvalidLogin() //InvalidLogin
 {
    hl.EnterEmailfx("galat@yopmail.com");
    hl.EnterPasswordfx("JoBhiMainKehnaChahu");
 }



 @Test
  public void HostValidlogin() //Valid Login
  {
    hl.EnterEmailfx(email);
    hl.EnterPasswordfx(password);
    hl.Loginbtnfx();
  }



}
