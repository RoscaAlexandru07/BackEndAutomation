package objectData.responseObject;

import objectData.collectionOfIsbns.CollectionOfIsbns;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

import java.util.List;
@Getter
public class ResponseBookStoreBookAdded implements ResponseNotNull {
    @JsonProperty("books")
    private List<Books> books;

    @Override
    public void validateNotNullFields() {
        for (Books book : books) {
            book.validateNotNullFields();
        }
    }
    public void validateBook(List<CollectionOfIsbns> actualValue) {
        for(int index = 0; index < books.size(); index++){
            Assert.assertEquals(actualValue.get(index).getIsbn(), books.get(index).getIsbn());
        }
    }
}
