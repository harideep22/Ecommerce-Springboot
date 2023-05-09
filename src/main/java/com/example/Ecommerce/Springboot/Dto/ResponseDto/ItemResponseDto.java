package com.example.Ecommerce.Springboot.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {
    String productName;

    int priceOfOneItem;

    int totalPrice;

    int quantity;
}
