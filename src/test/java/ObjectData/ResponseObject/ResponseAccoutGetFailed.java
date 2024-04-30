package ObjectData.ResponseObject;

import ObjectData.BookObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
@Getter
public class ResponseAccoutGetFailed {
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;

}
