package dev.abhisek.springreactivemongocrud.util;

import org.springframework.beans.BeanUtils;

import dev.abhisek.springreactivemongocrud.dto.ProductDto;
import dev.abhisek.springreactivemongocrud.entity.Product;

public class AppUtils {

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
}
