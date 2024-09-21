package pageObjects;

import driver.DriverFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Global_Vars;

import java.time.Duration;

public class Base_PO {
  public Base_PO() {
    PageFactory.initElements(getDriver(), this);
  }

  public WebDriver getDriver(){
    return DriverFactory.getDriver();
  }

  public void navigateTo_URL(String Url) {
    getDriver().get(Url);
  }

  public String generateRandomString(int len) {
    return RandomStringUtils.randomAlphabetic(len);
  }

  public String generateRandomNumber(int len) {
    return RandomStringUtils.randomNumeric(len);
  }

  public void sendKeys(By by,String textToType) {
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
    wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(textToType);
  }

  public void sendKeys(WebElement element,String textToType) {
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
    wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToType);
  }

  public void waitForElementAndClick(By by) {
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
    wait.until(ExpectedConditions.elementToBeClickable(by)).click();
  }

  public void waitFor(By by) {
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
  }

  public void waitFor(WebElement element) {
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  public void waitForElementAndClick(WebElement element) {
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
  }

  public void waitForAlert_And_ValidateText(String element) {
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Vars.DEFAULT_EXPLICIT_TIMEOUT));
    wait.until(ExpectedConditions.alertIsPresent());
    String alert_Message_Text = getDriver().switchTo().alert().getText();
    Assert.assertEquals(alert_Message_Text, element);
  }
}
