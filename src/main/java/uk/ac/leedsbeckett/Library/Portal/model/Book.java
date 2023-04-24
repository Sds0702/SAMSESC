package uk.ac.leedsbeckett.Library.Portal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Book
{
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long isbn;
    @Column(unique = true)
    private String bookIsbn;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private List<Transaction> transactionList = new ArrayList<>();
    @Transient
    private boolean hasOutstandingBalance;

    public Book(String bookIsbn)
    {
        this.bookIsbn = bookIsbn;
    }

    public String getStudentId() {
        return Id;
    }
}
