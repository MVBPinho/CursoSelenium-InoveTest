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

public class CT03EnviarMensagem {
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
	public void testCT03EnviarMensagem() throws Exception {
		// Acessa a página de contato do site Inove Teste
		driver.get(baseUrl + "/contato");
		// Preenche todos os campos do formulário de contato
		driver.findElement(By.name("your-name")).clear();
		driver.findElement(By.name("your-name")).sendKeys("Hugo Peres");
		driver.findElement(By.name("your-email")).clear();
		driver.findElement(By.name("your-email")).sendKeys("contato@inoveteste.com.br");
		driver.findElement(By.name("your-subject")).clear();
		driver.findElement(By.name("your-subject")).sendKeys("Curso Gratuito de Selenium");
		driver.findElement(By.name("your-message")).clear();
		driver.findElement(By.name("your-message")).sendKeys("Quais são os módulos desse curso?");
		// Clica no botão Enviar
		driver.findElement(By.cssSelector("input.wpcf7-form-control.wpcf7-submit")).click();
		// Valida a mensagem de sucesso do envio
		assertEquals(driver.findElement(By.xpath("//div[@id='wpcf7-f372-p24-o1']/form/div[2]")).getText(),
				"Agradecemos a sua mensagem. Responderemos em breve.");
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