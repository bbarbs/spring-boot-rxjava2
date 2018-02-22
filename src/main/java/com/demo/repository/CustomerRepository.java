package com.demo.repository;

import com.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Get customer by email.
     *
     * @param email
     * @return
     */
    Customer findByEmail(String email);

    /**
     * Check if email exist.
     *
     * @param email
     * @return
     */
    boolean existsByEmail(String email);

    /**
     * Get customers by age.
     *
     * @param age
     * @return
     */
    List<Customer> findAllByAge(int age);
}
