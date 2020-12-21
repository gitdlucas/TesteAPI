package TesteDBC;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

public class TesteRestAssured {

    private String url = "https://reqres.in/api/users";

    @Test
    public void getPageOneTest(){
        given().
                param("page", "1").
                when().
                get(url).
                then().
                statusCode(200).
                body("page", equalTo(1));
    }

    @Test
    public void getUserTest() {
        get(url + "/2").then().body("data.id", equalTo(2));
    }

    @Test
    public void postUserTest(){
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Rafael");
        requestParams.put("job", "QA");

        given().
                body(requestParams.toJSONString()).
                when().
                post(url).
                then().
                statusCode(201).
                body(containsString("createdAt"));
    }

    @Test
    public void putUserTest(){
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Rafael A.");
        requestParams.put("job", "QA/DEV");

        given().
                body(requestParams.toJSONString()).
                when().
                put(url + "/2").
                then().
                statusCode(200).
                body(containsString("updatedAt"));
    }

    @Test
    public void deleteUserTest(){
        when().
                delete(url + "/2").
                then()
                .statusCode(204);
    }
}