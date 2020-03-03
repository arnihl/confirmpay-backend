package is.hi.hbv.confirmpay.ConfirmPay.Model;

import javax.persistence.*;
import java.time.*;
import java.util.Date;
import java.util.List;


@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /* title? */
    private String name;

    private Date eDate;
    private double priceCat;
    private long eventOwner;
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

    public Event(String name, Date date, double priceCat, long ownerId, String description,
                 int maxParticipants, int minParticipants, boolean isPublic,
                 boolean isRefundPossible, PaymentMethod paymentMethod ){
        this.name = name;
        this.eDate = date;
        this.priceCat = priceCat;
        this.eventOwner = ownerId;
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

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public double getPriceCat() {
        return priceCat;
    }

    public void setPriceCat(double priceCat) {
        this.priceCat = priceCat;
    }

    public long getOwnerId() {
        return eventOwner;
    }

    public void setOwnerId(long ownerId) {
        this.eventOwner = ownerId;
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
        // TODO: Sjá hvort event sé búið.. ef svo er þá breyta isActive í false
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
