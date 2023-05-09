package com.example.Ecommerce.Springboot.Controller;

import com.example.Ecommerce.Springboot.Dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Springboot.Exception.EmailAlreadyPresentException;
import com.example.Ecommerce.Springboot.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {
        try{
            SellerResponseDto sellerResponseDto=sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get a seller by email

    //get by id

    //get all seller

    //update seller info based on emailid

    // delete a seller based on email

    // delete by id

    // get all sellers of particular age


}
