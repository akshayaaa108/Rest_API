import Files.ReUsableMethods;
import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.jar.JarEntry;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class basics {
    public static void main(String[] args){
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Add Place
        String response = given().relaxedHTTPSValidation()
                .log().all().queryParam("key","qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope",equalTo("APP")).extract().response().asString();

        System.out.println(response);
        JsonPath js = ReUsableMethods.rewToJson(response);
        String placeId=js.getString("place_id");
        System.out.println(placeId);
        //update Place
        String newAddress = "Madhukar colony more";
        given().relaxedHTTPSValidation()
                .queryParam("key","qaclick123")
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200)
                .body("msg",equalTo("Address successfully updated"));

        //Get place
        String getPlaceResponse = given().relaxedHTTPSValidation()
                .queryParam("key","qaclick123")
                        .queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                        .then().statusCode(200).extract().response().asString();
        System.out.println(getPlaceResponse);
        JsonPath js1 = ReUsableMethods.rewToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress,newAddress);


    }
}
