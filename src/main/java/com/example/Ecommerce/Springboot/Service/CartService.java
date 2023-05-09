package com.example.Ecommerce.Springboot.Service;

import com.example.Ecommerce.Springboot.Dto.RequestDto.CheckOutCartRequestDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.ItemResponseDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.Springboot.Exception.InvalidCardException;
import com.example.Ecommerce.Springboot.Exception.InvalidCustomerException;
import com.example.Ecommerce.Springboot.Model.*;
import com.example.Ecommerce.Springboot.Repository.CardRepository;
import com.example.Ecommerce.Springboot.Repository.CartRepository;
import com.example.Ecommerce.Springboot.Repository.CustomerRepository;
import com.example.Ecommerce.Springboot.Repository.OrderRepository;
import com.example.Ecommerce.Springboot.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    public CartResponseDto saveCart(Integer customerId, Item item){
        Customer customer=customerRepository.findById(customerId).get();
        Cart cart=customer.getCart();

        int newTotal= cart.getCartTotal()+item.getRequiredQuantity()*item.getProduct().getPrice();
        cart.setCartTotal(newTotal);
        cart.getItems().add(item);

        cart.setNumberOfItems(cart.getItems().size());
        item.setCart(cart);
        Cart savedCart=cartRepository.save(cart);

        CartResponseDto cartResponseDto=CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .numberOfItems(cart.getNumberOfItems())
                .build();

        List<ItemResponseDto> itemResponseDtoList=new ArrayList<>();
        for(Item item1: cart.getItems()){
            ItemResponseDto itemResponseDto=new ItemResponseDto();
            itemResponseDto.setPriceOfOneItem(item1.getProduct().getPrice());
            itemResponseDto.setQuantity(item1.getRequiredQuantity());
            itemResponseDto.setProductName(item1.getProduct().getName());
            itemResponseDto.setTotalPrice(item1.getRequiredQuantity()*item.getProduct().getPrice());

            itemResponseDtoList.add(itemResponseDto);
        }
        cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;
    }

    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws Exception {
        Customer customer;
        try{
            customer=customerRepository.findById(checkOutCartRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer is Invalid !!");
        }
        Card card=cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());


        if(card==null || card.getCvv()!= checkOutCartRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Card details are wrong !!");
        }

        Cart cart=customer.getCart();
        if(cart.getNumberOfItems()==0)
            throw new Exception("Cart Is Empty");

        try {
            Ordered order=orderService.placeOrder(customer,card);
            customer.getOrderList().add(order);
            Ordered saveOrder=orderRepository.save(order);
            resetCart(cart);
            OrderResponseDto orderResponseDto=new OrderResponseDto();
            orderResponseDto.setOrderDate(saveOrder.getOrderDate());
            orderResponseDto.setCardUsed(saveOrder.getCardUsed());
            orderResponseDto.setCustomerName(customer.getName());
            orderResponseDto.setOrderNo(saveOrder.getOrderNo());
            orderResponseDto.setTotalValue(saveOrder.getTotalValue());

            List<ItemResponseDto> itemList=new ArrayList<>();
            for (Item item:saveOrder.getItems()){
                ItemResponseDto itemResponseDto=new ItemResponseDto();
                itemResponseDto.setPriceOfOneItem(item.getProduct().getPrice());
                itemResponseDto.setQuantity(item.getRequiredQuantity());
                itemResponseDto.setProductName(item.getProduct().getName());
                itemResponseDto.setTotalPrice(item.getRequiredQuantity()*item.getProduct().getPrice());
                itemList.add(itemResponseDto);

            }
            orderResponseDto.setItems(itemList);
            return orderResponseDto;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }


    }
    public void resetCart(Cart cart){
        cart.setCartTotal(0);
        cart.setNumberOfItems(0);
        cart.setItems(new ArrayList<>());
    }
}
