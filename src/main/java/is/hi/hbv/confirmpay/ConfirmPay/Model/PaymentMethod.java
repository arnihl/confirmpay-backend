package is.hi.hbv.confirmpay.ConfirmPay.Model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;
import java.util.Optional;

@Embeddable
public class PaymentMethod {
    private String pName;
    private String nameOfPayer;
    private String SSN;
    private String cardType; //MasterCard, Visa, etc.
    private String cardNo; // Var accountno í UML-i
    private String expirationDate;
    private String securityNo;
    private String email;
    @Column(nullable = true)
    private long ownerId; // finna út hvernig optional virkar almennilega!
    private Date pDate;
    public PaymentMethod() {
    }

    public PaymentMethod(String name, String nameOfPayer,
                         String SSN, String cardType, String cardNo,
                         String expirationDate, String securityNo, String email, Date date) {
        this.pName = name;
        this.nameOfPayer = nameOfPayer;
        this.SSN = SSN;
        this.cardType = cardType;
        this.cardNo = cardNo;
        this.expirationDate = expirationDate;
        this.securityNo = securityNo;
        this.email = email;
        this.pDate = date;
    }
    // annar constructor fyrir optional ownerId;
    public PaymentMethod(String name, String nameOfPayer,
                         String SSN, String cardType, String cardNo,
                         String expirationDate, String securityNo, String email, Date date, long ownerId) {
        this.pName = name;
        this.nameOfPayer = nameOfPayer;
        this.SSN = SSN;
        this.cardType = cardType;
        this.cardNo = cardNo;
        this.expirationDate = expirationDate;
        this.securityNo = securityNo;
        this.email = email;
        this.ownerId = ownerId;
        this.pDate = date;
    }

    public String getName() {
        return pName;
    }

    public void setName(String pName) {
        this.pName = pName;
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

    public Optional<Long> getOwnerId() {
        return Optional.ofNullable(ownerId);
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public Date getpDate() {
        return pDate;
    }

    public void setpDate(Date pDate) {
        this.pDate = pDate;
    }
}
