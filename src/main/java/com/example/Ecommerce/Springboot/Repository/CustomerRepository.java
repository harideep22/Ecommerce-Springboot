package com.example.Ecommerce.Springboot.Repository;

import com.example.Ecommerce.Springboot.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByMobNo(String mobNo);
    Customer findByEmailId(String emailId);
}
