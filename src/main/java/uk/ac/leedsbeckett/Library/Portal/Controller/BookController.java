package uk.ac.leedsbeckett.Library.Portal.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;

@RestController
public class BookController
{
    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }
}
    @GetMapping("/books")
    public CollectionModel<EntityModel<Book>> all() {
        return bookService.getAllBooks();
    }

    @PostMapping("/books")
    ResponseEntity<?> newAccount(@RequestBody @NotNull @NotEmpty Book newBook) {
        return bookService.createNewAccount(newBook);
    }

    @GetMapping("/books/student/{BookIsbn}")
    public EntityModel<Book> getStudentAccount(@PathVariable String Isbn) {
        return bookService.getBookById()ByStudentId(studentId);
    }

    @GetMapping("/accounts/{isbn}")
    public EntityModel<Book> one(@PathVariable Long isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @PutMapping("/books/{id}")
    ResponseEntity<?> editAccount(@RequestBody Book newBook, @PathVariable Long id) {
        return accountService.updateOrCreateAccount(newBook, id);
    }

    @DeleteMapping("/accounts/{id}")
    ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        return accountService.deleteAccount(id);
    }

}