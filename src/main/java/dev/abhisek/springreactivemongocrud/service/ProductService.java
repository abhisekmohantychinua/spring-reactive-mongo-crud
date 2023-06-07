package dev.abhisek.springreactivemongocrud.service;

import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import dev.abhisek.springreactivemongocrud.dto.ProductDto;
import dev.abhisek.springreactivemongocrud.repository.ProductRepository;
import dev.abhisek.springreactivemongocrud.util.AppUtils;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Flux<ProductDto> getAllProduct() {
        return repository
                .findAll()
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> getProductById(String id) {
        return repository
                .findById(id)
                .map(AppUtils::entityToDto);

    }

    public Flux<ProductDto> getProductInRange(Double min, Double max) {
        return repository
                .findByPriceBetween(Range.closed(min, max))
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono
                .map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
        return repository
                .findById(id)
                .flatMap(product -> productDtoMono.map(AppUtils::dtoToEntity))
                .doOnNext(product -> product.setId(id))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteProduct(String id) {
        return repository.deleteById(id);
    }

}
