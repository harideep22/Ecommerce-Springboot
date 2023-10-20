package com.example.Ecommerce.Springboot.Service;

import com.example.Ecommerce.Springboot.Dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.Springboot.Enum.ProductStatus;
import com.example.Ecommerce.Springboot.Exception.InvalidCustomerException;
import com.example.Ecommerce.Springboot.Exception.InvalidProductException;
import com.example.Ecommerce.Springboot.Model.Cart;
import com.example.Ecommerce.Springboot.Model.Customer;
import com.example.Ecommerce.Springboot.Model.Item;
import com.example.Ecommerce.Springboot.Model.Product;
import com.example.Ecommerce.Springboot.Repository.CustomerRepository;
import com.example.Ecommerce.Springboot.Repository.ItemRepository;
import com.example.Ecommerce.Springboot.Repository.ProductRepository;
import com.example.Ecommerce.Springboot.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {
        Customer customer;
        try{
            customer=customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("  Customer Id is Invalid !!  ");
        }
        Product product;
        try{
            product=productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new InvalidProductException( "  Product Id is Invalid !!  ");
        }
        if(itemRequestDto.getRequiredQuantity()> product.getQuantity() || product.getProductStatus()!= ProductStatus.AVAILABLE){
            throw new Exception("Product Out Of Stock!!!");
        }

        Item item= ItemTransformer.ItemRequestToItem(itemRequestDto);

        // item.setCart(customer.getCart());
        item.setProduct(product);

        product.getItemList().add(item);
        // Product savedProduct=productRepository.save(product);

        // int size=product.getItemList().size();
        return itemRepository.save(item);

    }
}
