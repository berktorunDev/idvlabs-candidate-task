package com.stock.tracker.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.tracker.dto.ProductDTO;
import com.stock.tracker.enums.ProductCategory;
import com.stock.tracker.request.CreateProductRequest;
import com.stock.tracker.request.UpdateProductRequest;
import com.stock.tracker.service.ProductService;
import com.stock.tracker.util.ResponseHandler;

@RestController
@RequestMapping("/api/public/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseHandler.successResponse(HttpStatus.OK, "Products retrieved successfully", products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable UUID id) {
        ProductDTO product = productService.getProductById(id);
        if (product != null) {
            return ResponseHandler.successResponse(HttpStatus.OK, "Product retrieved successfully", product);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Product not found");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        String name = createProductRequest.getName();
        String description = createProductRequest.getDescription();
        double price = createProductRequest.getPrice();
        ProductCategory category = createProductRequest.getCategory();

        if (name == null || description == null || price <= 0 || category == null) {
            return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST,
                    "Name, description, price, and category are required");
        }

        ProductDTO createdProduct = productService.createProduct(name, description, price, category);
        if (createdProduct != null) {
            return ResponseHandler.successResponse(HttpStatus.CREATED, "Product created successfully", createdProduct);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Product creation failed");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable UUID id,
            @RequestBody UpdateProductRequest updateProductRequest) {
        String name = updateProductRequest.getName();
        String description = updateProductRequest.getDescription();
        double price = updateProductRequest.getPrice();
        ProductCategory category = updateProductRequest.getCategory();

        if (name == null && description == null && price <= 0 && category == null) {
            return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, "No update fields provided");
        }

        ProductDTO updatedProduct = productService.updateProduct(id, name, description, price, category);
        if (updatedProduct != null) {
            return ResponseHandler.successResponse(HttpStatus.OK, "Product updated successfully", updatedProduct);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Product not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID id) {
        boolean result = productService.deleteProduct(id);
        if (result) {
            return ResponseHandler.successResponse(HttpStatus.OK, "Product deleted successfully", null);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Product not found");
        }
    }
}
