package brp.model.books;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class NewBookPOJO {
    private String name;
    @JsonProperty("category_id")
    private int categoryId;

    private int price;
    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("image_ids")
    private int[] imageIds;

    private boolean status;
}
