package com.example.demo.MockRepo;

import com.example.demo.model.Booking;
import com.example.demo.model.Customer;
import com.example.demo.model.Mechanic;
import com.example.demo.model.Services;
import com.example.demo.repository.BookingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Repository
@Profile("mock")
public class InMemoryBookingRepository implements BookingRepository {

    private final Map<Integer, Booking> bookingMap = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public InMemoryBookingRepository() {
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Build Customer with setters (no positional ctor anymore)
        Customer customer1 = new Customer();
        customer1.setFirstName("Nesha");
        customer1.setLastName("Lake");
        customer1.setPhone("1234567890");
        customer1.setEmail("nesha@example.com");
        customer1.setCarModel("Civic"); // optional

        // Mechanic
        Mechanic mechanic1 = new Mechanic();
        mechanic1.setName("John Smith");
        mechanic1.setSpecialty("Engine Specialist");
        mechanic1.setYearsExperience(15);

        // Service
        Services service1 = new Services();
        service1.setName("Oil Change");
        service1.setDescription("Standard oil change");
        service1.setPrice(new BigDecimal("39.99"));

        // Booking
        Booking b1 = new Booking();
        b1.setCustomer(customer1);
        b1.setService(service1);
        b1.setMechanic(mechanic1);
        b1.setAppointmentAt(
                LocalDateTime.now().plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0) // morning slot
        );
        b1.setQuotedPrice(new BigDecimal("39.99"));
        b1.setStatus("SCHEDULED");
        b1.setNotes(null);

        save(b1);
    }

    @Override
    public <S extends Booking> S save(S booking) {
        if (booking.getId() == null) {
            booking.setId(idGenerator.getAndIncrement());
        }
        bookingMap.put(booking.getId(), booking);
        return booking;
    }

    @Override
    public Optional<Booking> findById(Integer id) {
        return Optional.ofNullable(bookingMap.get(id));
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(bookingMap.values());
    }

    @Override
    public void deleteById(Integer id) {
        bookingMap.remove(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return bookingMap.containsKey(id);
    }

    // ---- JpaRepository default methods  not need for testing ----
    @Override public long count() { return bookingMap.size(); }
    @Override public void delete(Booking entity) { bookingMap.remove(entity.getId()); }
    @Override public void deleteAll() { bookingMap.clear(); }
    @Override public void deleteAll(Iterable<? extends Booking> entities) {
        entities.forEach(e -> bookingMap.remove(e.getId()));
    }
    @Override public void deleteAllById(Iterable<? extends Integer> ids) {
        ids.forEach(bookingMap::remove);
    }
    @Override public List<Booking> findAllById(Iterable<Integer> ids) {
        List<Booking> list = new ArrayList<>();
        ids.forEach(id -> {
            if (bookingMap.containsKey(id)) list.add(bookingMap.get(id));
        });
        return list;
    }
    @Override public <S extends Booking> List<S> saveAll(Iterable<S> entities) {
        List<S> saved = new ArrayList<>();
        entities.forEach(e -> saved.add(save(e)));
        return saved;
    }
    @Override public void flush() {}
    @Override public <S extends Booking> S saveAndFlush(S entity) { return save(entity); }

    @Override
    public <S extends Booking> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override public void deleteAllInBatch() { bookingMap.clear(); }
    @Override public void deleteAllInBatch(Iterable<Booking> entities) { deleteAll(entities); }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override public Booking getOne(Integer integer) { return bookingMap.get(integer); }
    @Override public Booking getById(Integer integer) { return bookingMap.get(integer); }

    @Override
    public Booking getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Booking> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Booking> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Booking> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Booking> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Booking> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Booking> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Booking, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Booking> findByStatus(String status) {
        return List.of();
    }

    @Override
    public List<Booking> findByAppointmentAtBetween(LocalDateTime start, LocalDateTime end) {
        return List.of();
    }

    @Override
    public List<Booking> findByMechanicAndAppointmentAtBetween(Mechanic mechanic, LocalDateTime start, LocalDateTime end) {
        return List.of();
    }

    @Override
    public List<Booking> findByCustomer(Customer customer) {
        return List.of();
    }

    @Override
    public List<Booking> findByServiceAndStatus(Services service, String status) {
        return List.of();
    }

    @Override
    public List<Booking> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Booking> findAll(Pageable pageable) {
        return null;
    }
}
