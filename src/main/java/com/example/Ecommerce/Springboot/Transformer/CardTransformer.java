package com.example.Ecommerce.Springboot.Transformer;

import com.example.Ecommerce.Springboot.Dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.Springboot.Model.Card;

public class CardTransformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .expiryDate(cardRequestDto.getExpiryDate())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .build();
    }
}
