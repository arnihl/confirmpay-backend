package is.hi.hbv.confirmpay.ConfirmPay.Repository;

import is.hi.hbv.confirmpay.ConfirmPay.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event save(Event event);
    List<Event> findAll();
    Event findById(long id);
    List<Event> findByOwnerId(long id);
    List<Event> findAllByPublicIs(boolean isPublic);
    void delete(Event event);

}
