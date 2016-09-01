package com.app.automation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.app.baseLogic.SortLogic;
import com.app.manager.WebDrivermanager;
import com.app.page.models.HomePage;
import com.app.page.models.MobileFeature;
import com.app.page.models.MobilePage;
import com.app.page.models.ShoppingCart;

public class WebPageAutomation extends WebDrivermanager {
   private WebDriver driver;
   private String url = "http://live.guru99.com/index.php/";
   private HomePage homePge;
   private MobilePage mobilePage;
   private MobileFeature mblFeature;
   private ShoppingCart shpCart;
   
   
   @BeforeTest
   public void beforeTest() {
	 driver = new WebDrivermanager().setEnvironment("Firefox");
	 driver.get(url);
	 driver.manage().window().maximize();
  }
	
  @Test(priority=0, enabled=true)
  public void launchWebApp() throws InterruptedException {
	  homePge = new HomePage(driver);
	  homePge.loadWebPageToMaximise(url);
	  homePge.verifyTitle("Home page");
	  WebElement ele = homePge.findAddressTag();
	  waitExplicitDuration(driver, ele);
	  homePge.veifyAddress();
	  mobilePage = homePge.clickOnMobileLink(driver);
	  waitImplicitDuration(driver);
  }
  
  @Test(priority=1, enabled=true)
  public void testMobilePage(){
	  mobilePage.verifyTitle("Mobile");
	  mobilePage.sortItemsByName();
  }
  
  @Test(priority=2, enabled=true)
  public void verifyItemSortByName(){
	  WebElement webEle = driver.findElement(By.className("copyright"));
	  waitExplicitDuration(driver, webEle);
	  mobilePage.verifyItemsDisplayedByName();
	  mobilePage.verifyTitle("Mobile");
  }
  
  @Test(priority=3, enabled=true)
  public void compareItemPrice(){
	  mblFeature = mobilePage.checkSonyExperiaPrice();
  }
  
  @Test(priority=4, enabled=true)
  public void verifyAddCartItems(){
	  shpCart = mblFeature.clickOnAddToCart();
	  waitExplicitDuration(driver, shpCart.quantityTextField());
	  shpCart.updateItemQuantity();
	  shpCart.submitUpdateAndVerify();
	  shpCart.emptyCart().verifyEmptyCartMsg();
	  mobilePage = shpCart.clickOnMobileLink();
	}
  
  @Test(priority=5, enabled=false)
  public void verifyCartItems(){
	 driver.findElement(By.linkText("Mobile".toUpperCase())).click();
	 WebElement wele = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/ul/li[2]/a"));
	 waitExplicitDuration(driver, wele);
	 ArrayList<String> msgList = new ArrayList<String>();
	 msgList.add("The product IPhone has been added to comparison list.");
	 msgList.add("");
	 msgList.add("The product Sony Xperia has been added to comparison list.");
	 driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/ul/li[2]/a")).click();
	 String strTxt = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/ul/li/ul/li/span")).getText().trim();
	 Assert.assertEquals(msgList.get(0).toString(), strTxt);
	 driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/ul/li[2]/a")).click();	
	 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }
  
  @Test(priority=6, enabled=false)
  public void verifyItemsOnCart(){
	  String phone1= "IPHONE";
	  String phone2= "SONY XPERIA";
	  Actions actions = new Actions(driver);
	  WebElement spanEle = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[3]/div[2]/div[2]/div/button/span/span"));
	  //actions.moveToElement(spanEle).perform();
	  actions.moveToElement(spanEle).click(spanEle).build().perform();
	  waitImplicitDuration(driver);
	  Set<String> eleSet = driver.getWindowHandles();
	  Iterator<String> itr = eleSet.iterator();
	 
	  String parentWindow = itr.next();
	  String childWindow = itr.next();
	  driver.switchTo().window(childWindow);
	  
	  String popupMobile1 = driver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText();
	  String popupMobile2 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText(); 
	  
	  Assert.assertEquals(popupMobile1, phone1);
	  Assert.assertEquals(popupMobile2, phone2);
	  driver.findElement(By.xpath("//button[@title='Close Window']")).click();
	  driver.switchTo().window(parentWindow);
	  driver.navigate().refresh();
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }
  
