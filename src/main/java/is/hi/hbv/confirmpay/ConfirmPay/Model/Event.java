package is.hi.hbv.confirmpay.ConfirmPay.Model;

import javax.persistence.*;
import java.time.*;
import java.util.List;

import is.hi.hbv.confirmpay.ConfirmPay.Model.PaymentMethod;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /* title? */
    private String name;
    private LocalDate date;
    private double priceCat;
    private long ownerId;
    @Lob
    private String description;
    private int maxParticipants;
    private int minParticipants;
    private boolean isPublic;
    private boolean isRefundPossible;
    // mögulega óþarfi fyrst við höfum date
    private boolean isActive;
    @Embedded
    private PaymentMethod paymentMethod;
    @ElementCollection
    private List<PaymentMethod> payments;

    public Event() {
    }

    public Event(String name, double priceCat, long ownerId, String description,
                 int maxParticipants, int minParticipants, boolean isPublic,
                 boolean isRefundPossible, boolean isActive, PaymentMethod paymentMethod ){
        this.name = name;
        this.date = LocalDate.now();
        this.priceCat = priceCat;
        this.ownerId = ownerId;
        this.description = description;
        this.maxParticipants = maxParticipants;
        this.minParticipants = minParticipants;
        this.isPublic = isPublic;
        this.isRefundPossible = isRefundPossible;
        this.paymentMethod = paymentMethod;
    }

    // þarf að muna að save-a ALLTAF í repository eftir að kallað er í þetta
    public void addToPaymentList(PaymentMethod paymentMethod){
        this.payments.add(paymentMethod);

    }

    public void refund(PaymentMethod paymentMethod){
        this.payments.remove(paymentMethod);
    }

    // getters and setters
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

    public double getPriceCat() {
        return priceCat;
    }

    public void setPriceCat(double priceCat) {
        this.priceCat = priceCat;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getMinParticipants() {
        return minParticipants;
    }

    public void setMinParticipants(int minParticipants) {
        this.minParticipants = minParticipants;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isRefundPossible() {
        return isRefundPossible;
    }

    public void setRefundPossible(boolean refundPossible) {
        isRefundPossible = refundPossible;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<PaymentMethod> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentMethod> payments) {
        this.payments = payments;
    }
}
