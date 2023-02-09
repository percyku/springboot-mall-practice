package com.percyku.springbootmallprarice.service;

import com.percyku.springbootmallprarice.dto.ProductRequest;
import com.percyku.springbootmallprarice.model.Product;

import java.util.List;

public interface ProductService {



    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);


    void updateProduct(Integer productId,ProductRequest productRequest);



}
