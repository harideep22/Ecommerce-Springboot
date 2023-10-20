package com.example.Ecommerce.Springboot.Controller;

import com.example.Ecommerce.Springboot.Dto.RequestDto.CheckOutCartRequestDto;
import com.example.Ecommerce.Springboot.Dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.Springboot.Model.Item;
import com.example.Ecommerce.Springboot.Service.CartService;
import com.example.Ecommerce.Springboot.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addCart(@RequestBody ItemRequestDto itemRequestDto) throws Exception {
        try {
            Item savedItem = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto=cartService.saveCart(itemRequestDto.getCustomerId(),savedItem);
            return new ResponseEntity(cartResponseDto,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/checkout")
    public OrderResponseDto checkOutCard(@RequestBody CheckOutCartRequestDto checkOutCartRequestDto) throws Exception {
        return cartService.checkOutCart(checkOutCartRequestDto);
    }
}
