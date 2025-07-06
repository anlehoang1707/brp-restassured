package brp.commons;

public enum ApiEndPoint {
    // AUTHENTICATION

    POST_LOGIN("/login"),
    POST_REGISTER("/register"),

    // USER MANAGEMENT

    POST_USERS("/users"),
    GET_ALL_USERS("/users"),
    POST_USER("/user"),
    GET_USER_BY_USERNAME("/user"),
    DELETE_USER("/user"),
    PUT_USER_BY_ID("/user/%s"),
    PATCH_USER_BY_ID("/user/%s"),


    // BOOK MANAGEMENT

    GET_ALL_BOOKS("/books"),
    POST_BOOK("/book"),
    GET_BOOK_BY_STATUS("/book/find-by-status"),
    GET_BOOK_BY_ID("/book/%s"),
    PUT_BOOK_BY_ID("/book/%s"),
    PATCH_BOOK_BY_ID("/book/%s"),
    DELETE_BOOK_BY_ID("/book/%s"),

    // IMAGE MANAGEMENT

    GET_ALL_IMAGES("/images"),
    GET_IMAGE_BY_ID("/image/%s"),
    POST_IMAGE("/image"),
    POST_IMAGE_BY_ID("/image/%s"),
    DELETE_IMAGE_BY_ID("/image/%s"),


    // CATEGORY MANAGEMENT

    GET_ALL_CATEGORYS("/categorys"),
    POST_CATEGORY("/category"),
    GET_CATEGORY_BY_ID("/category/%s"),
    PUT_CATEGORY_BY_ID("/category/%s"),
    DELETE_CATEGORY_BY_ID("/category/%s")
    ;

    private final String pattern;

    ApiEndPoint(String pattern) {
        this.pattern = pattern;
    }

    public String getPathString(Object... args) {
        return String.format(pattern, args);
    }
}
