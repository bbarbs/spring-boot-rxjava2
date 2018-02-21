package com.demo.service.impl;

import com.demo.exception.CustomerNotFoundException;
import com.demo.exception.EmailExistsException;
import com.demo.model.Customer;
import com.demo.repository.CustomerRepository;
import com.demo.service.CustomerService;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Observable<List<Customer>> findAllCustomer() {
        return Observable.fromCallable(() -> {
            List<Customer> customers = this.customerRepository.findAll();
            return customers;
        });
    }

    @Override
    public Observable<Customer> getCustomerById(Long customerId) {
        return Observable.fromCallable(() -> {
            Customer customer = customerRepository.findOne(customerId);
            if (customer == null) {
                throw new CustomerNotFoundException("Customer not found");
            }
            return customer;
        });
    }

    @Override
    public Observable<Customer> addCustomer(Customer customer) {
        return Observable.defer(() -> {
            // Check if email exist.
            boolean isEmailExist = this.customerRepository.existsByEmail(customer.getEmail());
            if (isEmailExist) {
                throw new EmailExistsException("Email already exist");
            }
            Customer c = this.customerRepository.save(customer);
            return Observable.just(c);
        });
    }

    @Override
    public Observable<Customer> updateCustomerById(Long customerId, Customer customer) {
        return Observable.fromCallable(() -> {
            Customer c = this.customerRepository.findOne(customerId);
            if (c == null) {
                throw new CustomerNotFoundException("Customer not found");
            }
            // Update customer.
            c.setFirstname(customer.getFirstname());
            c.setLastname(customer.getLastname());
            c.setAge(customer.getAge());
            this.customerRepository.save(c);
            return c;
        });
    }

    @Override
    public Observable<Integer> deleteCustomerById(Long customerId) {
        return Observable.defer(() -> {
            Customer customer = this.customerRepository.findOne(customerId);
            if (customer == null) {
                return Observable.error(new CustomerNotFoundException("Customer not found"));
            }
            this.customerRepository.delete(customerId);
            return Observable.just(1);
        });
    }
}
