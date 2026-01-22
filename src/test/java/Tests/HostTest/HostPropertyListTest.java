package Tests.HostTest;

import org.testng.annotations.Test;

import Base.Launch;
import Pages.Host.HostLogin;
import Pages.Host.HostPropertyListing;
import Utils.ConfigReader;

public class HostPropertyListTest extends Launch{

  


  @Test
  public void CreateProp()
  {
   HostPropertyListing HPL = new HostPropertyListing(getDriver());
   HPL.CreateProperty();
  }



}
