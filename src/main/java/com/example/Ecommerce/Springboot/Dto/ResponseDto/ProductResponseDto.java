package com.example.Ecommerce.Springboot.Dto.ResponseDto;

import com.example.Ecommerce.Springboot.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {

    String productName;
    String sellerName;
    int quantity;
    ProductStatus productStatus;
}
