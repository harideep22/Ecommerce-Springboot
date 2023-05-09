package com.example.Ecommerce.Springboot.Repository;

import com.example.Ecommerce.Springboot.Model.Ordered;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Ordered,Integer> {

}
