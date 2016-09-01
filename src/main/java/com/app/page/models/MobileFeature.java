package com.app.page.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MobileFeature {
	private WebDriver driver;

	@FindBy(css="span.price")
	WebElement priceBlock;
	
	@FindBy(css="button.btn-cart")
	WebElement btnCart; 
	
	public MobileFeature(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ShoppingCart clickOnAddToCart(){
		btnCart.click();
		return new ShoppingCart(driver);
	}
	
	
}
