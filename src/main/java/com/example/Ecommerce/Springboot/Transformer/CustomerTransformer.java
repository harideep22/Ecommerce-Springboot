package com.example.Ecommerce.Springboot.Transformer;

import com.example.Ecommerce.Springboot.Dto.RequestDto.CustomerRequestDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.CustomerResponseDto;
import com.example.Ecommerce.Springboot.Model.Cart;
import com.example.Ecommerce.Springboot.Model.Customer;

public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .age(customerRequestDto.getAge())
                .mobNo(customerRequestDto.getMobNo())
                .name(customerRequestDto.getName())
                .address(customerRequestDto.getAddress())
                .emailId(customerRequestDto.getEmailId())
                .build();
    }
    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("Welcome  "+customer.getName()+"   Amazon !!!")
                .build();
    }
}
