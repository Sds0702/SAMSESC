package uk.ac.leedsbeckett.Library.Portal.Controller;

import com.sun.istack.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RestController
public class BookController
{
    private final BookService bookService;

    BookController(BookService bookService)
    {
        this.bookService = bookService;
    }
    @GetMapping("/books")
    public CollectionModel<EntityModel<Book>> all()
    {
        return bookService.getAllBooks();
    }

    @PostMapping("/books")
    ResponseEntity<?> newAccount(@RequestBody @NotNull Book newBook) {
        return bookService.createNewBook(newBook);
    }

    @GetMapping("/books/student/{BookId}")
    public EntityModel<Book> getStudentAccount(@PathVariable String Id) {
        return bookService.getBookById(Id);
    }

    @GetMapping("/accounts/{isbn}")
    public EntityModel<Book> one(@PathVariable Long isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @PutMapping("/books/{id}")
    ResponseEntity<?> editAccount(@RequestBody Book newBook, @PathVariable Long id) {
        return bookService.updateOrCreateAccount(newBook, id);
    }

   /* @DeleteMapping("/accounts/{id}")
    ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        return bookService.deleteAccount(id);
    }*/
}