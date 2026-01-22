package Pages.Host;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HostPropertyListing {

    public HostPropertyListing(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }


    @FindBy (xpath = "//button[normalize-space()='Create listing']")
    public WebElement Createlist;

    @FindBy (xpath = "(//input[@id='listingTitle'])[1]")
    public WebElement ListingTitle;






    public void CreateProperty()
    {
        Createlist.click();
    }
   



}
