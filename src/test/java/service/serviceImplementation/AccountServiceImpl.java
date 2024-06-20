package service.serviceImplementation;

import objectData.requestObject.RequestAccount;
import service.apiService.AccountAPIService;
import service.interfaceService.AccountServiceInterface;
import service.endpoints.AccountEndpoints;
import io.restassured.response.Response;

public class AccountServiceImpl implements AccountServiceInterface {

    //facem o instanta de api service ca sa putem accesa metodele generale PUT, GET, DELETE, POST

    private AccountAPIService accountAPIService;
    public AccountServiceImpl(){
        accountAPIService = new AccountAPIService();
    }
    @Override
    public Response createAccount(RequestAccount body) {
        return accountAPIService.post(body, AccountEndpoints.ACOUNT_CREATE);
    }

    @Override
    public Response generateAccountToken(RequestAccount body) {
        return accountAPIService.post(body, AccountEndpoints.ACOUNT_TOKEN);
    }

    @Override
    public Response getSpecificAccount(String token, String userId) {

        String accountEndpointURL = AccountEndpoints.ACOUNT_GET + userId;
        return accountAPIService.get(token, accountEndpointURL);
    }

    @Override
    public Response deleteSpecificAccount(String token, String userId) {
        String accountEndpointURL = AccountEndpoints.ACOUNT_DELETE + userId;
        return accountAPIService.delete(token, accountEndpointURL);
    }
}
