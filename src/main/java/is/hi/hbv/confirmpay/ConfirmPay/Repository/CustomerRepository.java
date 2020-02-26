package is.hi.hbv.confirmpay.ConfirmPay.Repository;

import is.hi.hbv.confirmpay.ConfirmPay.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer save(Customer customer);
    List<Customer> findAll();
    Customer findById(long id);
    Customer findByName(String name);
    List<Customer> findAllByNameIn(String name);
    void delete(Customer customer);


}
