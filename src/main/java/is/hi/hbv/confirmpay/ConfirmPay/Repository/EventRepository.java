package is.hi.hbv.confirmpay.ConfirmPay.Repository;

import is.hi.hbv.confirmpay.ConfirmPay.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event save(Event event);
    List<Event> findAll();
    // gæti verið nauðsynlegt að láta þetta returna Optional<Event>
    Event findById(long id);
    List<Event> findByEventOwner(long id);
    List<Event> findAllByisPublic(boolean isPublic);
    void delete(Event event);

}
