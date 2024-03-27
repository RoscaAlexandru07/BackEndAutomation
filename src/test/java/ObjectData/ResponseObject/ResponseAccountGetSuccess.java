package ObjectData.ResponseObject;

import ObjectData.BookObject;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseAccountGetSuccess {

    //jsonProperty("userID") -> aici treci exact cum vine response-ul si practic e un fel de alias userID = userid (proprietate java)
    @JsonProperty("userId")
    private String userID;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("books")
    private List<BookObject> books;


    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public List<BookObject> getBooks() {
        return books;
    }
}
