package Tests;

import Actions.AccountActions;
import Actions.BookStoreActions;
import ObjectData.RequestObject.RequestAccount;
import ObjectData.RequestObject.RequestBookStore;
import ObjectData.ResponseObject.ResponseAccountGetSuccess;
import ObjectData.ResponseObject.ResponseAccountSuccess;
import ObjectData.ResponseObject.ResponseBookStoreBookAdded;
import ObjectData.ResponseObject.ResponseTokenSuccess;
import PropertyUtility.PropertyUtility;
import hooks.Hooks;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class CreateAccountTest extends Hooks {

    public String userId;
    public RequestAccount requestAccountBody;
    public RequestBookStore requestBookStore;
    public String token;

    public AccountActions accountActions;
    public BookStoreActions bookStoreActions;

    @Test
    public void CreateAccount(){
        System.out.println("Step1: create new account");
        createAccount();
        System.out.println();

        System.out.println("Step2: generate new token");
        generateToken();
        System.out.println();

        System.out.println("Step 3: get specific account");
        getSpecificAccount();

        /*System.out.println("Step 4: delete specific account");
        deleteSpecificAccount();*/


        System.out.println("Step 5: add specific book");
        addSpecificBook();

        System.out.println("Step 3: get specific account");
        getSpecificAccount();
    }


    public void createAccount(){
        PropertyUtility propertyUtility = new PropertyUtility("RequestData/createAccountData");
        requestAccountBody = new RequestAccount(propertyUtility.getAllData());

        accountActions = new AccountActions();
        ResponseAccountSuccess responseAccountBody = accountActions.createNewAccount(requestAccountBody);
        userId = responseAccountBody.getUserID();
    }

    public void generateToken(){

        ResponseTokenSuccess responseTokenSuccess = accountActions.generateToken(requestAccountBody);
        token = responseTokenSuccess.getToken();
    }

    public void getSpecificAccount(){
        accountActions.checkPresenceAccount(token,userId,requestAccountBody);
    }

    public void deleteSpecificAccount(){
        accountActions.deleteSpecificAccount(token,userId);
    }

    public void addSpecificBook(){
        PropertyUtility propertyUtility = new PropertyUtility("RequestData/bookStoreData");

        HashMap<String, String> testData = propertyUtility.getAllData();
        testData.put("userId", userId);

        requestBookStore = new RequestBookStore(testData);

        bookStoreActions = new BookStoreActions();
        ResponseBookStoreBookAdded responseBookStoreBookAdded = bookStoreActions.addBook(requestBookStore, token);

    }

    public void getBooksFromAccount(){
        bookStoreActions = new BookStoreActions();
        bookStoreActions.getBooks(token);

    }
}
