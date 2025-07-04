package brp.commons;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class SpecBuilder {
    public static RequestSpecification getRequestSpecBuilder() {
        return new RequestSpecBuilder().
                setBaseUri(GlobalConstants.BASE_URI).
                setBasePath(GlobalConstants.BASE_PATH).
                addHeader("Authorization", "Bearer " + GlobalConstants.TOKEN).
                setContentType(ContentType.JSON).
                setAccept(ContentType.JSON).
                addFilter(new RequestLoggingFilter()).
                addFilter(new ResponseLoggingFilter()).
                log(LogDetail.ALL).
                build();

    }

    public static ResponseSpecification getResponseSpecBuilder() {
        return new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();

    }

    public static RequestSpecification getRequestSpecBuilderNoAuth() {
        return new RequestSpecBuilder()
                .setBaseUri(GlobalConstants.BASE_URI)
                .setBasePath(GlobalConstants.BASE_PATH)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON).addFilter(new RequestLoggingFilter()).addFilter(new ResponseLoggingFilter()).log(LogDetail.ALL).build();
    }
}
