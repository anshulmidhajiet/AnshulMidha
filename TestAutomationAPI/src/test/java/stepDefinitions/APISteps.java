package stepDefinitions;

import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APISteps {

    private Response response;
    
    @Given("I send a GET request to {string}")
    public void i_send_a_get_request_to(String url) {
        response = RestAssured.get(url);
    }
    
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        Assert.assertEquals("status code is not expected!", expectedStatusCode, response.getStatusCode());
    }
    
    @Then("the response should contain the BPIs {string}, {string}, and {string}")
    public void the_response_should_contain_the_bpis(String bpi1, String bpi2, String bpi3) {
    	String responseBody = response.getBody().asString();

    	Assert.assertTrue(responseBody.contains("\"USD\""));
        Assert.assertTrue(responseBody.contains("\"GBP\""));
        Assert.assertTrue(responseBody.contains("\"EUR\""));

    }
    
    @Then("the {string} description should be {string}")
    public void the_description_should_be(String bpi, String expectedDescription) {
        String actualDescription = response.jsonPath().getString("bpi." + bpi + ".description");
        Assert.assertEquals("description for " + bpi+ "is not expected", expectedDescription, actualDescription);
    }
}
