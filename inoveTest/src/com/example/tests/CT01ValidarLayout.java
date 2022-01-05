package com.example.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CT01ValidarLayout {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private String pathChromeDriver = "C:\\RPA\\chromedriver.exe";

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", pathChromeDriver);
		driver = new ChromeDriver();
		baseUrl = "https://livros.inoveteste.com.br/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCT01ValidarLayout() throws Exception {
		// Acessa a página de contato do site Inove Teste
		driver.get(baseUrl + "/contato");
		// Valida os layout do formulário de contato
		assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Envie uma mensagem");
		assertTrue(isElementPresent(By.name("your-name")));
		assertTrue(isElementPresent(By.name("your-email")));
		assertTrue(isElementPresent(By.name("your-subject")));
		assertTrue(isElementPresent(By.name("your-message")));
		assertTrue(isElementPresent(By.cssSelector("input.wpcf7-form-control.wpcf7-submit")));
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}