package com.example.Ecommerce.Springboot.Repository;

import com.example.Ecommerce.Springboot.Enum.ProductCategory;
import com.example.Ecommerce.Springboot.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProductCategory(ProductCategory productCategory);

    @Query(value = "select * from product p where p.price>:price and p.product_category=:category",nativeQuery = true)
    List<Product> getAllProductsByPriceAndCategory(int price, String category);
}
