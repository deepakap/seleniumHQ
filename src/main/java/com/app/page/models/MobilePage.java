package com.app.page.models;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.app.baseLogic.SortLogic;
import com.app.manager.WebDrivermanager;

public class MobilePage extends WebDrivermanager {
	
	private WebDriver driver;
	
	@FindBy(tagName="select")
	WebElement select;
	
	@FindBy(css="address.copyright")
	WebElement copyrightAdd;
	
	@FindBy(css="ul.products-grid--max-4-col")
	WebElement itemUL;
	
	@FindBy(css="span#product-price-1")
	WebElement sonyXperiaPrice;
	
	@FindBy(xpath=".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/h2/a")
	WebElement sonyMobileFeature;
	
	private List <String> strList = new ArrayList<String>();
	
	public MobilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyTitle(String title){
		Assert.assertEquals(driver.getTitle(), title);
	}
	
	public MobilePage sortItemsByName(){
		Select dropDown = new Select(select);
		dropDown.selectByVisibleText("Name");
		return new MobilePage(driver);
	}
	
	public void verifyItemsDisplayedByName(){
		waitExplicitDuration(driver, copyrightAdd);
		List<WebElement> list = itemUL.findElements(By.tagName("a"));
		  strList = new ArrayList<String>();
		  for(WebElement elem : list){
			  if(elem.getText()!="" && !elem.getText().isEmpty() && (!elem.getText().equalsIgnoreCase("Add to Wishlist") && !elem.getText().equalsIgnoreCase("Add to Compare")) ){
				  strList.add(elem.getText());
			  }
		  }
		  Boolean flag = new SortLogic().verifyItemsSortOrder((ArrayList<String>) strList);
		  Assert.assertTrue(flag, "Listed Items are sorted by Name !");
	}
	
	public MobileFeature checkSonyExperiaPrice(){
		String value =  sonyXperiaPrice.getText();
		String mobile = sonyMobileFeature.getText();
		sonyMobileFeature.click();
		System.out.println( mobile+":"+value);
		MobileFeature features = new MobileFeature(driver);
		Assert.assertEquals(value, features.priceBlock.getText());
		return features;
	}
	
	
	
}
