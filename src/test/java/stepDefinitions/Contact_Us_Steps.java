package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Base_PO;
import pageObjects.Contact_Us_PO;


public class Contact_Us_Steps extends Base_PO {
    private Contact_Us_PO contact_us_po;

    public Contact_Us_Steps(Contact_Us_PO contact_us_po) {
        this.contact_us_po = contact_us_po;
    }

    @Given("I access the web driver university contact us page")
    public void i_access_the_web_driver_university_contact_us_page() {
        contact_us_po.navigateTo_WebDriverUniversity_Contact_Us_Page();
    }

    @When("I enter a specific name {}")
    public void i_enter_a_specific_name(String name) {
        contact_us_po.setSpecific_FirstName(name);
    }

    @And("I enter a specific lastname {}")
    public void i_enter_a_specific_lastname(String lastname) {
        contact_us_po.setSpecific_LastName(lastname);
    }

    @And("I enter a specific email {}")
    public void i_enter_a_specific_email(String email) {
        contact_us_po.setSpecific_Email(email);
    }

    @And("I enter a comment {int}")
    public void i_enter_a_comment(Integer int1) {
        contact_us_po.setSpecific_Comment(int1);
    }

    @And("I click on button submit")
    public void i_click_on_button_submit() {
        contact_us_po.clickOn_Submit_Button();
    }

    @Then("I should be presented with contact us submission successful message")
    public void i_should_be_presented_with_contact_us_submission_successful_message() {
       contact_us_po.validate_Successful_SubmissionMessage_Text();
    }
}
