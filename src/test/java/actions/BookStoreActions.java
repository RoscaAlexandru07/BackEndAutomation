package actions;

import objectData.requestObject.RequestBookStore;
import objectData.requestObject.RequestAccountBook;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseBookStoreBookAdded;
import objectData.responseObject.ResponseBooks;
import RestClient.ResponseStauts;
import service.serviceImplementation.BookstoreServiceImpl;
import io.restassured.response.Response;
import org.testng.Assert;

public class BookStoreActions {

    BookstoreServiceImpl bookstoreService;

    public ResponseBookStoreBookAdded addBook(RequestBookStore requestBookStoreBody, String token){
        bookstoreService = new BookstoreServiceImpl();
        Response response = bookstoreService.addBooksToAccount(requestBookStoreBody, token);

        System.out.println(response.statusCode()); //400 exemplu de cum arata raspusul
        System.out.println(response.statusLine()); //HTTP/1.1 400 Bad Request exemplu de cum arata raspunsul
        Assert.assertEquals(response.getStatusCode(), ResponseStauts.SC_CREATED);
        System.out.println(response.statusLine()); //HTTP/1.1 400 Bad Request exemplu de cum arata raspunsul

        ResponseBookStoreBookAdded responseBookStoreBookAdded = response.body().as(ResponseBookStoreBookAdded.class);

        responseBookStoreBookAdded.validateNotNullFields();
        responseBookStoreBookAdded.validateBook(requestBookStoreBody.getCollectionOfIsbns());

        /*System.out.println(Arrays.toString(CollectionOfIsbns.getIsbn())); // as String mai face formatari pe langa, specific rest assured. asta e diff intre asta si asi to string
        Assert.assertEquals(responseBookStoreBookAdded.getIsbn(), requestBookStoreBody.getIsbn());
*/
        return responseBookStoreBookAdded;
    }

    public ResponseBooks getBooks(String token){
        bookstoreService = new BookstoreServiceImpl();
        Response response = bookstoreService.getBooksFromAccount(token);

        System.out.println(response.statusCode()); //400 exemplu de cum arata raspusul
        System.out.println(response.statusLine()); //HTTP/1.1 400 Bad Request exemplu de cum arata raspunsul

        ResponseBooks responseBooks = response.body().as(ResponseBooks.class);

        return responseBooks;

    }

    public void updateSpecificBookFromAccount(String token, RequestAccountBook requestBody, String actualBook){
        Response response = bookstoreService.updateSpecificBook(requestBody, token, actualBook);
        Assert.assertEquals(response.statusCode(), ResponseStauts.SC_OK);

        ResponseAccountSuccess responseAccountSuccess = response.body().as(ResponseAccountSuccess.class);
        responseAccountSuccess.validateNotNullFields();
        responseAccountSuccess.validateBookPresance(requestBody.getIsbn());
    }

    public void deleteSpecificBookFromAccount(String token, RequestAccountBook requestBody){
        Response response = bookstoreService.deleteSpecificBook(requestBody, token);
        Assert.assertEquals(response.statusCode(), ResponseStauts.SC_NOCONTENT);
    }

    public void deleteBooksFromAccount(String token, String userId){
        Response response = bookstoreService.deleteBooks(token, userId);
        Assert.assertEquals(response.statusCode(), ResponseStauts.SC_NOCONTENT);
    }
}
