package com.example.Ecommerce.Springboot.Dto.RequestDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CheckOutCartRequestDto {

    int customerId;

    String cardNo;

    int cvv;

}
