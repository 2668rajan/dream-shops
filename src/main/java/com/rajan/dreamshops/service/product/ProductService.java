package com.rajan.dreamshops.service.product;

import com.rajan.dreamshops.dto.ProductDto;
import com.rajan.dreamshops.entity.Product;
import com.rajan.dreamshops.request.AddProductRequest;
import com.rajan.dreamshops.request.UpdateProductRequest;
import org.hibernate.sql.Update;

import java.util.List;

public interface ProductService {

    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(UpdateProductRequest product, Long productId);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);
}
