package Service;

import RestClient.RequestType;
import RestClient.RestClinet;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonAPIService {
    //layer 2 = definirea actiunilor care e vor face pe configurarile de client (layer 1)

    public Response post(Object body, String endpoint)
    {
        RequestSpecification requestSpecification = RestAssured.given();
        //pentru tipul asta de post facem un post cu un body
        requestSpecification.body(body);
        Response response = performRequest(RequestType.REQUEST_POST, requestSpecification, endpoint);

        return response;
    }

    public Response get(String token, String endpoint)
    {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + token);
        Response response = performRequest(RequestType.REQUEST_GET, requestSpecification, endpoint);

        return response;
    }

    public Response delete(String token, String endpoint)
    {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + token);
        Response response = performRequest(RequestType.REQUEST_DELETE, requestSpecification, endpoint);

        return response;
    }

    //metoda care instantiaza legatura cu layer 1
    private Response performRequest(String requestType, RequestSpecification requestSpecification, String enpoint)
    {
        return new RestClinet().performRequest(requestType,requestSpecification,enpoint);
    }
}
