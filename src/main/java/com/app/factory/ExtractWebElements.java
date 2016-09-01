package com.app.factory;

import org.openqa.selenium.WebElement;

public abstract class ExtractWebElements {
	
	public abstract WebElement findByID();
	
	public abstract WebElement findByCSS();
	
	public abstract WebElement findByName();
	
	public abstract WebElement findByXpath();
	
	public abstract WebElement findByCssSelector();
	
}
