package uk.ac.leedsbeckett.Library.Portal.Controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import uk.ac.leedsbeckett.Library.Portal.Controller.BookController
import uk.ac.leedsbeckett.Library.Portal.model.BookModelAssembler;
import uk.ac.leedsbeckett.Library.Portal.model.BookRepository;
import uk.ac.leedsbeckett.Library.Portal.model.TransactionRepository;*;
import javax.transaction.Transaction;
import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService
{
    private final BookRepository bookRepository;
    private final BookModelAssembler assembler;
    //private final FineRepository fineRepository;
    private final TransactionRepository transactionRepository;

    public BookService(BookRepository bookRepository, BookModelAssembler assembler, /*FineRepository fineRepository*/ , TransactionRepository transactionRepository) {
        this.bookRepository = bookRepository;
        this.assembler = assembler;
        /*this.fineRepository = fineRepository;*/
        this.transactionRepository = transactionRepository;
    }
    public EntityModel<Book> getBookByIsbn (Long isbn) {
        Book account = bookRepository.findBookByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
        return assembler.toModel(populateOutstandingBalance(Book));
    }

    public CollectionModel<EntityModel<Book>> getAllBooks() {
        List<EntityModel<Book>> books = bookRepository.findAll()
                .stream()
                .map(this::populateOutstandingBalance)
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(Book, linkTo(methodOn(BookController.class).all()).withSelfRel());
    }

    public EntityModel<Book> getBookById(String BookIsbn) {
        Book bookAccount = bookRepository.findBookByIsbn(BookIsbn);
        if (bookAccount == null) {
            throw new BookNotFoundException(BookIsbn);
        }
        return assembler.toModel(populateOutstandingBalance(bookAccount));
    }

    public ResponseEntity<?> createNewBook(Book newBook) {
        if (newBook.getBookIsbn() == null || newBook.getBookByIsbn().isEmpty()) {
            throw new AccountNotValidException();
        }
        Book savedBook;
        try {
            savedBook = BookRepository.save(newBook);
        } catch (DataIntegrityViolationException e) {
            throw new BookNotValidException("An account already exists for student ID " + newBook.getBookByIsbn() + ".");
        }
        EntityModel<Book> entityModel = assembler.toModel(savedBook);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> updateOrCreateAccount(Book newBook, Long isbn) {
        Book updatedIsbn = bookRepository.findBookByIsbn(isbn)
                .map(account -> {
                    account.setBookIsbn(newBook.getBookByIsbn());
                    return bookRepository.save(isbnsbn);
                })
                .orElseGet(() -> {
                    newBook.setIsbn(isbn);
                   return accountRepository.save(newBook);
                });
        EntityModel<Book> entityModel = assembler.toModel(updatedAccount);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    /*public ResponseEntity<?> deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        accountRepository.delete(account);
        return ResponseEntity.noContent().build();
    }*/

    private Book populateOutstandingBalance(Book account) {
        if (account != null) {
            List<Transaction> transactions = TransactionRepository.findTransactionByAccount_IdAndStatus(account.getId(), Status.OUTSTANDING);

            if (transactions != null && !transactions.isEmpty()) {
                account.setHasOutstandingBalance(transactions
                        .stream()
                        .anyMatch(transaction -> transaction.getStatus().equals(Status.OUTSTANDING)));
            }
        }
        return account;
    }
}*/

