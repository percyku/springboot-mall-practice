package com.percyku.springbootmallprarice.dao;



import com.percyku.springbootmallprarice.model.Product;

import java.util.List;

public interface ProductDao {

    Product getProductById(Integer productId);
}
