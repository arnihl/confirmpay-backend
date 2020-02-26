package is.hi.hbv.confirmpay.ConfirmPay.Service;

import is.hi.hbv.confirmpay.ConfirmPay.Model.Event;

import java.util.List;

public interface EventService {
    Event save(Event event);
    List<Event> findAll();
    Event findById(long id);
    List<Event> findByOwnerId(long id);
    List<Event> findNewestPublic(boolean isPublic);
    void delete(Event event);
}
