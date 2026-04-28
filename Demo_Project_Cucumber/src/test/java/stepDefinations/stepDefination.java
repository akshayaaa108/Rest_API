package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class stepDefination extends Utils {
    RequestSpecification reqBody;
    ResponseSpecification resspec;
    Response response;

    TestDataBuild data = new TestDataBuild();



    @Given("Add Place Payload {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
        reqBody = given().relaxedHTTPSValidation().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));

    }
    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        resspec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
        // Write code here that turns the phrase above into concrete actions
        response = reqBody
                .when().post("maps/api/place/add/json")
                .then().spec(resspec).extract().response();
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(false);
//        assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String ExpectedValue) {
        // Write code here that turns the phrase above into concrete actions
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        assertEquals(js.get(keyValue).toString(),ExpectedValue);
    }
}
