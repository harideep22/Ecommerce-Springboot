package com.example.Ecommerce.Springboot.Service;

import com.example.Ecommerce.Springboot.Dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Springboot.Exception.EmailAlreadyPresentException;
import com.example.Ecommerce.Springboot.Model.Seller;
import com.example.Ecommerce.Springboot.Repository.SellerRepository;
import com.example.Ecommerce.Springboot.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {
//        Seller seller=new Seller();
//        seller.setName(sellerRequestDto.getName());
//        seller.setAge(sellerRequestDto.getAge());
//        seller.setEmailId(sellerRequestDto.getEmailId());
//        seller.setMobNo(sellerRequestDto.getMobNo());

        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId())!=null)
            throw new EmailAlreadyPresentException("Email Id already registered");

        Seller seller= SellerTransformer.sellerRequestDtotoSeller(sellerRequestDto);
        Seller savedSeller=sellerRepository.save(seller);

        SellerResponseDto sellerResponseDto=SellerTransformer.sellerToSellerResponse(savedSeller);
        return sellerResponseDto;
    }
}
