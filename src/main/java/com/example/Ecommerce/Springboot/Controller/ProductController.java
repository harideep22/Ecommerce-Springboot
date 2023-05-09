package com.example.Ecommerce.Springboot.Controller;

import com.example.Ecommerce.Springboot.Dto.RequestDto.ProductRequestDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.Springboot.Enum.ProductCategory;
import com.example.Ecommerce.Springboot.Exception.InvalidSellerException;
import com.example.Ecommerce.Springboot.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSellerException {
        return productService.addProduct(productRequestDto);
    }
    @GetMapping("/get/{category}")
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable ProductCategory category){
        return productService.getAllProductsByCategory(category);
    }

    @GetMapping("/get/{price}/{category}")
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(@PathVariable ("price") int price,
                                                                     @PathVariable("category") String productCategory)
    {
        return productService.getAllProductsByPriceAndCategory(price,productCategory);
    }

    // get all products by seller emailid

    // delete a product by sellerid and productid

    // return top 5 cheapest products

    // return top 5 costliest products

    // return all out of stock products

    // return all available products

    // return all products that have quantity less than 10

    // return cheapest product in a particular category

    // return costliest product in a particular category

}
