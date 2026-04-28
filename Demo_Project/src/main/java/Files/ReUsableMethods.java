package Files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {
    public static JsonPath rewToJson(String response){
        JsonPath js = new JsonPath(response);
        return  js;
    }
}
