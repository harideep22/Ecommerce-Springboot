package com.example.Ecommerce.Springboot.Dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequestDto {
    int customerId;
    int productId;
    int requiredQuantity;

}
