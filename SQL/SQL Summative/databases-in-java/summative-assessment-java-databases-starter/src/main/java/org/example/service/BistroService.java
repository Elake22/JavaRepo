package org.example.service;

import org.example.data.*;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BistroService {

    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final PaymentRepo paymentRepo;
    private final PaymentTypeRepo paymentTypeRepo;
    private final ServerRepo serverRepo;
    private final TaxRepo taxRepo;
    private final ItemRepo itemRepo;
    private final ItemCategoryRepo itemCategoryRepo;

    public BistroService(
            OrderRepo orderRepo,
            OrderItemRepo orderItemRepo,
            PaymentRepo paymentRepo,
            PaymentTypeRepo paymentTypeRepo,
            ServerRepo serverRepo,
            TaxRepo taxRepo,
            ItemRepo itemRepo,
            ItemCategoryRepo itemCategoryRepo
    ) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.paymentRepo = paymentRepo;
        this.paymentTypeRepo = paymentTypeRepo;
        this.serverRepo = serverRepo;
        this.taxRepo = taxRepo;
        this.itemRepo = itemRepo;
        this.itemCategoryRepo = itemCategoryRepo;
    }

    // 1. Retrieve all orders
    public List<Order> getAllOrders() throws InternalErrorException, RecordNotFoundException {
        return orderRepo.getAllOrders();
    }

    // 2. Retrieve a single order by ID
    public Order getOrder(int orderId) throws InternalErrorException, RecordNotFoundException {
        Order order = orderRepo.getOrderById(orderId);
        if (order == null) {
            throw new RecordNotFoundException("Order not found");
        }
        order.setItems(orderItemRepo.findByOrderId(orderId));
        order.setPayments(paymentRepo.findByOrderId(orderId));
        return order;
    }

    // 3. Delete order
    public void deleteOrder(int orderId) throws InternalErrorException {
        orderRepo.deleteOrder(orderId);
    }

    // 4. Create a new blank order
    public Order getNewOrder() {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setItems(new ArrayList<>());
        order.setPayments(new ArrayList<>());
        return order;
    }

    // 5. Calculate order totals
    public void calculateOrderTotals(Order order) {
        BigDecimal subtotal = order.getItems().stream()
                .map(oi -> oi.getPrice().multiply(BigDecimal.valueOf(oi.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal tax = subtotal.multiply(new BigDecimal("0.10")); // Placeholder: adjust if needed
        BigDecimal total = subtotal.add(tax).add(order.getTip() != null ? order.getTip() : BigDecimal.ZERO);

        order.setSubTotal(subtotal);
        order.setTax(tax);
        order.setTotal(total);
    }

    // 6. Update existing order
    public void updateOrder(Order order) throws InternalErrorException {
        orderRepo.deleteOrder(order.getOrderID());
        orderItemRepo.update(order.getOrderID(), order.getItems());
        paymentRepo.update(order.getOrderID(), order.getPayments());
    }

    // 7. Add new order
    public Order addOrder(Order order) throws InternalErrorException {
        Order created = orderRepo.addOrder(order);
        orderItemRepo.update(created.getOrderID(), order.getItems());
        paymentRepo.update(created.getOrderID(), order.getPayments());
        return created;
    }

    // 8. Get all item categories
    public List<ItemCategory> getAllItemCategories() {
        return itemCategoryRepo.findAll();
    }

    // 9. Get all items by category
    public List<Item> getAllItemsByCategory(int categoryId) throws InternalErrorException {
        return itemRepo.findByCategoryId(categoryId); // You must implement this in ItemRepoImpl
    }

    // 10. Get item by ID
    public Item getItem(int itemId) throws InternalErrorException, RecordNotFoundException {
        return itemRepo.findById(itemId)
                .orElseThrow(() -> new RecordNotFoundException("Item not found"));
    }

    // 11. Get all payment types
    public List<PaymentType> getAllPaymentTypes() throws InternalErrorException {
        return paymentTypeRepo.findAll();
    }

    // 12. Get server by ID
    public Server getServerByID(int id) throws RecordNotFoundException, InternalErrorException {
        Server server = serverRepo.getServerById(id);
        if (server == null) {
            throw new RecordNotFoundException("Server not found");
        }
        return server;
    }

    // 13. Get all available servers
    public List<Server> getAllAvailableServers(LocalDate date) throws InternalErrorException {
        List<Server> servers = serverRepo.getAllAvailableServers(date);
        System.out.println("DEBUG: Available servers found = " + servers.size());
        return servers;
    }


    // Optional: used for displaying order info
    public Optional<Tax> getTaxById(int id) throws InternalErrorException {
        return taxRepo.findById(id);
    }

    public List<Item> getAllItems() throws InternalErrorException {
        return itemRepo.findAll();
    }

    public Optional<Server> getServerById(int id) throws RecordNotFoundException, InternalErrorException {
        return Optional.ofNullable(serverRepo.getServerById(id));
    }

    // Optional: fully hydrated order
    public Optional<Order> getCompleteOrder(int orderId) throws InternalErrorException {
        try {
            Order order = orderRepo.getOrderById(orderId);
            order.setItems(orderItemRepo.findByOrderId(orderId));
            order.setPayments(paymentRepo.findByOrderId(orderId));
            return Optional.of(order);
        } catch (RecordNotFoundException e) {
            return Optional.empty();
        }
    }



}
