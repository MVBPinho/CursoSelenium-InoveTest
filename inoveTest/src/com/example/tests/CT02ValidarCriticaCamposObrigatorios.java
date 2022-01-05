package com.example.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CT02ValidarCriticaCamposObrigatorios {
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
	public void testCT02ValidarCriticaCamposObrigatorios() throws Exception {
		// Acessa a página de contato do site Inove Teste
		driver.get(baseUrl + "/contato");
		// Clica no botão Enviar sem preencher os campos
		driver.findElement(By.cssSelector("input.wpcf7-form-control.wpcf7-submit")).click();
		// Valida as críticas dos campos obrigatórios
		assertEquals(driver.findElement(By.cssSelector("span.wpcf7-not-valid-tip")).getText(),
				"O campo é obrigatório.");
		assertEquals(
				driver.findElement(By.cssSelector("span.wpcf7-form-control-wrap.your-email > span.wpcf7-not-valid-tip"))
						.getText(),
				"O campo é obrigatório.");
		assertEquals(driver
				.findElement(By.cssSelector("span.wpcf7-form-control-wrap.your-subject > span.wpcf7-not-valid-tip"))
				.getText(), "O campo é obrigatório.");
		assertEquals(driver
				.findElement(By.cssSelector("span.wpcf7-form-control-wrap.your-message > span.wpcf7-not-valid-tip"))
				.getText(), "O campo é obrigatório.");
		assertEquals(driver.findElement(By.xpath("//div[@id='wpcf7-f372-p24-o1']/form/div[2]")).getText(),
				"Um ou mais campos possuem um erro. Verifique e tente novamente.");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
