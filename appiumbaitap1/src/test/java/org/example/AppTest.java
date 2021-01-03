package org.example;

import static org.junit.Assert.assertTrue;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;

    @Before
    public void initTest() throws MalformedURLException {
        //Khoi tao driver
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("deviceName", "5200ea67f4b9c425");
        capabilities.setCapability("app", "C:\\Users\\admin\\Downloads\\Calculator_v7.8.apk");

        driver = new AppiumDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {


        String phepTinh = "123+456";

        MobileElement congOperator = (MobileElement) this.driver.findElement(By.id("com.google.android.calculator:id/op_add"));
        for (int i = 0; i < phepTinh.length(); i++) {
            if (phepTinh.charAt(i) == '+') {
                congOperator.click();
                continue;
            }
            else {
                MobileElement numberElement = (MobileElement) this.driver.findElement(By.id("com.google.android.calculator:id/digit_" + phepTinh.charAt(i)));
                numberElement.click();
                System.out.println(numberElement);
            }
        }
        //Verify
        MobileElement resultField = (MobileElement) this.driver.findElement(By.id("com.google.android.calculator:id/result_preview"));
        Assert.assertEquals("579", resultField.getText());
    }
}
