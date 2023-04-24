package uk.ac.leedsbeckett.Library.Portal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Locale;

@Entity
@Data
public class Transaction
{
    private @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    @Size(min = 8, max = 8, message = "{reference.size}")
    @Pattern(regexp = "[A-Z0-9]*", message = "{reference.format}")
    private String reference;
    private Double amount;
    private LocalDate dueDate;
    private Type type;
    private String getStudentId;
    private Status status;
    @ManyToOne
    @JoinColumn(name="book_fk",referencedColumnName="id")
    @ToString.Exclude
    private Book account;

    @JsonProperty
    public String getStudentId()
    {
        return account.getStudentId();
    }

    @JsonProperty
    public void setAccount(Account account) {
        this.account = account;
    }

    @JsonIgnore
    public Account getAccount() {
        return this.account;
    }

    public Invoice() {
    }

    public Invoice(Double amount, LocalDate dueDate, Type type, Account account) {
        this.amount = amount;
        this.dueDate = dueDate;
        this.type = type;
        this.account = account;
        populateReference();
    }

    public void populateReference() {
        if (this.reference == null) {
            this.reference = RandomStringUtils.random(8, true, true).toUpperCase(Locale.UK);
        }
    }
}
