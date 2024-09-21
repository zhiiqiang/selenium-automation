package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import utils.Global_Vars;

public class Contact_Us_PO extends Base_PO {
  private @FindBy(how = How.XPATH, using = "//input[@name=\"first_name\"]")
  WebElement firstName_TextField;

  private @FindBy(name = "last_name")
  WebElement lastName_TextField;

  private @FindBy(xpath = "//input[@name=\"email\"]")
  WebElement email_TextField;

  private @FindBy(name = "message")
  WebElement message_TextField;

  private @FindBy(xpath = "//input[@value=\"SUBMIT\"]")
  WebElement submit_Button;

  private @FindBy(xpath = "//div[@id=\"contact_reply\"]/h1")
  WebElement successfulSubmission_MessageText;

  public Contact_Us_PO() {
    super();
  }

  public void navigateTo_WebDriverUniversity_Contact_Us_Page() {
    navigateTo_URL(Global_Vars.WEBDRIVER_UNIVERSITY_HOME_PAGE_URL + "/Contact-Us/contactus.html");
  }

  public void setSpecific_FirstName(String firstName) {
    sendKeys(firstName_TextField, firstName);
  }

  public void setSpecific_LastName(String lastName) {
    sendKeys(lastName_TextField, lastName);
  }

  public void setSpecific_Email(String email) {
    sendKeys(email_TextField, email);
  }

  public void setSpecific_Comment(int len) {
    sendKeys(message_TextField, generateRandomString(len) + generateRandomNumber(5));
  }

  public void clickOn_Submit_Button() {
    waitForElementAndClick(submit_Button);
  }

  public void validate_Successful_SubmissionMessage_Text() {
    waitFor(successfulSubmission_MessageText);
    Assert.assertEquals(successfulSubmission_MessageText.getText(), "Thank You for your Message!");
  }
}
