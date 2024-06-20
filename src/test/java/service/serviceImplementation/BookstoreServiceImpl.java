package service.serviceImplementation;
import objectData.requestObject.RequestBookStore;
import objectData.requestObject.RequestAccountBook;
import service.apiService.BookStoreAPIService;
import service.interfaceService.BookstoreServiceInterface;
import service.endpoints.BookStoreEndpoint;
import io.restassured.response.Response;

public class BookstoreServiceImpl implements BookstoreServiceInterface {

    private BookStoreAPIService bookStoreAPIService;
    public BookstoreServiceImpl() {
        bookStoreAPIService = new BookStoreAPIService();
    }

    @Override
    public Response addBooksToAccount(RequestBookStore body, String token) {
        // body.setUserID(userID);
        return bookStoreAPIService.post(body, token, BookStoreEndpoint.BOOKSTORE_ADD);
    }

    @Override
    public Response getBooksFromAccount(String token) {
        return bookStoreAPIService.get(token, BookStoreEndpoint.BOOKSTORE_GET_BOOKS);
    }

    @Override
    public Response deleteSpecificBook(RequestAccountBook body, String token) {
        return bookStoreAPIService.delete(body, token, BookStoreEndpoint.BOOKSTORE_DELETE_BOOK);

    }


    @Override
    public Response updateSpecificBook(RequestAccountBook body, String token, String actualBook) {
        String url = BookStoreEndpoint.BOOKSTORE_UPDATE + actualBook;
        return bookStoreAPIService.put(body, token, url);
    }

    @Override
    public Response deleteBooks(String token, String userId) {
        String url = BookStoreEndpoint.BOOKSTORE_DELETE_BOOKS + userId;
        return bookStoreAPIService.delete(token, url);
    }


}
