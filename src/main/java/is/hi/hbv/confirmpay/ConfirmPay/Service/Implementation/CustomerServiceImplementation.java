package is.hi.hbv.confirmpay.ConfirmPay.Service.Implementation;

import is.hi.hbv.confirmpay.ConfirmPay.Hashing.PassEncrypter;
import is.hi.hbv.confirmpay.ConfirmPay.Model.Customer;
import is.hi.hbv.confirmpay.ConfirmPay.Repository.CustomerRepository;
import is.hi.hbv.confirmpay.ConfirmPay.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {
    CustomerRepository repository;

    @Autowired
    public CustomerServiceImplementation(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {
        PassEncrypter enc = new PassEncrypter();
        String encPass = "";
        try {
            encPass = enc.encrypt(customer.getPassword());
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        customer.setPassword(encPass);
        return repository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Customer> findByNameIn(String name) {
        return repository.findAllByNameIn(name);
    }

    @Override
    public Customer findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void delete(Customer customer) {
        repository.delete(customer);
    }

    @Override
    public Customer login(Customer customer) {
        PassEncrypter enc = new PassEncrypter();
        Customer exists = findByName(customer.getName());
        if(exists == null){
            return null;
        }
        String existsPass = "";
        try {
            existsPass = enc.encrypt(customer.getPassword());
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        if(existsPass.equals(exists.getPassword())){
            return exists;
        }
        return null;

    }
}
