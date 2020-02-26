package is.hi.hbv.confirmpay.ConfirmPay.Service.Implementation;

import is.hi.hbv.confirmpay.ConfirmPay.Model.Event;
import is.hi.hbv.confirmpay.ConfirmPay.Repository.EventRepository;
import is.hi.hbv.confirmpay.ConfirmPay.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImplementation implements EventService {

    EventRepository repository;

    @Autowired
    public EventServiceImplementation(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event save(Event event) {
        return repository.save(event);
    }

    @Override
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public Event findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Event> findByOwnerId(long id) {
        return repository.findByOwnerId(id);
    }

    @Override
    public List<Event> findNewestPublic(boolean isPublic) {
        // TODO: raða í nýjast fyrst.
        return repository.findAllByPublicIs(isPublic);
    }

    @Override
    public void delete(Event event) {
        repository.delete(event);
    }
}
