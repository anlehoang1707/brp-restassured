package brp.commons;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiKeyword {

    public static Response get(String path) {
        Response response = given(SpecBuilder.getRequestSpecBuilder()).when().get(path).then().spec(SpecBuilder.getResponseSpecBuilder()).extract().response();
        return response;
    }

    public static Response get(String path, Map<String, String> headers) {
        Response response =
                given(SpecBuilder.getRequestSpecBuilder().headers(headers)).
                        when().
                        get(path).
                        then().
                        spec(SpecBuilder.getResponseSpecBuilder()).
                        extract().
                        response();
        return response;
    }

    public static Response getWithQuery(String path, String paramName, String paramValue) {
        Response response =
                given(SpecBuilder.getRequestSpecBuilder()).queryParam(paramName,paramValue).
                        when().
                        get(path).
                        then().
                        spec(SpecBuilder.getResponseSpecBuilder()).
                        extract().
                        response();
        return response;
    }

    public static Response getWithQuery(String path, Map<String, String> queryParams) {
        RequestSpecification request = given(SpecBuilder.getRequestSpecBuilder());

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            request.queryParam(entry.getKey(), entry.getValue());
        }

        Response response =
                request.when()
                        .get(path)
                        .then()
                        .spec(SpecBuilder.getResponseSpecBuilder())
                        .extract()
                        .response();

        return response;
    }

    public static Response getNoAuth(String path) {
        Response response = given(SpecBuilder.getRequestSpecBuilderNoAuth()).when().get(path).then().spec(SpecBuilder.getResponseSpecBuilder()).extract().response();
        return response;
    }

    public static Response post(String path, Object payload) {
        Response response = given(SpecBuilder.getRequestSpecBuilder()).body(payload).when().post(path).then().spec(SpecBuilder.getResponseSpecBuilder()).extract().response();
        return response;
    }


    public static Response postNoAuth(String path, Object payload) {
        Response response = given(SpecBuilder.getRequestSpecBuilderNoAuth()).body(payload).when().post(path).then().spec(SpecBuilder.getResponseSpecBuilder()).extract().response();
        return response;
    }

    public static Response put(String path, Object payload) {
        Response response = given(SpecBuilder.getRequestSpecBuilder()).body(payload).when().put(path).then().extract().response();
        return response;
    }

    public static Response putNoAuth(String path, Object payload) {
        Response response = given(SpecBuilder.getRequestSpecBuilderNoAuth()).body(payload).when().put(path).then().extract().response();
        return response;
    }

    public static Response delete(String path, Object payload) {
        Response response = given(SpecBuilder.getRequestSpecBuilder()).body(payload).when().delete(path).then().extract().response();
        return response;
    }

    public static String getResponseKeyValue(Response response, String responseKey) {
        return response.jsonPath().get(responseKey).toString();
//        return response.getBody().path(responseKey);
    }

    public static String getResponseKeyValue(String responseBody, String responseKey) {
        JsonPath jsonPath = new JsonPath(responseBody);
        return jsonPath.get(responseKey).toString();
    }

    public static int getStatusCode(Response response) {
        return response.getStatusCode();
    }

    public static String getStatusLine(Response response) {
        return response.getStatusLine();
    }

    public static String getResponseHeader(Response response, String headerName) {
        return response.getHeader(headerName);
    }

    public static String getResponseContentType(Response response) {
        return response.getContentType();
    }

    public static String getResponseCookieName(Response response, String cookieName) {
        return response.getCookie(cookieName);
    }

}
