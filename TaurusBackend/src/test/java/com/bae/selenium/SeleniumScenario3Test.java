// package com.bae.selenium;

// import static org.assertj.core.api.Assertions.assertThat;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;
// import org.openqa.selenium.remote.RemoteWebDriver;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;

// public class SeleniumScenario3Test {

// 	private RemoteWebDriver driver;

// 	@BeforeEach
// 	void setup() {
// 		ChromeOptions options = new ChromeOptions();
// 		options.setHeadless(true);
// 		this.driver = new ChromeDriver(options);
// 		this.driver.manage().window().maximize();
// 	}

// 	@Test
// 	void testSubmit() {
// 		WebDriverWait explicitWait = new WebDriverWait(driver, 15);

// 		this.driver.get("http://localhost/reg-search");

// 		WebElement name = this.driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/form/div/input"));

// 		name.sendKeys("AI51 EYW");

// 		WebElement submit = this.driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/form/div/button"));

// 		submit.click();

// 		WebElement click = this.driver.findElement(By.xpath("/html/body/div/div/div/div[2]/nav/a[1]"));

// 		click.click();

// 		WebElement reg = explicitWait.until(ExpectedConditions
// 				.elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/div[1]/div[1]/span/h2")));

// 		assertThat(reg.getText()).isEqualTo("AI51 EYW");
// 	}

// 	@AfterEach
// 	void tearDown() {
// 		this.driver.close();
// 	}

// }
//
