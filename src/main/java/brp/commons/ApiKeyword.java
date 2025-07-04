package brp.commons;

import brp.utils.LogUtils;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.logging.Log;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiKeyword {

    @Step("GET request to {path}")
    public static Response get(String path) {
        Response response = given(SpecBuilder.getRequestSpecBuilder()).when().get(path).then().spec(SpecBuilder.getResponseSpecBuilder()).extract().response();
        LogUtils.info("GET request to " + path);
        return response;
    }
    @Step("GET request to {0} with headers {1}")
    public static Response get(String path, Map<String, String> headers) {
        LogUtils.info("GET request to " + path + " with headers: " + headers);
        Response response =
                given(SpecBuilder.getRequestSpecBuilder().headers(headers)).
                        when().
                        get(path).
                        then().
                        spec(SpecBuilder.getResponseSpecBuilder()).
                        extract().
                        response();
        LogUtils.info("Response: " + response.prettyPrint());
        return response;
    }

    @Step("GET request to {0} with query param {1}={2}")
    public static Response getWithQuery(String path, String paramName, String paramValue) {
        LogUtils.info("GET request to " + path + " with query param: " + paramName + "=" + paramValue);
        Response response =
                given(SpecBuilder.getRequestSpecBuilder()).queryParam(paramName,paramValue).
                        when().
                        get(path).
                        then().
                        spec(SpecBuilder.getResponseSpecBuilder()).
                        extract().
                        response();
        LogUtils.info("Response: " + response.prettyPrint());
        return response;
    }

    @Step("GET request to {0} with query params {1}")
    public static Response getWithQuery(String path, Map<String, String> queryParams) {
        LogUtils.info("GET request to " + path + " with query params: " + queryParams);
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
        LogUtils.info("Response: " + response.prettyPrint());
        return response;
    }

    @Step("GET request to {0} without authentication")
    public static Response getNoAuth(String path) {
        Response response = given(SpecBuilder.getRequestSpecBuilderNoAuth()).when().get(path).then().spec(SpecBuilder.getResponseSpecBuilder()).extract().response();
        LogUtils.info("GET request to " + path + " without authentication");
        return response;
    }

    @Step("POST request to {0} with payload {1}")
    public static Response post(String path, Object payload) {
        LogUtils.info("POST request to " + path + " with payload: " + payload);
        Response response = given(SpecBuilder.getRequestSpecBuilder()).body(payload).when().post(path).then().spec(SpecBuilder.getResponseSpecBuilder()).extract().response();
        LogUtils.info("Response: " + response.prettyPrint());
        return response;
    }

    @Step("POST request to {0} without authentication with payload {1}")
    public static Response postNoAuth(String path, Object payload) {
        LogUtils.info("POST request to " + path + " without authentication with payload: " + payload);
        Response response = given(SpecBuilder.getRequestSpecBuilderNoAuth()).body(payload).when().post(path).then().spec(SpecBuilder.getResponseSpecBuilder()).extract().response();
        LogUtils.info("Response: " + response.prettyPrint());
        return response;
    }

    @Step("PUT request to {0} with payload {1}")
    public static Response put(String path, Object payload) {
        LogUtils.info("PUT request to " + path + " with payload: " + payload);
        Response response = given(SpecBuilder.getRequestSpecBuilder()).body(payload).when().put(path).then().extract().response();
        LogUtils.info("Response: " + response.prettyPrint());
        return response;
    }

    @Step("PUT request to {0} without authentication with payload {1}")
    public static Response putNoAuth(String path, Object payload) {
        LogUtils.info("PUT request to " + path + " without authentication with payload: " + payload);
        Response response = given(SpecBuilder.getRequestSpecBuilderNoAuth()).body(payload).when().put(path).then().extract().response();
        LogUtils.info("Response: " + response.prettyPrint());
        return response;
    }

    @Step("DELETE request to {0} with payload {1}")
    public static Response delete(String path, Object payload) {
        LogUtils.info("DELETE request to " + path + " with payload: " + payload);
        Response response = given(SpecBuilder.getRequestSpecBuilder()).body(payload).when().delete(path).then().extract().response();
        LogUtils.info("Response: " + response.prettyPrint());
        return response;
    }

    @Step("DELETE request to {0} with query param {1}={2}")
    public static Response deleteWithQuery(String path, String paramName, String paramValue) {
        LogUtils.info("DELETE request to " + path + " with query param: " + paramName + "=" + paramValue);
        Response response =
                given(SpecBuilder.getRequestSpecBuilder()).queryParam(paramName,paramValue).
                        when().
                        delete(path).
                        then().
                        spec(SpecBuilder.getResponseSpecBuilder()).
                        extract().
                        response();
        LogUtils.info("Response: " + response.prettyPrint());
        return response;
    }

    @Step("DELETE request to {0} with multiple query params map {1}")
    public static Response deleteWithQuery(String path, Map<String, String> queryParams) {
        LogUtils.info("DELETE request to " + path + " with multiple query params map: " + queryParams);
        RequestSpecification request = given(SpecBuilder.getRequestSpecBuilder());
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            request.queryParam(entry.getKey(), entry.getValue());
        }

        Response response =
                request.when()
                        .delete(path)
                        .then()
                        .spec(SpecBuilder.getResponseSpecBuilder())
                        .extract()
                        .response();
        LogUtils.info("Response: " + response.prettyPrint());
        return response;
    }

    @Step("Extracting response key {1} from response {0}")
    public static String getResponseKeyValue(Response response, String responseKey) {
        LogUtils.info("Extracting response key: " + responseKey);
        return response.jsonPath().get(responseKey).toString();
//        return response.getBody().path(responseKey);
    }

    @Step("Extracting response key {1} from response body {0}")
    public static String getResponseKeyValue(String responseBody, String responseKey) {
        LogUtils.info("Extracting response key: " + responseKey + " from response body: " + responseBody);
        JsonPath jsonPath = new JsonPath(responseBody);
        return jsonPath.get(responseKey).toString();
    }

    @Step("Get status code from response {0}")
    public static int getStatusCode(Response response) {
        LogUtils.info("Extracting status code: " + response.getStatusCode());
        return response.getStatusCode();
    }

    @Step("Get status line from response {0}")
    public static String getStatusLine(Response response) {
        LogUtils.info("Extracting status line: " + response.getStatusLine());
        return response.getStatusLine();
    }

    @Step("Get response header from response {0}")
    public static String getResponseHeader(Response response, String headerName) {
        LogUtils.info("Extracting response header: " + headerName);
        return response.getHeader(headerName);
    }

    @Step("Get response content type from response {0}")
    public static String getResponseContentType(Response response) {
        LogUtils.info("Extracting response content type: " + response.getContentType());
        return response.getContentType();
    }

    @Step("Get response cookie from response {0}")
    public static String getResponseCookieName(Response response, String cookieName) {
        LogUtils.info("Extracting response cookie: " + cookieName);
        return response.getCookie(cookieName);
    }

}
