package ObjectData.CollectionOfIsbns;

import ObjectData.ResponseNotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.testng.Assert;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class CollectionOfIsbns implements ResponseNotNull {
    private String isbn;


    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(isbn);
    }
}
