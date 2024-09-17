package com.rajan.dreamshops.controllers;

import com.rajan.dreamshops.dto.ProductDto;
import com.rajan.dreamshops.entity.Product;
import com.rajan.dreamshops.exception.AlreadyExistsException;
import com.rajan.dreamshops.exception.ResourceNotFoundException;
import com.rajan.dreamshops.request.AddProductRequest;
import com.rajan.dreamshops.request.UpdateProductRequest;
import com.rajan.dreamshops.response.ApiResponse;
import com.rajan.dreamshops.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> allProducts = productService.getAllProducts();
           List<ProductDto> convertedProducts = productService.getConvertedProducts(allProducts);
            return ResponseEntity.ok(new ApiResponse("Found!", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Not Found", e.getMessage()));
        }
    }

    @GetMapping("/product/{productID}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable long productID) {
        try {
            Product product = productService.getProductById(productID);
            ProductDto productDto = productService.convertToDto(product);

            return ResponseEntity.ok(new ApiResponse("Found!", productDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product productAdded = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Product Created Successfully", productAdded));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable long productId,
                                                     @RequestBody UpdateProductRequest product) {
        try {
            Product updatedProduct = productService.updateProduct(product, productId);
            return ResponseEntity.ok(new ApiResponse("Product Updated Successfully", updatedProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product Deleted Successfully", productId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName,
                                                                @RequestParam String productName) {
        try {
            List<Product> productsByBrandAndName = productService.getProductsByBrandAndName(brandName, productName);
            if (productsByBrandAndName.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product Not Found", null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProducts(productsByBrandAndName);
            return ResponseEntity.ok(new ApiResponse("Found!", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/by/category-and-name")
    public ResponseEntity<ApiResponse> getProductByCategoryAndName(@RequestParam String categoryName,
                                                                   @RequestParam String brandName){
        try {
            List<Product> productsByCategoryAndBrand =
                    productService.getProductsByCategoryAndBrand(categoryName, brandName);
            if(productsByCategoryAndBrand.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product Not Found", null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProducts(productsByCategoryAndBrand);
            return ResponseEntity.ok(new ApiResponse("Found!", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/by-name/{name}/product")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name) {
        try {
            List<Product> productsByName = productService.getProductsByName(name);
            if (productsByName.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product Not Found", null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProducts(productsByName);
            return ResponseEntity.ok(new ApiResponse("Found!", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/by/brand")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brandName) {
        try {
            List<Product> productsByBrand = productService.getProductsByBrand(brandName);
            if (productsByBrand.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product Not Found", null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProducts(productsByBrand);
            return ResponseEntity.ok(new ApiResponse("Found!", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<ApiResponse> getProductByCategory(@PathVariable String category) {
        try {
            List<Product> productsByCategory = productService.getProductsByCategory(category);
            if (productsByCategory.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product Not Found", null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProducts(productsByCategory);
            return ResponseEntity.ok(new ApiResponse("Found!", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/count/by/brand-and-name")
    public ResponseEntity<ApiResponse> countProductByBrandAndName(@RequestParam String brand, @RequestParam String productName) {
        try {
            Long l = productService.countProductsByBrandAndName(brand, productName);
            if (l == 0) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product Not Found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Found!", l));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }


}
