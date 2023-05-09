package com.example.Ecommerce.Springboot.Service;

import com.example.Ecommerce.Springboot.Dto.RequestDto.CustomerRequestDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.CustomerResponseDto;
import com.example.Ecommerce.Springboot.Exception.MobileNoAlreadyPresentException;
import com.example.Ecommerce.Springboot.Model.Cart;
import com.example.Ecommerce.Springboot.Model.Customer;
import com.example.Ecommerce.Springboot.Repository.CustomerRepository;
import com.example.Ecommerce.Springboot.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyPresentException {

        if(customerRepository.findByMobNo(customerRequestDto.getMobNo())!=null){
            throw new MobileNoAlreadyPresentException("Sorry! Customer Already exists!");
        }
        Customer customer= CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart= Cart.builder()
                .numberOfItems(0)
                .customer(customer)
                .cartTotal(0)
                .build();
        customer.setCart(cart);

        Customer savedCustomer=customerRepository.save(customer); //saves customer and cart in repository

        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }
}
