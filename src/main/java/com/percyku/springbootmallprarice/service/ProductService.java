package com.percyku.springbootmallprarice.service;

import com.percyku.springbootmallprarice.dto.ProductQueryParams;
import com.percyku.springbootmallprarice.dto.ProductRequest;
import com.percyku.springbootmallprarice.model.Product;

import java.util.List;

public interface ProductService {


    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
