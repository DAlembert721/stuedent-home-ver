package com.acme.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class CreatePropertySteps {

    final static String ROOT_URI = "http://localhost:8080/api";
    Response response;
    RequestSpecification httpRequest = RestAssured.given();

    @Given("a landlord in the register property's view")
    public void aLandlordInTheRegisterPropertySView() {
    }



    @When("the landlord clicks the register button")
    public void theLandlordClicksTheRegisterButton() {


    }

    @And("make a post request to {string}")
    public void makeAPostRequestTo(String url) {
        response = httpRequest.post(ROOT_URI + url);
    }

    @Then("the system promotes the ad of the property")
    public void theSystemPromotesTheAdOfTheProperty() {
    }

    @And("the result received has a status code of {int}")
    public void theResultReceivedHasAStatusCodeOf(int code) {
        Assert.assertEquals(code,response.getStatusCode());
        System.out.println("The body of post: ->  " + response.getBody().asString());
    }



    @Then("the system asks to correct the wrong data")
    public void theSystemAsksToCorrectTheWrongData() {
    }

    @And("the information entered is correct")
    public void theInformationEnteredIsCorrect() {

        httpRequest.contentType(ContentType.JSON).body();
    }

    @And("the information entered is incorrect")
    public void theInformationEnteredIsIncorrect() {
        httpRequest.contentType(ContentType.JSON).body();
    }
}
