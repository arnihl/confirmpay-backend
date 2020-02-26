package is.hi.hbv.confirmpay.ConfirmPay.Model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private LocalDate date;
    private int numOfEvents;
    private double rating;
    private String password;
    private String email;

    public Customer() {
    }

    public Customer(String name, int numOfEvents,
                    double rating, String password, String email) {
        this.name = name;
        this.date = LocalDate.now();
        this.numOfEvents = numOfEvents;
        this.rating = rating;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNumOfEvents() {
        return numOfEvents;
    }

    public void setNumOfEvents(int numOfEvents) {
        this.numOfEvents = numOfEvents;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
