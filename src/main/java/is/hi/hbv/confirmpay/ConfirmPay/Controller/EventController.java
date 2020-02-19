package is.hi.hbv.confirmpay.ConfirmPay.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import is.hi.hbv.confirmpay.ConfirmPay.Model.Event;
import is.hi.hbv.confirmpay.ConfirmPay.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @RequestMapping(value = "/api/get/{id}", method = RequestMethod.GET)
    public JSONPObject GetEvent(@PathVariable(id) String id){
        Event event = eventService.get(id);
        return
    }
}
