package Service.InterfaceService;
import ObjectData.RequestObject.RequestBookStore;
import io.restassured.response.Response;

public interface BookstoreServiceInterface {

    Response addBooksToAccount(RequestBookStore body, String token);
    Response getBooksFromAccount(String token);
    Response deleteSpecificBook(RequestBookStore body);
    Response updateSpecificBook(RequestBookStore body);
    Response deleteAccountBooks(RequestBookStore body);
}
