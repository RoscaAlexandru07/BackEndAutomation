package objectData.responseObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ResponseAccoutGetFailed {
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;

}
