package com.example.Ecommerce.Springboot.Repository;

import com.example.Ecommerce.Springboot.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
