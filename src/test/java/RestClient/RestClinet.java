package RestClient;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClinet {

    //layer 1 = clasa unde definim configurari la nivel de client
    //am de facut 2 actiuni: 1. Metoda care configureaza clientul
    //                       2. Metoda care returneaza un raspuns pe baza configurarii clientului

    private RequestSpecification prepareClient(RequestSpecification requestSpecification)
    {
        requestSpecification.baseUri("https://demoqa.com/");
        //requestSpecification.contentType("application/json");
        requestSpecification.contentType(ContentType.JSON);

        return requestSpecification;
    }

    public Response performRequest(String requestType, RequestSpecification requestSpecification, String endpoint)
    {
        switch (requestType)
        {
            case RequestType.REQUEST_POST:
                return prepareClient(requestSpecification).post(endpoint);
            case RequestType.REQUEST_GET:
                return prepareClient(requestSpecification).get(endpoint);
            case RequestType.REQUEST_PUT:
                return prepareClient(requestSpecification).put(endpoint);
            case RequestType.REQUEST_DELETE:
                return prepareClient(requestSpecification).delete(endpoint);
        }
            return null;
    }

}
