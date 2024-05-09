package Actions;

import ObjectData.RequestObject.RequestAccount;
import ObjectData.ResponseObject.ResponseAccountGetSuccess;
import ObjectData.ResponseObject.ResponseAccountSuccess;
import ObjectData.ResponseObject.ResponseAccoutGetFailed;
import ObjectData.ResponseObject.ResponseTokenSuccess;
import RestClient.ResponseStauts;
import Service.ServiceImplementation.AccountServiceImpl;
import io.restassured.response.Response;
import org.testng.Assert;

public class AccountActions {

    //layer 3 care se bazeaza pe serviciul specific acesti clase
    private AccountServiceImpl accountService;

    public ResponseAccountSuccess createNewAccount(RequestAccount requestBody){
        accountService = new AccountServiceImpl();
        Response response = accountService.createAccount(requestBody);

        System.out.println(response.statusCode()); //400
        Assert.assertEquals(response.getStatusCode(), ResponseStauts.SC_CREATED);
        System.out.println(response.statusLine()); //HTTP/1.1 400 Bad Request


        //se valideaza resposeBody-ul
        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);
        responseAccountBody.validateNotNullFields();
        System.out.println(responseAccountBody.getUserID()); // as String mai face formatari pe langa, specific rest assured. asta e diff intre asta si asi to string
        Assert.assertEquals(responseAccountBody.getUserName(), requestBody.getUserName());
        return responseAccountBody;
    }

    public ResponseTokenSuccess generateToken(RequestAccount requestBody){
        accountService = new AccountServiceImpl();
        Response response = accountService.generateAccountToken(requestBody);

        System.out.println(response.statusCode()); //400
        System.out.println(response.statusLine()); //HTTP/1.1 400 Bad Request

        ResponseTokenSuccess responseTokenSuccess = response.body().as(ResponseTokenSuccess.class);
        responseTokenSuccess.validateNotNullFields();
        System.out.println(responseTokenSuccess.getToken());
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), ResponseStauts.SC_OK);
        System.out.println(response.getStatusLine());

        Assert.assertEquals(responseTokenSuccess.getStatus(), "Success");
        Assert.assertEquals(responseTokenSuccess.getResult(), "User authorized successfully.");
        return responseTokenSuccess;
    }

    public void checkPresenceAccount(String token, String userID, RequestAccount requestBody){
        accountService = new AccountServiceImpl();
        Response response = accountService.getSpecificAccount(token, userID);

        System.out.println(response.getStatusCode());
        if(response.getStatusCode() == ResponseStauts.SC_OK){
            Assert.assertEquals(response.getStatusCode(), ResponseStauts.SC_OK);
            System.out.println(response.getStatusLine());
            ResponseAccountGetSuccess responseAccountGetSuccess = response.body().as(ResponseAccountGetSuccess.class);
            Assert.assertEquals(responseAccountGetSuccess.getUserName(), requestBody.getUserName());

        }else {
            Assert.assertEquals(response.getStatusCode(), ResponseStauts.REQUEST_DELETE);
            System.out.println(response.getStatusLine());
            ResponseAccoutGetFailed responseAccoutGetFailed = response.body().as(ResponseAccoutGetFailed.class);
            Assert.assertEquals(responseAccoutGetFailed.getCode(), "1207");
            Assert.assertEquals(responseAccoutGetFailed.getMessage(), "User not found!");
        }




    }

    public void deleteSpecificAccount(String token, String userID){
        accountService = new AccountServiceImpl();
        Response response = accountService.deleteSpecificAccount(token, userID);

        Assert.assertEquals(response.getStatusCode(), ResponseStauts.SC_NOCONTENT);
    }
}
