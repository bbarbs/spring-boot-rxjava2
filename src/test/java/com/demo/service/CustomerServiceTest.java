package com.demo.service;

import com.demo.model.Customer;
import com.demo.repository.CustomerRepository;
import com.demo.service.impl.CustomerServiceImpl;
import io.reactivex.Maybe;
import io.reactivex.observers.TestObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.when;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerServiceImpl service;

    @Mock
    CustomerRepository repository;

    @Test
    public void testShouldFindAllCustomers() {
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        when(this.repository.findAll()).thenReturn(Arrays.asList(customer));
        TestObserver<List<Customer>> observer = new TestObserver<>();
        Maybe<List<Customer>> list = this.service.findAllCustomer();
        list.subscribe(observer);
        observer.assertValueCount(1);
        observer.assertNoErrors();
        observer.assertValue(Arrays.asList(customer));
    }

    @Test
    public void testShouldGetCustomerById() {
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        when(this.repository.findOne(1L)).thenReturn(customer);
        TestObserver<Customer> observer = new TestObserver<>();
        Maybe<Customer> list = this.service.getCustomerById(1L);
        list.subscribe(observer);
        observer.assertValueCount(1);
        observer.assertNoErrors();
        observer.assertValue(customer);
    }

    @Test
    public void testShouldAddCustomer() {
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        when(this.repository.save(customer)).thenReturn(customer);
        TestObserver<Customer> observer = new TestObserver<>();
        Maybe<Customer> list = this.service.addCustomer(customer);
        list.subscribe(observer);
        observer.assertValueCount(1);
        observer.assertNoErrors();
        observer.assertValue(customer);
    }
}
