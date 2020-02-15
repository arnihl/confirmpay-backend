package is.hi.hbv.confirmpay.ConfirmPay.Repository;

import is.hi.hbv.confirmpay.ConfirmPay.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
