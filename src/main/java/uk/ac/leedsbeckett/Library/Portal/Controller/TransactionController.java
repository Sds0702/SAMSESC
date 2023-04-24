package uk.ac.leedsbeckett.Library.Portal.Controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.leedsbeckett.Library.Portal.model.Transaction;

public class TransactionController
{
    private final TransactionService transactionService;

    TransactionController(TransactionService transactionService) {
        this.transactionService = TransactionService;
    }

    @GetMapping("/transaction")
    public CollectionModel<EntityModel<Transaction>> all() {
        return transactionService.getAllTransaction();
    }

    @GetMapping("/transaction/{id}")
    public EntityModel<Transaction> one(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/transaction/reference/{reference}")
    public EntityModel<Transaction> one(@PathVariable String reference) {
        return transactionService.getTransactionByReference(reference);
    }

    @PostMapping("/transactions")
    ResponseEntity<?> createNewTransaction(@RequestBody Transaction transaction) {
        return transactionService.createNewTransaction(transaction);
    }

   /* @DeleteMapping("/transaction/{reference}/cancel")
    public ResponseEntity<?> cancel(@PathVariable String reference) {
        return transactionService.cancel(reference);
    }

    @PutMapping("/transaction/{reference}/pay")
    public ResponseEntity<?> pay(@PathVariable String reference) {
        return transactionService.pay(reference);
    }*/
}
