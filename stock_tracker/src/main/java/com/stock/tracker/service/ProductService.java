package com.stock.tracker.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.stock.tracker.dto.ProductDTO;
import com.stock.tracker.enums.ProductCategory;
import com.stock.tracker.model.Product;
import com.stock.tracker.repository.ProductRepository;
import com.stock.tracker.util.MapperUtil;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;

    public ProductService(ProductRepository productRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
    }

    /**
     * Retrieves all products from the database.
     *
     * @return A list of ProductDTO containing information about all products.
     */
    public List<ProductDTO> getAllProducts() {
        // Retrieve all products from the database.
        List<Product> products = productRepository.findAll();

        // Map the list of Product entities to a list of ProductDTOs using a mapper.
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> mapperUtil.convertToDTO(product, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOs;
    }

    /**
     * Retrieves a product by its unique ID from the database.
     *
     * @param id The unique ID of the product to retrieve.
     * @return A ProductDTO containing information about the retrieved product, or
     *         null if not found.
     */
    public ProductDTO getProductById(UUID id) {
        // Retrieve a product by its unique ID from the database.
        Product product = productRepository.findById(id).orElse(null);

        // Map the Product entity to a ProductDTO using a mapper.
        ProductDTO productDTO = mapperUtil.convertToDTO(product, ProductDTO.class);

        return productDTO;
    }

    /**
     * Creates a new product with the provided information and saves it in the
     * database.
     *
     * @param name        The name of the product.
     * @param description The description of the product.
     * @param price       The price of the product.
     * @param category    The category of the product.
     * @return A ProductDTO containing information about the created product.
     */
    public ProductDTO createProduct(String name, String description, double price, ProductCategory category) {
        // Create a new Product entity with the provided parameters.
        Product product = new Product(name, description, price, category);

        // Save the newly created product in the database.
        Product createdProduct = productRepository.save(product);

        // Map the created Product entity to a ProductDTO using a mapper.
        ProductDTO productDTO = mapperUtil.convertToDTO(createdProduct, ProductDTO.class);

        return productDTO;
    }

    /**
     * Updates an existing product with the provided information.
     *
     * @param id          The unique ID of the product to update.
     * @param name        The new name for the product (or null to keep the current
     *                    name).
     * @param description The new description for the product (or null to keep the
     *                    current description).
     * @param price       The new price for the product (or a value <= 0 to keep the
     *                    current price).
     * @param category    The new category for the product (or null to keep the
     *                    current category).
     * @return A ProductDTO containing information about the updated product, or
     *         null if the product is not found.
     */
    public ProductDTO updateProduct(UUID id, String name, String description, double price, ProductCategory category) {
        // Update an existing product in the database.
        // If any of the update parameters are null or <= 0, the corresponding fields
        // are not updated.

        // Retrieve the product by its unique ID.
        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            // Update product fields if valid parameters are provided.
            if (name != null) {
                product.setName(name);
            }

            if (description != null) {
                product.setDescription(description);
            }

            if (price > 0) {
                product.setPrice(price);
            }

            if (category != null) {
                product.setCategory(category);
            }

            // Save the updated product in the database.
            productRepository.save(product);

            // Map the updated Product entity to a ProductDTO using a mapper.
            ProductDTO productDTO = mapperUtil.convertToDTO(product, ProductDTO.class);

            return productDTO;
        }

        return null;
    }

    /**
     * Deletes a product by its unique ID from the database.
     *
     * @param id The unique ID of the product to delete.
     * @return true if the product is successfully deleted, false if the product is
     *         not found.
     */
    public boolean deleteProduct(UUID id) {
        // Delete a product by its unique ID.
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
