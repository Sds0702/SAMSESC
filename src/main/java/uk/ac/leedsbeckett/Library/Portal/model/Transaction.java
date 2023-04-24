package uk.ac.leedsbeckett.Library.Portal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    private String Id;
    private Double BookIsbn;
    private LocalDate dueDate;
    private Type type;
    private Status status;
    @ManyToOne
    @JoinColumn(name="Account",referencedColumnName="id")
    @ToString.Exclude
    private Book account;

    @JsonProperty
    public String getStudentId()
    {
        return account.getStudentId();
    }

    @JsonProperty
    public void setAccount(Book account) {
        this.account = account;
    }

    @JsonIgnore
    public Book getAccount() {
        return this.account;
    }

    public Transaction() {
    }

    public Transaction(String Id, Double BookIsbn, LocalDate dueDate, Type type, Book account) {
        this.Id = getStudentId();
        this.BookIsbn = getBookIsbn();
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
