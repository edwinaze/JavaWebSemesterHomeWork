package com.manager.javawebsemesterhomework.repository;

import com.manager.javawebsemesterhomework.entity.DO.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Page<Customer> findAllByNameLike(String name, Pageable pageable);
    public Page<Customer> findAllByAddressLike(String address, Pageable pageable);
}
