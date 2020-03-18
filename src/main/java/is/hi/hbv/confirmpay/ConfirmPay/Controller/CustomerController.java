package is.hi.hbv.confirmpay.ConfirmPay.Controller;

import is.hi.hbv.confirmpay.ConfirmPay.Model.Customer;
import is.hi.hbv.confirmpay.ConfirmPay.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        //TODO: Email verður að vera uniqe líka..
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid User format");
        }
        Customer exists = service.findByName(customer.getName());
        if(exists == null) {
            customer.setcDate(new Date());
            return service.save(customer);
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is taken");
    }


    @RequestMapping(value = "/api/customer/getall", method = RequestMethod.GET)
    public List<Customer> findAllCustomers(){
        // TODO: passa að bara admin geti fundið alla, eða hvað?
        List<Customer> customers = service.findAll();
        if(customers.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Users found");
        }
        return customers;
    }

    @RequestMapping(value = "/api/customer/findbyid", method = RequestMethod.GET)
    public Customer findCustomerById(@RequestParam String id){
        Customer exists = service.findById(Long.parseLong(id));
        if(exists == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No User found");
        return exists;
    }

    // Search by name
    @RequestMapping(value = "/api/customer/findbyname", method = RequestMethod.GET)
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
        session.setAttribute("loggedInUser", loginAttempt);
        return loginAttempt;
    }

    @RequestMapping(value = "/make-customers", method = RequestMethod.GET)
    public void makeCustomers(){
        Customer c1 = new Customer("Summon snow", new Date(), 0, 5.0,"password","summon@gmail.com");
        Customer c2 = new Customer("Sofa king", new Date(), 0, 5.0,"password", "sofa@gmail.com");
        Customer c3 = new Customer("John Shooter", new Date(), 0, 5.0, "password", "john@gmail.com" );
        service.save(c1);
        service.save(c2);
        service.save(c3);
    }

    @RequestMapping(value = "/api/customer/isloggedin", method = RequestMethod.GET)
    public Customer isLoggedIn(HttpSession session){
        Customer sessionUser = (Customer) session.getAttribute("loggedInUser");
        if(sessionUser != null){
            return sessionUser;
        }
        else
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You have to be logged in");
    }

    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") long id){
        // TODO: Eventually... eingöngu leyfa þeim notanda sem skráður er inn, eyða sjálfum sér.
        Customer exists = service.findById(id);
        if(exists != null){
            service.delete(exists);
            return ResponseEntity.noContent().build();
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);
    }


}
