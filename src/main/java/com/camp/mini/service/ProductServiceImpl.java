package com.camp.mini.service;

import com.camp.mini.domain.Product;
import com.camp.mini.dto.ProductDto;
import com.camp.mini.dto.ProductDto.Response;
import com.camp.mini.dto.ProductDto.Update;
import com.camp.mini.enums.Status;
import com.camp.mini.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Long createProduct(ProductDto.Create createDto){
        Product product = createDto.toEntity();
        productRepository.save(product);

        return product.getId();
    }

    @Override
    public ProductDto.Response getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품이 없습니다."));

        return ProductDto.Response.toDto(product);
    }

    @Override
    public List<Response> getProducts() {
        List<Product> products = productRepository.findByStatus(Status.Product.ACTIVE);

        return products.stream()
                .map(ProductDto.Response::toDto)
                .toList();
    }

    @Override
    public void updateProduct(Long id, Update update) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("상품이 없습니다."));

        product.update(update);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품이 없습니다."));

        product.delete();
    }

}
