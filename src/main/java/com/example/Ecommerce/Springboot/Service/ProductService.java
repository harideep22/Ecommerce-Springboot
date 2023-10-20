package com.example.Ecommerce.Springboot.Service;

import com.example.Ecommerce.Springboot.Dto.RequestDto.ProductRequestDto;
import com.example.Ecommerce.Springboot.Dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.Springboot.Enum.ProductCategory;
import com.example.Ecommerce.Springboot.Enum.ProductStatus;
import com.example.Ecommerce.Springboot.Exception.InvalidSellerException;
import com.example.Ecommerce.Springboot.Model.Item;
import com.example.Ecommerce.Springboot.Model.Ordered;
import com.example.Ecommerce.Springboot.Model.Product;
import com.example.Ecommerce.Springboot.Model.Seller;
import com.example.Ecommerce.Springboot.Repository.ProductRepository;
import com.example.Ecommerce.Springboot.Repository.SellerRepository;
import com.example.Ecommerce.Springboot.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PrinterMessageFromOperator;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {
        Seller seller;
        try{
            seller=sellerRepository.findById(productRequestDto.getSellerId()).get();

        }
        catch (Exception e){
            throw new InvalidSellerException("Seller doesnt Exception");
        }
        Product product= ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        seller.getProducts().add(product);
        sellerRepository.save(seller);          // saves both seller and product

        return ProductTransformer.ProductToProductResponseDto(product);

    }
    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category){
        List<Product> products=productRepository.findByProductCategory(category);

        List<ProductResponseDto> productResponseDtoList=new ArrayList<>();
        for(Product product:products){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtoList;
    }

    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price,String productCategory){
        List<Product> products=productRepository.getAllProductsByPriceAndCategory(price,productCategory);
        List<ProductResponseDto> productResponseDtoList=new ArrayList<>();
        for(Product product:products){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtoList;
    }

    public void decreaseProductQuantity(Item item) throws Exception {

            Product product=item.getProduct();
            int quantity= item.getRequiredQuantity();
            int currentQuantity=product.getQuantity();
            if(quantity>currentQuantity){
                throw new Exception("Out of Stock");
            }
            product.setQuantity(currentQuantity-quantity);

            if(product.getQuantity()==0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
    }
    public List<ProductResponseDto> getAllProductsByEmailId(String emailId){
        Seller seller=sellerRepository.findByEmailId(emailId);
        List<Product> products=seller.getProducts();
        List<ProductResponseDto> productResponseDtos=new ArrayList<>();

        for(Product product: products){
            ProductResponseDto productResponseDto=ProductTransformer.ProductToProductResponseDto(product);

            productResponseDtos.add(productResponseDto);
        }

        return productResponseDtos;
    }

    public List<ProductResponseDto> getAllOutOfStockProducts(){
        List<Product> productList=productRepository.findAll();
        List<Product> productListOutOfSTOCK=new ArrayList<>();
        List<ProductResponseDto> productResponseDtoList=new ArrayList<>();

        for (Product product: productList){
            if(product.getProductStatus()==ProductStatus.OUT_OF_STOCK){
                productListOutOfSTOCK.add(product);
            }
        }
        for (Product product:productListOutOfSTOCK){
            ProductResponseDto productResponseDto=ProductTransformer.ProductToProductResponseDto(product);
            productResponseDtoList.add(productResponseDto);
        }
        return  productResponseDtoList;
    }


}
