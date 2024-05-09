package ObjectData.CollectionOfIsbns;

import ObjectData.ResponseObject.ResponseNotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.testng.Assert;

@AllArgsConstructor
@Data
public class CollectionOfIsbns implements ResponseNotNull {
    private String isbn;


    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(isbn);
    }
}