  @Test(priority=7, enabled=false)
  public void registerUserLogin(){
	  String link = "REGISTER";
	  driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a/span[2]")).click();
	  WebElement ele = driver.findElement(By.id("header-account"));
	  
	  List<WebElement> list = ele.findElements(By.tagName("li"));
	  Iterator<WebElement> itr = list.iterator();
	  while(itr.hasNext()){
		  WebElement regEle = itr.next();
		  if(regEle.getText().equalsIgnoreCase(link)){
			  regEle.click();
			  break;
		  }
	  }
	  String title = driver.getTitle().toString();
	  String expString = "CREATE NEW CUSTOMER ACCOUNT";
	  Assert.assertEquals(title.toUpperCase(),expString);
	  
	  driver.findElement(By.id("firstname")).sendKeys("Niss5ss");
	  driver.findElement(By.id("lastname")).sendKeys("altimak");
	  driver.findElement(By.id("email_address")).sendKeys("niss5ss.altimaX@gmail.com");
	  driver.findElement(By.id("password")).sendKeys("welcome");
	  driver.findElement(By.id("confirmation")).sendKeys("welcome");
	  driver.findElement(By.id("is_subscribed")).click();
	  driver.findElement(By.xpath(".//*[@id='form-validate']/div[2]/button")).submit();
	  
  }
  
  @Test(priority=8, enabled=false)
  public void verifyRegisteration(){
	  String regTxt = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div/div/ul/li/ul/li/span")).getText();
      Assert.assertEquals(regTxt, "Thank you for registering with Main Website Store.");
  }
  
  @Test(priority=9, enabled=false)
  public void addToWishList(){
	  driver.findElement(By.linkText("TV")).click();
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  driver.findElement(By.linkText("Add to Wishlist")).click();
	  String title = "My Wishlist";
	  Assert.assertEquals(driver.getTitle().toString(), title);
	  driver.findElement(By.xpath(".//*[@id='wishlist-view-form']/div/div/button[1]")).click();
	  WebElement shareBtn = driver.findElement(By.xpath(".//*[@id='form-validate']/div[2]/button"));
	  waitExplicitDuration(driver, shareBtn);
	  driver.findElement(By.id("email_address")).sendKeys("nissan_new.altima@gmail.com");
	  driver.findElement(By.id("message")).sendKeys("This is Test message from automation user");
	  shareBtn.click();
	  String shareTxt = "Your Wishlist has been shared.";
	  WebElement shareEle = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span"));
	  Assert.assertEquals(shareEle.getText(), shareTxt);
  }
  
  @Test(priority=10, enabled=false)
  public void performItemCheckout(){
	  driver.findElement(By.cssSelector("button.btn-cart")).click();
	  driver.findElement(By.cssSelector("button.btn-proceed-checkout")).click();
	  Assert.assertEquals(driver.getTitle(), "Checkout");
	  driver.findElement(By.id("billing:street1")).sendKeys("350 Santa Clara");
	  driver.findElement(By.id("billing:city")).sendKeys("Sunnyvale");
	  Select dropDown = new Select(driver.findElement(By.id("billing:region_id")));
	  dropDown.selectByVisibleText("California");
	  driver.findElement(By.id("billing:postcode")).sendKeys("94087");
	  driver.findElement(By.id("billing:telephone")).sendKeys("4082341654");
	  driver.findElement(By.id("billing:use_for_shipping_yes")).click();
	  waitImplicitDuration(driver);
	  driver.findElement(By.cssSelector("button.button[title='Continue']")).click();
	  waitImplicitDuration(driver);  
	  driver.findElement(By.cssSelector("button.validation-passed")).click();
	  driver.findElement(By.xpath(".//*[@id='shipping-method-buttons-container']/button")).submit();
	  waitImplicitDuration(driver);
	  //WebElement radioEle = driver.findElement(By.cssSelector("input#p_method_checkmo"));
      //waitExplicitDuration(driver, radioEle);
      //radioEle.click();
      driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/ol/li[3]/div[2]/form/div[3]/button")).click();
      waitImplicitDuration(driver);
      WebElement elm = driver.findElement(By.id("p_method_checkmo"));
      waitExplicitDuration(driver, elm);
      elm.click();
      driver.findElement(By.xpath(".//*[@id='payment-buttons-container']/button")).click();
      WebElement btn = driver.findElement(By.cssSelector("button.btn-checkout"));
      waitExplicitDuration(driver, btn);
      btn.click();
      //driver.findElement(By.cssSelector("button.validation-passed")).click();
      //driver.findElement(By.cssSelector("button.btn-checkout")).submit();
      WebElement eleText = driver.findElement(By.cssSelector("h2.sub-title"));
      waitExplicitDuration(driver, eleText);
      Assert.assertEquals(eleText.getText(), "Thank you for your purchase!".toUpperCase());
   }
  
  @AfterTest
	public void destroyEnvironnment(){
		driver.quit();
	}

}
