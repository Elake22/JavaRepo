package com.example.demo.MockRepo;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Repository
@Profile("mock")
public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<Integer, Customer> customerMap = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public InMemoryCustomerRepository() {
        initializeSampleData();
    }

    private void seed(String first, String last, String phone, String email, String carModel) {
        Customer c = new Customer();          // uses JPA no-arg ctor
        c.setFirstName(first);
        c.setLastName(last);
        c.setPhone(phone);
        c.setEmail(email);                    // service will normalize to lowercase
        c.setCarModel(carModel);              // null is fine if you don’t have one
        save(c);
    }

    private void initializeSampleData() {
        seed("Nesha",  "Lake",   "123-456-7890", "nesha@example.com",   "Civic");
        seed("Elijah", "Lake",   "321-654-0987", "elijah@example.com",  "Mustang");
        seed("Tina",   "Moore",  "222-333-4444", "tina@example.com",    null);
        seed("Brandon","Lee",    "555-123-9876", "brandon@example.com", "Camry");
        seed("Sophia", "Nguyen", "888-777-6666", "sophia@example.com",  "Model 3");
    }


    @Override
    public <S extends Customer> S save(S customer) {
        if (customer.getId() == null) {
            customer.setId(idGenerator.getAndIncrement());
        }
        customerMap.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return Optional.ofNullable(customerMap.get(id));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public boolean existsById(Integer id) {
        return customerMap.containsKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        customerMap.remove(id);
    }

    @Override
    public long count() {
        return customerMap.size();
    }

    @Override
    public void delete(Customer entity) {
        if (entity.getId() != null) {
            customerMap.remove(entity.getId());
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll() {
        customerMap.clear();
    }

    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {
        for (Customer c : entities) {
            delete(c);
        }
    }

    @Override
    public List<Customer> findAllById(Iterable<Integer> ids) {
        List<Customer> list = new ArrayList<>();
        ids.forEach(id -> {
            if (customerMap.containsKey(id)) {
                list.add(customerMap.get(id));
            }
        });
        return list;
    }

    @Override
    public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
        List<S> saved = new ArrayList<>();
        for (S c : entities) {
            saved.add(save(c));
        }
        return saved;
    }

    @Override public void flush() {}
    @Override public <S extends Customer> S saveAndFlush(S entity) { return save(entity); }

    @Override
    public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override public void deleteAllInBatch() { customerMap.clear(); }
    @Override public void deleteAllInBatch(Iterable<Customer> entities) { deleteAll(entities); }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override public Customer getOne(Integer integer) { return customerMap.get(integer); }
    @Override public Customer getById(Integer integer) { return customerMap.get(integer); }

    @Override
    public Customer getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Customer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Customer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Customer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Customer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public Optional<Customer> findByEmailIgnoreCase(String email) {
        return Optional.empty();
    }

    @Override
    public List<Customer> findByLastNameContainingIgnoreCase(String lastNamePart) {
        return List.of();
    }

    @Override
    public List<Customer> findByPhone(String phone) {
        return List.of();
    }

    @Override
    public List<Customer> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return null;
    }
}
