package is.hi.hbv.confirmpay.ConfirmPay.Controller;

import is.hi.hbv.confirmpay.ConfirmPay.Model.Event;
import is.hi.hbv.confirmpay.ConfirmPay.Model.PaymentMethod;
import is.hi.hbv.confirmpay.ConfirmPay.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/* Glósur frá Kristjáni um RestControllera
 * 1. Nota RequestBody til að fá gögn úr post formum
 *
 * 2. Nota throw new ResponseStatusException til að skila
 *    villum úr post requestum. mínúta 6 c.a í Code alonginu.
 *
 * Dæmi um 1 og 2 :
 *  public Movie addMovie(@Valid @RequestBody Movie movie, BindingResult result){
 *      if(result.hasErrors()){     // skilar httpstatus og error msg svo client viti hvað er vitlaust
 *          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid movie format");
 *      }
 *
 * }
 *
 * 3. Nota DELETE request þegar við viljum deleta með ResponseStatusException(httpStatus.NOT_FOUND,"errormsg")
 * 4. úr delete föllum skila ResponseEntity.noContent().build() vegna þess að við skilum engu.
 *  dæmi: public ResponseEntity<?> delete(long id) (úr controller)
 *          // ehv fetcha movie og service.delete(movie)
 *          return ResponseEntity.noContent().build();
 */
@RestController
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    // Receives Event on Json format and stores it in DB
    // Returns the stored event.
    @RequestMapping(value = "/api/event/save", method = RequestMethod.POST)
    public Event saveEvent(@Valid @RequestBody Event event, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Event format");
        }
        return eventService.save(event);
    }

    // Returns event with the corresponding id.
    @RequestMapping(value = "/api/event/get/{id}", method = RequestMethod.GET)
    public Event GetEvent(@PathVariable("id") String id){
        Event event = eventService.findById(Long.parseLong(id));
        if(event == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event with id: " + id + " was not found");
        return event;
    }

    // returns all events stored in the DB.
    // Should only be accessible to admin.
    // TODO: only accessible by admin?
    @RequestMapping(value = "/api/event/getall", method = RequestMethod.GET)
    public List<Event> getAll(){
        List<Event> events = eventService.findAll();
        if(events == null)
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Events available");
        return events;
    }

    // Returns a list of all public events.
    @RequestMapping(value = "/api/event/getallpublic", method = RequestMethod.GET)
    public List<Event> getAllPublic(){
        List<Event> events = eventService.findNewestPublic(true);
        if(events.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No events available");
        return events;
    }

    // Receives an id
    // returns a List of all events owned by the owner of
    // corresponding id.
    @RequestMapping(value = "/api/event/getbyowner/{id}", method = RequestMethod.GET)
    public List<Event> getByOwner(@PathVariable("id") String id){
        List<Event> events = eventService.findByOwnerId(Long.parseLong(id));
        if(events.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "This owner has no events");
        return events;
    }

    // Receives an id
    // Deletes an event with corresponding id.
    @RequestMapping(value = "/api/event/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEvent(@PathVariable("id") String id){
        // TODO: Tjékka hvort loggedInUser sé sá sami og owner á Event
        Event event = eventService.findById(Long.parseLong(id));
        if(event == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with id: " + id);
        }
        else{
            eventService.delete(event);
        }
        return ResponseEntity.noContent().build();

    }


    @RequestMapping(value = "/make-events", method = RequestMethod.GET)
    public void makeEvents(){
        PaymentMethod pm = new PaymentMethod("John Shooter", "John George Shooter the 3rd","0303834589", "MasterCard", "8734918293847162", "0712", "221", "shooter@gmail.com" , new Date());
        Event e1 = new Event("Funding Event!", new Date(), 100.1, 1L, "Support fund for victims of the Corona virus", -1, -1, true,false,pm);
        Event e2 = new Event("Ferðalag til himalaya", new Date(), 200000.0, 0L, "Ferð Lionsklúbbsins Libra til Himalaya", 10,10,false,true,pm);
        eventService.save(e1);
        eventService.save(e2);
    }


    /* This gets called when a registered or non-registered user(customer)
     * wants to pay for an event.
     * Receives JSON string on the format of a Paymentmethod and an id of event.
     *
     * If payment was successful, the payment method is added to the payments of the
     *  event and the same paymentmethod gets returned.
     */

    @RequestMapping(value = "/api/event/pay/{id}", method = RequestMethod.POST)
    public PaymentMethod payEvent(@PathVariable("id") long id, @Valid @RequestBody PaymentMethod pmethod, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment method not valid");
        }
        Event event = eventService.findById(id);
        if(event == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event with id: " + id + ", not found");
        }
        List<PaymentMethod> payments = event.getPayments();
        payments.add(pmethod);
        event.setPayments(payments);
        eventService.save(event);
        return pmethod;
    }
}
