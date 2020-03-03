package is.hi.hbv.confirmpay.ConfirmPay.Controller;

import is.hi.hbv.confirmpay.ConfirmPay.Model.Customer;
import is.hi.hbv.confirmpay.ConfirmPay.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class CustomerController {

    CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // Signup
    @RequestMapping(value = "/api/customer/save", method = RequestMethod.POST)
    public Customer saveCustomer(@Valid @RequestBody Customer customer, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid User format");
        }
        return service.save(customer);
    }


    @RequestMapping(value = "/api/customer/findall", method = RequestMethod.GET)
    public List<Customer> findAllCustomers(){
        // TODO: passa að bara admin geti fundið alla, eða hvað?
        return service.findAll();
    }

    @RequestMapping(value = "/api/customer/findbyid/{id}")
    public Customer findCustomerById(@PathVariable("id") String id){
        return service.findById(Long.parseLong(id));
    }

    // Search by name
    @RequestMapping(value = "/api/customer/findByName")
    public List<Customer> findByCustomerNameIn(@RequestParam String name){
        List<Customer> customers = service.findByNameIn(name);
        if(customers.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No users found");
        }
        return customers;
    }

    @RequestMapping(value = "/api/customer/login", method = RequestMethod.POST)
    public Customer customerLogin(@Valid @RequestBody Customer customer, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Either username or password are incorrect");
        }
        Customer loginAttempt = service.login(customer);
        if(loginAttempt==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Either username or password are incorrect");
        }
        return loginAttempt;
    }

    @RequestMapping(value = "/make-customers", method = RequestMethod.GET)
    public void makeCustomers(){
        Customer c1 = new Customer("Summon Tufak", new Date(), 0, 5.0,"password","summon@gmail.com");
        Customer c2 = new Customer("Sofa king", new Date(), 0, 5.0,"password", "sofa@gmail.com");
        Customer c3 = new Customer("John Shooter", new Date(), 0, 5.0, "password", "john@gmail.com" );
        service.save(c1);
        service.save(c2);
        service.save(c3);
    }


}
