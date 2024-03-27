package ObjectData.ResponseObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseBookStoreBookAdded {
    @JsonProperty("isbn")
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

}
