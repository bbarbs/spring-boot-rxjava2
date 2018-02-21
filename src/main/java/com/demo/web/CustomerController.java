package com.demo.web;

import com.demo.model.Customer;
import com.demo.service.CustomerService;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController extends ExceptionHandler {

    @Autowired
    CustomerService customerService;

    /**
     * Get list of customers.
     *
     * @return
     */
    @GetMapping(
            produces = APPLICATION_JSON_VALUE
    )
    public List<Customer> getAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        Observable<List<Customer>> list = this.customerService.findAllCustomer();
        list.subscribe(res -> customers.addAll(res)).dispose();
        return customers;
    }

    /**
     * Add customer.
     *
     * @param customer
     * @return
     */
    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public Customer addCustomer(@RequestBody Customer customer) {
        AtomicReference<Throwable> cause = new AtomicReference<>();
        AtomicReference<Customer> c = new AtomicReference<>(new Customer());
        Observable<Customer> result = this.customerService.addCustomer(customer);
        result.subscribe(
                res -> c.set(res),
                throwable -> cause.set(throwable)
        ).dispose();
        this.handleException(cause.get());
        return c.get();
    }

    /**
     * Get customer by id.
     *
     * @param customerId
     * @return
     */
    @GetMapping(
            value = "/{customerId}",
            produces = APPLICATION_JSON_VALUE
    )
    public Customer getCustomerById(@PathVariable(name = "customerId") Long customerId) {
        AtomicReference<Throwable> cause = new AtomicReference<>();
        AtomicReference<Customer> c = new AtomicReference<>(new Customer());
        Observable<Customer> result = this.customerService.getCustomerById(customerId);
        result.subscribe(
                customer -> c.set(customer),
                throwable -> cause.set(throwable)
        ).dispose();
        this.handleException(cause.get());
        return c.get();
    }

    /**
     * Delete customer by id.
     *
     * @param customerId
     * @return
     */
    @DeleteMapping(
            value = "/{customerId}",
            produces = TEXT_PLAIN_VALUE
    )
    public ResponseEntity<?> deleteCustomerById(@PathVariable(name = "customerId") Long customerId) {
        AtomicReference<Throwable> cause = new AtomicReference<>();
        Observable<Integer> result = this.customerService.deleteCustomerById(customerId);
        result.subscribe(
                integer -> {
                },
                throwable -> cause.set(throwable)
        ).dispose();
        this.handleException(cause.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Update customer by id.
     *
     * @param customerId
     * @param customer
     * @return
     */
    @PutMapping(
            value = "/{customerId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public Customer updateCustomerById(@PathVariable(name = "customerId") Long customerId, @RequestBody Customer customer) {
        AtomicReference<Throwable> cause = new AtomicReference<>();
        AtomicReference<Customer> c = new AtomicReference<>(new Customer());
        Observable<Customer> result = this.customerService.updateCustomerById(customerId, customer);
        result.subscribe(
                cust -> c.set(cust),
                throwable -> cause.set(throwable)
        ).dispose();
        this.handleException(cause.get());
        return c.get();
    }
}
