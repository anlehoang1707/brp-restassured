package brp.helpers;

import brp.commons.ApiEndPoint;
import brp.commons.ApiKeyword;
import brp.commons.BaseTest;
import io.restassured.response.Response;

import java.util.List;

public class TestDataHelper{
    public static int getValidCategoryId() {
        Response response = ApiKeyword.get(ApiEndPoint.GET_ALL_CATEGORYS.getPathString());
        return ApiKeyword.getResponseKeyValueInt(response,"response[0].id");
    }

    public static List<Integer> getValidImageList(int firstImageIndex, int secondImageIndex) {
        Response response = ApiKeyword.get(ApiEndPoint.GET_ALL_IMAGES.getPathString());
        return ApiKeyword.getResponseKeyValueListInt(response,"response.id").subList(firstImageIndex,secondImageIndex);
    }

    public static int getValidBookId() {
        Response response = ApiKeyword.get(ApiEndPoint.GET_ALL_BOOKS.getPathString());
        return ApiKeyword.getResponseKeyValueInt(response,"response[0].id");
    }
}
