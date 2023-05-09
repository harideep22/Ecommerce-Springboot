package com.example.Ecommerce.Springboot.Transformer;

import com.example.Ecommerce.Springboot.Dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Springboot.Model.Seller;

public class SellerTransformer {
    public static Seller sellerRequestDtotoSeller(SellerRequestDto sellerRequestDto) {
            return Seller.builder()
                .name(sellerRequestDto.getName())
                .age(sellerRequestDto.getAge())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }
    public static SellerResponseDto sellerToSellerResponse(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .build();
    }
}
