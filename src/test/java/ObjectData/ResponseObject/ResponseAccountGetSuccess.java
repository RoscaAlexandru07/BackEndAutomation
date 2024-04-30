package ObjectData.ResponseObject;

import ObjectData.BookObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
@Getter
public class ResponseAccountGetSuccess {

    //jsonProperty("userID") -> aici treci exact cum vine response-ul si practic e un fel de alias userID = userid (proprietate java)
    @JsonProperty("userId")
    private String userID;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("books")
    private List<BookObject> books;

}
