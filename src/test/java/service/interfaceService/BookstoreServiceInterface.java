package service.interfaceService;
import objectData.requestObject.RequestBookStore;
import objectData.requestObject.RequestAccountBook;
import io.restassured.response.Response;

public interface BookstoreServiceInterface {

    Response addBooksToAccount(RequestBookStore body, String token);
    Response getBooksFromAccount(String token);
    Response deleteSpecificBook(RequestAccountBook body, String token);
    Response updateSpecificBook(RequestAccountBook body, String token, String actualBook);
    Response deleteBooks(String token, String userId);
}
