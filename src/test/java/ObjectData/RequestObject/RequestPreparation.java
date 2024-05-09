package ObjectData.RequestObject;

import java.util.HashMap;

public interface RequestPreparation {

    //interfata care are ca scop sa searializeze un specific request body

    void prepareObject(HashMap<String, String> testData);
}
