package is.hi.hbv.confirmpay.ConfirmPay.Model;

import sun.tools.jstat.OptionOutputFormatter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Optional;

@Embeddable
public class PaymentMethod {
    private String name;
    private String nameOfPayer;
    private String SSN;
    private String cardType; //MasterCard, Visa, etc.
    private String cardNo; // Var accountno í UML-i
    private String expirationDate;
    private String securityNo;
    private String email;
    @Column(nullable = true)
    private long ownerId; // finna út hvernig optional virkar almennilega!
    private LocalDate date;
    public PaymentMethod() {
    }

    public PaymentMethod(String name, String nameOfPayer,
                         String SSN, String cardType, String cardNo,
                         String expirationDate, String securityNo, String email) {
        this.name = name;
        this.nameOfPayer = nameOfPayer;
        this.SSN = SSN;
        this.cardType = cardType;
        this.cardNo = cardNo;
        this.expirationDate = expirationDate;
        this.securityNo = securityNo;
        this.email = email;
        this.date = LocalDate.now();
    }
    // annar constructor fyrir optional ownerId;
    public PaymentMethod(String name, String nameOfPayer,
                         String SSN, String cardType, String cardNo,
                         String expirationDate, String securityNo, String email, Optional<Long> ownerId) {
        this.name = name;
        this.nameOfPayer = nameOfPayer;
        this.SSN = SSN;
        this.cardType = cardType;
        this.cardNo = cardNo;
        this.expirationDate = expirationDate;
        this.securityNo = securityNo;
        this.email = email;
        this.ownerId = ownerId.get();
        this.date = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfPayer() {
        return nameOfPayer;
    }

    public void setNameOfPayer(String nameOfPayer) {
        this.nameOfPayer = nameOfPayer;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSecurityNo() {
        return securityNo;
    }

    public void setSecurityNo(String securityNo) {
        this.securityNo = securityNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional getOwnerId() {
        return Optional.ofNullable(ownerId);
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
