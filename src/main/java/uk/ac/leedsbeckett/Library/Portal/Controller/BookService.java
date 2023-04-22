package uk.ac.leedsbeckett.Library.Portal.Controller;

import org.springframework.stereotype.Component;
import uk.ac.leedsbeckett.Library.Portal.Controller.BookController*;
import javax.transaction.Transaction;
import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService
{
    private final BookRepository bookRepository;
    private final BookModelAssembler assembler;
    private final TransactionRepository transactionRepository;

    public BookService(BookRepository bookRepository, BookModelAssembler assembler, TransactionRepository transactionRepository) {
        this.bookRepository = bookRepository;
        this.assembler = assembler;
        this.transactionRepository = transactionRepository;
    }
    public EntityModel<Book> getBookById (Long isbn) {
        Book account = bookRepositoryRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        return assembler.toModel(populateOutstandingBalance(account));
    }

    public CollectionModel<EntityModel<Book>> getAllBooks() {
        List<EntityModel<Book>> books = bookRepository.findAll()
                .stream()
                .map(this::populateOutstandingBalance)
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(accounts, linkTo(methodOn(BookController.class).all()).withSelfRel());
    }

    public EntityModel<Book> getBookById(String BookIsbn) {
        Book bookAccount = bookRepository.findBookById(BookIsbn);
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

    public ResponseEntity<?> updateOrCreateAccount(Book newBook, Long id) {
        Book updatedIsbn = bookRepository.findBookByIsbn(isbn)
                .map(account -> {
                    account.setBookIsbn(newBook.getBookByIsbn());
                    return bookRepository.save(Isbn);
                })
                .orElseGet(() -> {
                    newBook.setIsbn(isbn);
                /*    return accountRepository.save(newAccount);
                });
        EntityModel<Account> entityModel = assembler.toModel(updatedAccount);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        accountRepository.delete(account);
        return ResponseEntity.noContent().build();
    }

    private Account populateOutstandingBalance(Account account) {
        if (account != null) {
            List<Invoice> invoices = invoiceRepository.findInvoiceByAccount_IdAndStatus(account.getId(), Status.OUTSTANDING);

            if (invoices != null && !invoices.isEmpty()) {
                account.setHasOutstandingBalance(invoices
                        .stream()
                        .anyMatch(invoice -> invoice.getStatus().equals(Status.OUTSTANDING)));
            }
        }
        return account;
    }
}*/

