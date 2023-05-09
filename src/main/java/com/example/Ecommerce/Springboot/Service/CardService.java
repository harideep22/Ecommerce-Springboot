package com.example.Ecommerce.Springboot.Service;

import com.example.Ecommerce.Springboot.Dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.CardResponseDto;
import com.example.Ecommerce.Springboot.Exception.InvalidCustomerException;
import com.example.Ecommerce.Springboot.Model.Card;
import com.example.Ecommerce.Springboot.Model.Customer;
import com.example.Ecommerce.Springboot.Repository.CardRepository;
import com.example.Ecommerce.Springboot.Repository.CustomerRepository;
import com.example.Ecommerce.Springboot.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {

        Customer customer=customerRepository.findByMobNo(cardRequestDto.getMobNo());
        if(customer==null){
            throw new InvalidCustomerException("Sorry ! this customer doesn't exist ");
        }
        Card card= CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        customerRepository.save(customer);
        return CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .customerName(customer.getName())
                .build();

    }
}
