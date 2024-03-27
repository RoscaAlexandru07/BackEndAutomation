package Service.ServiceImplementation;

import ObjectData.RequestObject.RequestAccount;
import Service.APIService.AccountAPIService;
import Service.InterfaceService.AccountServiceInterface;
import io.restassured.response.Response;

public class AccountServiceImpl implements AccountServiceInterface {

    //facem o instanta de api service ca sa putem accesa metodele generale PUT, GET, DELETE, POST

    private AccountAPIService accountAPIService;
    @Override
    public Response createAccount(RequestAccount body) {
        accountAPIService = new AccountAPIService();
        return accountAPIService.post(body, "Account/v1/User");
    }

    @Override
    public Response generateAccountToken(RequestAccount body) {
        accountAPIService = new AccountAPIService();
        return accountAPIService.post(body, "Account/v1/GenerateToken");
    }

    @Override
    public Response getSpecificAccount(String token, String userId) {
        accountAPIService = new AccountAPIService();
        String accountEndpointURL = "Account/v1/User/" + userId;
        return accountAPIService.get(token, accountEndpointURL);
    }

    @Override
    public Response deleteSpecificAccount(String token, String userId) {
        accountAPIService = new AccountAPIService();
        String accountEndpointURL = "Account/v1/User/" + userId;
        return accountAPIService.delete(token, accountEndpointURL);
    }
}
