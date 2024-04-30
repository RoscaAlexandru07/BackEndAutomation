package ObjectData.ResponseObject;

import ObjectData.ResponseNotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;
@Getter
public class Books implements ResponseNotNull {

    @JsonProperty("isbn")
    private  String isbn;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(isbn);
    }
}
