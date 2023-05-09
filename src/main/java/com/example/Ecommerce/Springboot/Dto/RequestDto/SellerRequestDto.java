package com.example.Ecommerce.Springboot.Dto.RequestDto;


import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerRequestDto {
    String name;

    String emailId;

    Integer age;

    String mobNo;
}
