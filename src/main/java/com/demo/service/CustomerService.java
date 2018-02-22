package com.demo.service;

import com.demo.model.Customer;
import io.reactivex.Maybe;

import java.util.List;

public interface CustomerService {

    /**
     * Get list of customers.
     *
     * @return
     */
    Maybe<List<Customer>> findAllCustomer();

    /**
     * Get customer by id.
     *
     * @param customerId
     * @return
     */
    Maybe<Customer> getCustomerById(Long customerId);

    /**
     * Add customer.
     *
     * @param customer
     * @return
     */
    Maybe<Customer> addCustomer(Customer customer);

    /**
     * Update customer by id.
     *
     * @param customerId
     * @param customer
     * @return
     */
    Maybe<Customer> updateCustomerById(Long customerId, Customer customer);

    /**
     * Delete customer by id.
     *
     * @param customerId
     * @return
     */
    Maybe<Void> deleteCustomerById(Long customerId);
}
