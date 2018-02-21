package com.demo.service;

import com.demo.model.Customer;
import io.reactivex.Observable;

import java.util.List;

public interface CustomerService {

    /**
     * Get list of customers.
     *
     * @return
     */
    Observable<List<Customer>> findAllCustomer();

    /**
     * Get customer by id.
     *
     * @param customerId
     * @return
     */
    Observable<Customer> getCustomerById(Long customerId);

    /**
     * Add customer.
     *
     * @param customer
     * @return
     */
    Observable<Customer> addCustomer(Customer customer);

    /**
     * Update customer by id.
     *
     * @param customerId
     * @param customer
     * @return
     */
    Observable<Customer> updateCustomerById(Long customerId, Customer customer);

    /**
     * Delete customer by id.
     *
     * @param customerId
     * @return
     */
    Observable<Integer> deleteCustomerById(Long customerId);
}
