package com.example.Ecommerce.Springboot.Transformer;

import com.example.Ecommerce.Springboot.Dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.ItemResponseDto;
import com.example.Ecommerce.Springboot.Model.Item;

public class ItemTransformer {
    public static Item ItemRequestToItem(ItemRequestDto itemRequestDto){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }

}
