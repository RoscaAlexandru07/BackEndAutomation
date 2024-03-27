package Service.ServiceImplementation;
import ObjectData.RequestObject.RequestBookStore;
import ObjectData.ResponseObject.ResponseAccountSuccess;
import Service.APIService.BookStoreAPIService;
import Service.InterfaceService.BookstoreServiceInterface;
import io.restassured.response.Response;

public class BookstoreServiceImpl implements BookstoreServiceInterface {

    private BookStoreAPIService bookStoreAPIService;

    @Override
    public Response addBooksToAccount(RequestBookStore body) {
        bookStoreAPIService = new BookStoreAPIService();

       // body.setUserID(userID);
        return bookStoreAPIService.post(body, "BookStore/v1/Books");
    }

    @Override
    public Response getBooksFromAccount(String token) {
        bookStoreAPIService = new BookStoreAPIService();
        return bookStoreAPIService.get(token, "BookStore/v1/Books");
    }

    @Override
    public Response deleteSpecificBook(RequestBookStore body) {
        return null;
    }

    @Override
    public Response updateSpecificBook(RequestBookStore body) {
        return null;
    }

    @Override
    public Response deleteAccountBooks(RequestBookStore body) {
        return null;
    }


}
