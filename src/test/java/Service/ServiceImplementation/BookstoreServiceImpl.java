package Service.ServiceImplementation;
import ObjectData.RequestObject.RequestBookStore;
import ObjectData.RequestObject.RequestAccountBook;
import Service.APIService.BookStoreAPIService;
import Service.InterfaceService.BookstoreServiceInterface;
import io.restassured.response.Response;

public class BookstoreServiceImpl implements BookstoreServiceInterface {

    private BookStoreAPIService bookStoreAPIService;

    @Override
    public Response addBooksToAccount(RequestBookStore body, String token) {
        bookStoreAPIService = new BookStoreAPIService();

        // body.setUserID(userID);
        return bookStoreAPIService.post(body, token, "BookStore/v1/Books");
    }

    @Override
    public Response getBooksFromAccount(String token) {
        bookStoreAPIService = new BookStoreAPIService();
        return bookStoreAPIService.get(token, "BookStore/v1/Books");
    }

    @Override
    public Response deleteSpecificBook(RequestAccountBook body, String token) {
        bookStoreAPIService = new BookStoreAPIService();
        return bookStoreAPIService.delete(body, token, "BookStore/v1/Book");

    }


    @Override
    public Response updateSpecificBook(RequestAccountBook body, String token, String actualBook) {
        bookStoreAPIService = new BookStoreAPIService();
        String url = "BookStore/v1/Books/" + actualBook;
        return bookStoreAPIService.put(body, token, url);
    }

    @Override
    public Response deleteBooks(String token, String userId) {
        bookStoreAPIService = new BookStoreAPIService();
        String url = "BookStore/v1/Books?UserId=" + userId;
        return bookStoreAPIService.delete(token, url);
    }


}
