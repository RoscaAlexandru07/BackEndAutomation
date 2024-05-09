package ObjectData.ResponseObject;

import ObjectData.BookObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

import java.util.List;
@Getter
public class ResponseAccountSuccess implements ResponseNotNull {

    //jsonProperty("userID") -> aici treci exact cum vine response-ul si practic e un fel de alias userID = userid (proprietate java)
    @JsonProperty("userID")
    @JsonAlias("userId")
    private String userID;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("books")
    private List<BookObject> books;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(userID);
        Assert.assertNotNull(userName);
        for(BookObject bookObject : books) {
            bookObject.validateNotNullFields();
        }
    }

    public void validateBookPresance(String book){
        boolean checkBookPresence = false;
        for(BookObject responseBooks: books){
            if(responseBooks.getIsbn().equals(book)){
                checkBookPresence = true;
                break;
            }
        }
        Assert.assertTrue(checkBookPresence);
    }
}
