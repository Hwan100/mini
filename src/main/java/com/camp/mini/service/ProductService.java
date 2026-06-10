package com.camp.mini.service;

import com.camp.mini.dto.ProductDto;
import com.camp.mini.dto.ProductDto.Response;
import com.camp.mini.dto.ProductDto.Update;
import java.util.List;


public interface ProductService {
    //상품 등록
    Long createProduct(ProductDto.Create createDto);

    //상품 조회
    Response getProduct(Long id);

    List<Response> getProducts();

    void updateProduct(Long id, Update update);

    void deleteProduct(Long id);
}
