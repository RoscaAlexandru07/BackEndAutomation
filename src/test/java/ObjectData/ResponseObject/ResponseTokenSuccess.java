package ObjectData.ResponseObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseTokenSuccess {
    @JsonProperty("token")
    public String token;
    @JsonProperty("expires")
    public String expires;
    @JsonProperty("status")
    public String status;
    @JsonProperty("result")
    public String result;

    public String getToken() {
        return token;
    }

    public String getExpires() {
        return expires;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }
}
