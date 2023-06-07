package dev.abhisek.springreactivemongocrud.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.abhisek.springreactivemongocrud.dto.ProductDto;
import dev.abhisek.springreactivemongocrud.service.ProductService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public Flux<ProductDto> getProducts() {
        return service.getAllProduct();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable String id) {
        return service.getProductById(id);

    }

    @GetMapping("/range")
    public Flux<ProductDto> getProductBetweenRange(
            @RequestParam("min") Double min,
            @RequestParam("max") Double max) {
        return service.getProductInRange(min, max);
    }

    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtMono) {
        return service.saveProduct(productDtMono);
    }

    @PutMapping("/{id}")
    public Mono<ProductDto> updateProduct(
            @RequestBody Mono<ProductDto> productDtMono,
            @PathVariable String id) {
        return service.updateProduct(productDtMono, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return service.deleteProduct(id);
    }
}
