package com.mayab.calidad.crossbrowsing;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class crossBrowsing {
	private static void pause(long mils) {
		  try {
			  Thread.sleep(mils);
		  }catch(Exception e){
			  e.printStackTrace();
		  }
	  }
	final static String sauceUserName = System.getenv("cheke_98");
    final static String sauceAccessKey = System.getenv("c1662400-c6af-436f-ba12-f08537c9d320");
    final static String sauceURL = "https://cheke_98:c1662400-c6af-436f-ba12-f08537c9d320@ondemand.saucelabs.com:443/wd/hub";
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("username", sauceUserName);
        capabilities.setCapability("accessKey", sauceAccessKey);
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("version", "70.0");
        capabilities.setCapability("build", "Onboarding Sample App - Java-Junit5");
        capabilities.setCapability("name", "80-cross-browser");
       WebDriver driver = new RemoteWebDriver(new URL(sauceURL), capabilities);
       driver.get("https://mern-crud.herokuapp.com");
       WebElement element = driver.findElement(By.xpath("/html/body/div/div/div[2]/button"));
		//Hacemos click sobre él
		element.click();
		//Ahora llenaremos los valores del formulario
		String name = "Ezequiel";
		String email = "ezequiel@prubea.com";
		String age = "20";
		//Escribimos sobre el formulario
		element = driver.findElement(By.name("name"));
		element.sendKeys(name);
		element = driver.findElement(By.name("email"));
		element.sendKeys(email);
		element = driver.findElement(By.name("age"));
		element.sendKeys(age);
		//para el genero, debemos hacer click en el campo, ya que es un "combo box"
		element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div"));
		element.click();
		//Una vez que hacemos click, seleccionamos el genero male
		element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[1]"));
		element.click();
		//Despues hacemos click en agregar
		element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
		element.click();
		//esperamos 6 segundos para que aparezca el mensaje de exito
		pause(6000);
				//Ahora comprobamos que haya funcionado
		String expected = "Successfully added!";
		String gotMessage="";
		WebElement result = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p"));
		gotMessage = result.getText();
		assertThat(expected,is(equalTo(gotMessage)));
		boolean finalResult = false;
		if(gotMessage.equals("Successfully added!")) {
			finalResult = true;
		}else {
			finalResult = false;
		}
		if (finalResult){
		      ((JavascriptExecutor)driver).executeScript("sauce:job-result=passed");
		    }
		    else {
		      ((JavascriptExecutor)driver).executeScript("sauce:job-result=failed");
		    }
		driver.quit();
	}

}
