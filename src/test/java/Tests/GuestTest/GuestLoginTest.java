package Tests.GuestTest;

import org.testng.annotations.Test;

import Base.Launch;
import Pages.Guest.GuestLogin;
import Utils.ConfigReader;

public class GuestLoginTest extends Launch{

    

    String email = ConfigReader.getProperty("GuestEmail");
    String password = ConfigReader.getProperty("GuestPass");


    @Test
    public void GuestBlankLogin()
    {
        GuestLogin gl = new GuestLogin(getDriver());
        gl.MenuSignIn();
        gl.Submitbtnfx();
    }


    @Test
    public void GuestInvalidLogin()
    {
         GuestLogin gl = new GuestLogin(getDriver());
         gl.MenuSignIn();
        gl.EnterEmailfx("sdfv@sdf.com");
        gl.EnterPasswordfx("dsfsdf");
        gl.Submitbtnfx();
    }


    @Test
    public void GuestValidLogin()
    {
         GuestLogin gl = new GuestLogin(getDriver());
         gl.MenuSignIn();
         gl.EnterEmailfx(email);
        gl.EnterPasswordfx(password);
        gl.Submitbtnfx();
        gl.pasteStaticOTP();
        gl.OTPSubmitbtnfx();
    }


}
