package com.example.Ecommerce.Springboot.Transformer;

import com.example.Ecommerce.Springboot.Dto.RequestDto.ProductRequestDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.Springboot.Enum.ProductStatus;
import com.example.Ecommerce.Springboot.Model.Product;

public class ProductTransformer {
    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){

        return Product.builder()
                .name(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .productCategory(productRequestDto.getProductCategory())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){

        return ProductResponseDto.builder()
                .productName(product.getName())
                .productStatus(product.getProductStatus())
                .quantity(product.getQuantity())
                .sellerName(product.getSeller().getName())
                .build();
    }
}
