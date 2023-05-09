package com.example.Ecommerce.Springboot.Repository;

import com.example.Ecommerce.Springboot.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Seller findByEmailId(String email);

}
