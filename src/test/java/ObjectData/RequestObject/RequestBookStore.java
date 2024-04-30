package ObjectData.RequestObject;

import ObjectData.CollectionOfIsbns.CollectionOfIsbns;
import ObjectData.RequestPreparation;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
@Data
public class RequestBookStore implements RequestPreparation {

    private String userId;
    private List<CollectionOfIsbns> collectionOfIsbns;

    public RequestBookStore(HashMap<String, String> testData){
        prepareObject(testData);
    }
    @Override
    public void prepareObject(HashMap<String, String> testData) {
        for(String key: testData.keySet()) {
            switch (key) {
                case "userId":
                    setUserId(testData.get(key));
                    break;
                case "collectionOfIsbns":
                    prepareColletcionIsbn(testData.get(key));
                    break;
            }
        }
    }

    private void prepareColletcionIsbn(String value){
        String[] valueArrary = value.split(",");
        collectionOfIsbns = new ArrayList<>();

        for(int i =0; i<valueArrary.length; i++){
            collectionOfIsbns.add(new CollectionOfIsbns(valueArrary[i]));
        }
    }


}
