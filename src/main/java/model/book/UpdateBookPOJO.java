package model.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class UpdateBookPOJO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("category_id")
    private int categoryId;

    @JsonProperty("price")
    private int price;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("image_ids")
    private List<Integer> imageIds;

    @JsonProperty("status")
    private boolean status;

    public static UpdateBookPOJO getInstance(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            return mapper.readValue(new File(fileName),UpdateBookPOJO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
