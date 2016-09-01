/**
 * 
 */
package com.app.page.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.util.PageUtils;

/**
 * @author deepakkumar
 *
 */
public class ShoppingCart extends PageUtils {
	
	private WebDriver driver;
	
	private static String UPDATE_MSG = "Some of the products cannot be ordered in requested quantity.";
	private static String SHOPPIN_CART_ERROR_MSG="* The maximum quantity allowed for purchase is 500.";
	private static String CART_EMPTY_MSG = "SHOPPING CART IS EMPTY";
	
	@FindBy(xpath="//*[@title='Qty']")
	WebElement qtyField;

	@FindBy(name="update_cart_action")
	WebElement updateBtn;
	
	@FindBy(xpath=".//*[@id='top']/body/div[1]/div/div[2]/div/div/div/ul/li/ul/li/span")
	WebElement spanTxt;
	
	@FindBy(css="p.item-msg")
	WebElement errorMsgElement;
	
	@FindBy(id="empty_cart_button")
	WebElement emptyCrtBtn;
	
	@FindBy(tagName="h1")
	WebElement cartEmptyEle;
	
	@FindBy(linkText="MOBILE")
	WebElement mobileLnk;
	
	public ShoppingCart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void updateItemQuantity(){
		qtyField.sendKeys("1000");
	}
	
	public void submitUpdateAndVerify(){
		updateBtn.submit();
		Assert.assertEquals(spanTxt.getText(), UPDATE_MSG);
		Assert.assertEquals(errorMsgElement.getText(), SHOPPIN_CART_ERROR_MSG);
	}
	
	public WebElement quantityTextField(){
		return qtyField;
	}
		
	public ShoppingCart emptyCart(){
		emptyCrtBtn.click();
		return new ShoppingCart(driver);
	}
	
	public void verifyEmptyCartMsg(){
		Assert.assertEquals(cartEmptyEle.getText(), CART_EMPTY_MSG);
	}
	
	public MobilePage clickOnMobileLink(){
		mobileLnk.click();
		return new MobilePage(driver);
	}
	
	
}
