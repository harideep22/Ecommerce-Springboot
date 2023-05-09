package com.example.Ecommerce.Springboot.Service;

import com.example.Ecommerce.Springboot.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class OrderService {
    @Autowired
    ProductService productService;

    public Ordered placeOrder(Customer customer, Card card) throws Exception {

        Cart cart=customer.getCart();

        Ordered order=new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String cardNo=card.getCardNo();
        String maskedCardNo=generteMaskedCard(cardNo);
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);

        List<Item> orderedItems=new ArrayList<>();
        for(Item item:cart.getItems()){
            try{
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            }catch (Exception e){
                throw new Exception("Product Out of Stock");
            }
        }
        order.setItems(orderedItems);
        order.setTotalValue(cart.getCartTotal());
        return order;
    }
    public String generteMaskedCard(String cardNo){

        String maskedCardNo="";
        for (int i=0;i<cardNo.length()-4;i++){
            maskedCardNo+='X';
        }
        maskedCardNo+=cardNo.substring(cardNo.length()-4);
        return maskedCardNo;
    }
}
