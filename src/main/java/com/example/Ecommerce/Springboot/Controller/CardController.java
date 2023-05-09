package com.example.Ecommerce.Springboot.Controller;

import com.example.Ecommerce.Springboot.Dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.CardResponseDto;
import com.example.Ecommerce.Springboot.Exception.InvalidCustomerException;
import com.example.Ecommerce.Springboot.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) throws InvalidCustomerException {
        try {
            CardResponseDto cardResponseDto=cardService.addCard(cardRequestDto);
            return new ResponseEntity<>(cardResponseDto, HttpStatus.CREATED);
        }
        catch(InvalidCustomerException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
