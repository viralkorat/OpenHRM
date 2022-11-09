package com.OpenHRM;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

	WebDriver driver;

	@BeforeClass
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		login("Admin", "admin123");
	}

	@Test(priority=1)
	public void admin() {
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[1]/a[1]")).click();
	}
	
	@Test(priority=2)
	public void job () {
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[2]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Job Titles')]")).click();
		
	}
	
	@Test(priority=3)
	public void addButton() {
	driver.findElement(By.xpath("//*[@class='oxd-button oxd-button--medium oxd-button--secondary']")).click();	
	}

	@Test(priority=4)
	public void addJobTitle() {
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/input[1]")).sendKeys("John");
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/textarea[1]")).sendKeys("Engineer");
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[4]/div[1]/div[2]/textarea[1]")).sendKeys("Electrical Engineer");
		driver.findElement(By.xpath("//*[@type='submit']")).submit();
	}
	
	/*@Test(priority=5)
	public void selectAll() {
	driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/span[1]")).click();	
	}
	*/
	
	/*@Test(priority=6)
	public void declineDeleteAll() {
	WebElement deleteAll=driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]"));	
	deleteAll.click();
	String window= driver.getWindowHandle();
	driver.switchTo().window(window);
	driver.findElement(By.xpath("//body/div[@id='app']/div[3]/div[1]/div[1]/div[1]/div[3]/button[1]")).click();
	Delete all button is below
	driver.findElement(By.xpath("//body/div[@id='app']/div[3]/div[1]/div[1]/div[1]/div[3]/button[2]")).click();
	}*/
	
	@Test(priority=7)
	public void deleteSingle() throws InterruptedException {
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[2]/div[14]/div[1]/div[4]/div[1]/button[1]")).click();
		String window= driver.getWindowHandle();
		driver.switchTo().window(window);
		driver.findElement(By.xpath("//body/div[@id='app']/div[3]/div[1]/div[1]/div[1]/div[3]/button[2]")).click();
		Thread.sleep(3000);
	}
	@Test(priority=8)
	public void sorting() {
		WebElement sort =driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/i[1]"));
		Actions act =  new Actions(driver);
		act.moveToElement(sort).click().perform();
		String window2= driver.getWindowHandle();
		driver.switchTo().window(window2);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[2]")).click();
	}
	@Test(priority=9)
	public void edit() throws AWTException {
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[4]/div[1]/button[2]")).click();
		WebElement jobTitle =driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/input[1]"));
		jobTitle.click();
		
		Robot rb = new Robot();
		rb.delay(2000);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_A);
		rb.keyPress(KeyEvent.VK_BACK_SPACE);
		jobTitle.sendKeys("CEO");
		driver.findElement(By.xpath("//*[@type='submit']")).submit();
	}
	
	public void login(String username, String password) {
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@type='submit']")).click();
	}
	
	/*@AfterClass
	public void quite() {
		driver.quit();
	}
	*/
}
