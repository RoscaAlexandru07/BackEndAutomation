package ObjectData.ResponseObject;

import ObjectData.ResponseNotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

@Getter
public class ResponseTokenSuccess implements ResponseNotNull {
    @JsonProperty("token")
    public String token;
    @JsonProperty("expires")
    public String expires;
    @JsonProperty("status")
    public String status;
    @JsonProperty("result")
    public String result;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(token);
        Assert.assertNotNull(expires);
        Assert.assertNotNull(status);
        Assert.assertNotNull(result);
    }
}
