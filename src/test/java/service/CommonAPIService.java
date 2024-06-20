package service;

import RestClient.RequestType;
import RestClient.RestClinet;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonAPIService {
    //layer 2 = definirea actiunilor care e vor face pe configurarile de client (layer 1)
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_TYPE = "Bearer ";

    public Response post(Object body, String endpoint)
    {
        RequestSpecification requestSpecification = RestAssured.given();
        //pentru tipul asta de post facem un post cu un body
        requestSpecification.body(body);
        Response response = performRequest(RequestType.REQUEST_POST, requestSpecification, endpoint);

        return response;
    }

    public Response post(Object body, String token, String endpoint)
    {
        RequestSpecification requestSpecification = RestAssured.given();
        //pentru tipul asta de post facem un post cu un body
        requestSpecification.header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token);
        requestSpecification.body(body);
        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.REQUEST_POST);

        Response response = performRequest(RequestType.REQUEST_POST, requestSpecification, endpoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    public Response get(String token, String endpoint)
    {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token);
        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.REQUEST_GET);

        Response response = performRequest(RequestType.REQUEST_GET, requestSpecification, endpoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    public Response delete(String token, String endpoint)
    {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token);
        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.REQUEST_DELETE);

        Response response = performRequest(RequestType.REQUEST_DELETE, requestSpecification, endpoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    public Response put(Object body, String token, String endpoint)
    {
        RequestSpecification requestSpecification = RestAssured.given();
        //pentru tipul asta de post facem un post cu un body
        requestSpecification.header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token);
        requestSpecification.body(body);
        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.REQUEST_PUT);

        Response response = performRequest(RequestType.REQUEST_PUT, requestSpecification, endpoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    public Response delete(Object body, String token, String endpoint)
    {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token);
        requestSpecification.body(body);

        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.REQUEST_DELETE);

        Response response = performRequest(RequestType.REQUEST_DELETE, requestSpecification, endpoint);
        ServiceHelper.responseLogs(response);
        return response;
    }

    //metoda care instantiaza legatura cu layer 1
    private Response performRequest(String requestType, RequestSpecification requestSpecification, String enpoint)
    {
        return new RestClinet().performRequest(requestType,requestSpecification,enpoint);
    }
}
