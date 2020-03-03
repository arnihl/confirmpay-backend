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

    @RequestMapping(value = "/api/event/save", method = RequestMethod.POST)
    public Event saveEvent(@Valid @RequestBody Event event, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Event format");
        }
        return eventService.save(event);
    }

    // findbyID
    @RequestMapping(value = "/api/event/get/{id}", method = RequestMethod.GET)
    public Event GetEvent(@PathVariable("id") String id){
        Event event = eventService.findById(Long.parseLong(id));
        return event;
    }

    // findAll
    @RequestMapping(value = "/api/event/getall", method = RequestMethod.GET)
    public List<Event> getAll(){
        return eventService.findAll();
    }

    // findallPublic
    @RequestMapping(value = "/api/event/getallpublic", method = RequestMethod.GET)
    public List<Event> getAllPublic(){
        return eventService.findNewestPublic(true);
    }

    // findbyOwnerId
    @RequestMapping(value = "/api/event/getbyowner/{id}", method = RequestMethod.GET)
    public List<Event> getByOwner(@PathVariable("id") String id){
        return eventService.findByOwnerId(Long.parseLong(id));
    }

    // Delete ef þetta endar í villu þá prufa að skila ResponseEntity<?>
    // TODO: þarf sennilega einhvað að laga þetta..
    @RequestMapping(value = "/api/event/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") String id){
        // TODO: Tjékka hvort loggedInUser sé sá sami og owner á Event
        Event event = eventService.findById(Long.parseLong(id));
        if(event == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Event not found");
        }
        else{
            eventService.delete(event);
        }
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "/make-events", method = RequestMethod.GET)
    public void makeEvents(){
        // TODO: Búa til dummy gögn
        PaymentMethod pm = new PaymentMethod("John Shooter", "John George Shooter the 3rd","0303834589", "MasterCard", "8734918293847162", "0712", "221", "shooter@gmail.com" , new Date());
        Event e1 = new Event("Funding Event!", new Date(), 100.1, 1L, "Let's cure the corona virus", -1, -1, true,false,pm);
        Event e2 = new Event("Ferðalag til himalaya", new Date(), 200000.0, 0L, "Ferð Lionsklúbbsins Libra til Himalaya", 10,10,false,true,pm);
        eventService.save(e1);
        eventService.save(e2);
    }
}
