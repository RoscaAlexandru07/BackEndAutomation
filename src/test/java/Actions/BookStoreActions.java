package Actions;

import ObjectData.CollectionOfIsbns.CollectionOfIsbns;
import ObjectData.RequestObject.RequestAccount;
import ObjectData.RequestObject.RequestBookStore;
import ObjectData.ResponseObject.ResponseAccountSuccess;
import ObjectData.ResponseObject.ResponseBookStoreBookAdded;
import ObjectData.ResponseObject.ResponseBooks;
import RestClient.ResponseStauts;
import Service.ServiceImplementation.AccountServiceImpl;
import Service.ServiceImplementation.BookstoreServiceImpl;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Arrays;

public class BookStoreActions {

    BookstoreServiceImpl bookstoreService;

    public ResponseBookStoreBookAdded addBook(RequestBookStore requestBookStoreBody){
        bookstoreService = new BookstoreServiceImpl();
        Response response = bookstoreService.addBooksToAccount(requestBookStoreBody);

        System.out.println(response.statusCode()); //400 exemplu de cum arata raspusul
        System.out.println(response.statusLine()); //HTTP/1.1 400 Bad Request exemplu de cum arata raspunsul
        Assert.assertEquals(response.getStatusCode(), ResponseStauts.SC_CREATED);
        System.out.println(response.statusLine()); //HTTP/1.1 400 Bad Request exemplu de cum arata raspunsul

        ResponseBookStoreBookAdded responseBookStoreBookAdded = response.body().as(ResponseBookStoreBookAdded.class);

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
}
