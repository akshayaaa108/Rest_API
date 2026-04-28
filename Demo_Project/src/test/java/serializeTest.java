import Pojo.AddPlace;
import Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class serializeTest {

    @Test
    public void serialization(){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
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


        Response res = given().log().all().relaxedHTTPSValidation().queryParam("key","qaclick123")
                .body(p)
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract().response();
        String responseString = res.asString();
        System.out.println(responseString);



    }
}
