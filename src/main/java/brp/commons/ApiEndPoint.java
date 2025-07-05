package brp.commons;

public enum ApiEndPoint {
    GET_ALL_BOOK("/books/"),
    POST_BOOK("/book"),
    GET_BOOK_BY_STATUS("/book/find-by-status"),
    GET_BOOK_BY_ID("/book/%s"),
    PUT_BOOK_BY_ID("/book/%s"),
    PATCH_BOOK_BY_ID("/book/%s"),
    DELETE_BOOK_BY_ID("/book/%s"),;

    private final String pattern;

    ApiEndPoint(String pattern) {
        this.pattern = pattern;
    }

    public String getPathString(Object... args) {
        return String.format(pattern, args);
    }
}
