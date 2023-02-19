package com.percyku.springbootmallprarice.dao;



import com.percyku.springbootmallprarice.dto.ProductQueryParams;
import com.percyku.springbootmallprarice.dto.ProductRequest;
import com.percyku.springbootmallprarice.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void updateStock(Integer productId, Integer stock);

    void deleteProductById(Integer productId);
}
