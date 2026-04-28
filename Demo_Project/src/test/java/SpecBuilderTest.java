import Pojo.AddPlace;
import Pojo.GetPlace;
import Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {
    @Test
    public void serialization(){
//        RestAssured.baseURI = "https://rahulshettyacademy.com";
        // Serialization : Java object to Json
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setName("Frontline house");
        p.setPhone_number("(+91) 983 893 3937");
        p.setAddress("29, side layout, cohen 09");
        p.setWebsite("http://google.com");
        p.setLanguage("French-IN");

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);

        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);

        p.setLocation(l);

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        RequestSpecification reqBody = given().relaxedHTTPSValidation().spec(req).body(p);

        Response res = reqBody
                .when().post("maps/api/place/add/json")
                .then().spec(resspec).extract().response();

        // deserialization : Json to Java object.
        String responseString = res.asString();
        System.out.println(responseString);
        JsonPath js = new JsonPath(responseString);
        String placeId = js.getString("place_id");
        System.out.println(placeId);
        GetPlace  gp =
                given()
                        .relaxedHTTPSValidation()
                        .spec(req)
                        .queryParam("place_id", placeId)
                        .when()
                        .get("/maps/api/place/get/json")
                        .then()
                        .spec(resspec)
                        .extract()
                        .as(GetPlace.class);
        System.out.println(gp.getWebsite());



    }
}
