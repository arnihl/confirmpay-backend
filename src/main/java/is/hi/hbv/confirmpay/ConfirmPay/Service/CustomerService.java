package is.hi.hbv.confirmpay.ConfirmPay.Service;

import is.hi.hbv.confirmpay.ConfirmPay.Model.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    List<Customer> findAll();
    Customer findById(long id);
    Customer findByName(String name);
    List<Customer> findByNameIn(String name);
    void delete(Customer customer);
    Customer login(Customer customer);

}
