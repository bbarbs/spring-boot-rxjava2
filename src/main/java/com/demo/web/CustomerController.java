package com.demo.web;

import com.demo.model.Customer;
import com.demo.service.CustomerService;
import io.reactivex.Maybe;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * Get list of customers.
     *
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Customer.class)
    })
    @GetMapping(
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Maybe<List<Customer>> getAllCustomer() {
        return this.customerService.findAllCustomer();
    }

    /**
     * Add customer.
     *
     * @param customer
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Customer.class)
    })
    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Maybe<Customer> addCustomer(@RequestBody Customer customer) {
        return this.customerService.addCustomer(customer);
    }

    /**
     * Get customer by id.
     *
     * @param customerId
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Customer.class)
    })
    @GetMapping(
            value = "/{customerId}",
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Maybe<Customer> getCustomerById(@PathVariable(name = "customerId") Long customerId) {
        return this.customerService.getCustomerById(customerId);
    }

    /**
     * Delete customer by id.
     *
     * @param customerId
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content")
    })
    @DeleteMapping(
            value = "/{customerId}",
            produces = {APPLICATION_JSON_VALUE, TEXT_PLAIN_VALUE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Maybe<Void> deleteCustomerById(@PathVariable(name = "customerId") Long customerId) {
        return this.customerService.deleteCustomerById(customerId);
    }

    /**
     * Update customer by id.
     *
     * @param customerId
     * @param customer
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Customer.class)
    })
    @PutMapping(
            value = "/{customerId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Maybe<Customer> updateCustomerById(@PathVariable(name = "customerId") Long customerId, @RequestBody Customer customer) {
        return this.customerService.updateCustomerById(customerId, customer);
    }
}
