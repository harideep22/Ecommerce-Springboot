package com.example.Ecommerce.Springboot.Dto.RequestDto;

import com.example.Ecommerce.Springboot.Enum.CardType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {

    String mobNo;
    int cvv;

    Date expiryDate;

    String cardNo;

    CardType cardType;

}
