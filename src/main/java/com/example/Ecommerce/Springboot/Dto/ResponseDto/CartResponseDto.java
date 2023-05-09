package com.example.Ecommerce.Springboot.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {

    Integer cartTotal;
    Integer numberOfItems;
    String customerName;
    List<ItemResponseDto> items;
}
