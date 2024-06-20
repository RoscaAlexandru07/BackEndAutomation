package tests;

import actions.AccountActions;
import actions.BookStoreActions;
import objectData.requestObject.RequestAccount;
import objectData.requestObject.RequestBookStore;
import objectData.requestObject.RequestAccountBook;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseBookStoreBookAdded;
import objectData.responseObject.ResponseTokenSuccess;
import propertyUtility.PropertyUtility;
import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import hooks.Hooks;
import org.testng.annotations.Test;

import java.util.HashMap;

public class CreateAccountTest extends Hooks {

    public String userId;
    public RequestAccount requestAccountBody;
    public RequestBookStore requestBookStore;
    public String token;

    public AccountActions accountActions;
    public BookStoreActions bookStoreActions;
    public RequestAccountBook requestAccountBook;

    @Test
    public void CreateAccount(){
        System.out.println("Step1: create new account");
        createAccount();
        System.out.println();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user account is created");

        System.out.println("Step2: generate new token");
        generateToken();
        System.out.println();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Token was generated");


        System.out.println("Step 3: get specific account");
        getSpecificAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Validate the account presence");

        System.out.println("Step 5: add specific book");
        addSpecificBook();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "A book was added to specific account");

        System.out.println("Step 6: get specific account");
        getSpecificAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Validate the account presence");

        System.out.println("Step 7: Update Specific book from account");
        updateSpecificBook();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "A book was updated with success");

        System.out.println("Step 8: get specific account");
        getSpecificAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Validate the account presence");

        System.out.println("Step 9: delete specific book");
        deleteSpecificBook();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The specific book was deleted successfully");

        System.out.println("Step 10: get after delete of specific book");
        getSpecificAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "After delete of specific book");

        System.out.println("Step 11: delete all books from account");
        deleteAccountBooks();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Delete all books from account");

        System.out.println("Step 12: delete account");
        deleteSpecificAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Delete account");

        System.out.println("Step 13: get updated account");
        getSpecificAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Get updated account/deleted");
    }


    public void createAccount(){
        propertyUtility = new PropertyUtility("RequestData/createAccountData");
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
        propertyUtility = new PropertyUtility("RequestData/bookStoreData");

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

    public void updateSpecificBook(){
        propertyUtility = new PropertyUtility("RequestData/bookStoreData");

        HashMap<String, String> testData = propertyUtility.getAllData();
        String expectedBook = testData.get("expectedIsbn");
        String actualBook = testData.get("actualIsbn");
        testData.put("userId", userId);
        testData.put("isbn", expectedBook);
        requestAccountBook = new RequestAccountBook(testData);
        bookStoreActions.updateSpecificBookFromAccount(token, requestAccountBook, actualBook);
    }

    public void deleteSpecificBook(){
        bookStoreActions.deleteSpecificBookFromAccount(token, requestAccountBook);
    }

    public void deleteAccountBooks(){
        bookStoreActions.deleteBooksFromAccount(token, userId);
    }
}
