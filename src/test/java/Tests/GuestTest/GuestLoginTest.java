package Tests.GuestTest;

import org.testng.annotations.Test;

import Base.Launch;
import Pages.Guest.GuestLogin;
import Utils.ConfigReader;

public class GuestLoginTest extends Launch{

    GuestLogin gl = new GuestLogin(getDriver());

    String email = ConfigReader.getProperty("GuestEmail");
    String password = ConfigReader.getProperty("GuestPass");


    @Test
    public void GuestBlankLogin()
    {
        gl.Loginbtnfx();
    }


    @Test
    public void GuestInvalidLogin()
    {
        gl.EnterEmailfx("sdfv@sdf.com");
        gl.EnterPasswordfx("dsfsdf");
        gl.Loginbtnfx();
    }


    @Test
    public void GuestValidLogin()
    {
         gl.EnterEmailfx(email);
        gl.EnterPasswordfx(password);
        gl.Loginbtnfx();
    }


}
