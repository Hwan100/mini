package com.camp.mini.controller;

import com.camp.mini.dto.ProductDto;
import com.camp.mini.dto.ProductDto.Response;
import com.camp.mini.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
//@CrossOrigin(origins = "")
public class ProductController {

    private final ProductService productService;

    //상품 등록
    @PostMapping
    public ResponseEntity<Long> addProduct(@Valid @RequestBody ProductDto.Create createDto){
        System.out.println(createDto.getName());
        Long id = productService.createProduct(createDto);
        return ResponseEntity.ok(id);
    }

    //상품 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto.Response> getProduct(@PathVariable Long id){
        ProductDto.Response product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    //상품 전체조회
    @GetMapping
    public ResponseEntity<List<ProductDto.Response>> getProducts(){
        List<ProductDto.Response> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    //상품 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody ProductDto.Update update){
        productService.updateProduct(id, update);
        return ResponseEntity.noContent().build();
    }

    //상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
