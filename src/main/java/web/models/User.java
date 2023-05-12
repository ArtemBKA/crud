package web.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email should not be empty")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Firstname should not be empty")
    @Size(min = 2, max = 30, message = "Firstname should be between 2 and 30 characters")
    @Column(name = "firstname")
    private String firstname;

    @NotEmpty(message = "Lastname should not be empty")
    @Size(min = 2, max = 30, message = "Lastname should be between 2 and 30 characters")
    @Column(name = "lastname")
    private String lastname;

    public User() {}

    public User(int id, String email, String firstname, String lastname) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstname, lastname);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
