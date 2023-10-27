package com.stock.tracker.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.stock.tracker.dto.StockDTO;
import com.stock.tracker.model.Product;
import com.stock.tracker.model.Stock;
import com.stock.tracker.model.User;
import com.stock.tracker.repository.ProductRepository;
import com.stock.tracker.repository.StockRepository;
import com.stock.tracker.repository.UserRepository;
import com.stock.tracker.util.MapperUtil;

@Service
public class StockService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final MapperUtil mapperUtil;

    public StockService(StockRepository stockRepository, MapperUtil mapperUtil, ProductRepository productRepository,
            UserRepository userRepository) {
        this.stockRepository = stockRepository;
        this.mapperUtil = mapperUtil;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all stocks from the database.
     *
     * @return A list of StockDTO containing information about all stocks.
     */
    public List<StockDTO> getAllStocks() {
        // Retrieve all stocks from the database.
        List<Stock> stocks = stockRepository.findAll();

        // Map the list of Stock entities to a list of StockDTOs using a mapper.
        List<StockDTO> stockDTOs = stocks.stream()
                .map(stock -> mapperUtil.convertToDTO(stock, StockDTO.class))
                .collect(Collectors.toList());

        return stockDTOs;
    }

    /**
     * Retrieves a stock by its unique ID from the database.
     *
     * @param id The unique ID of the stock to retrieve.
     * @return A StockDTO containing information about the retrieved stock, or null
     *         if not found.
     */
    public StockDTO getStockById(UUID id) {
        // Retrieve a stock by its unique ID from the database.
        Stock stock = stockRepository.findById(id).orElse(null);

        // Map the Stock entity to a StockDTO using a mapper.
        StockDTO stockDTO = mapperUtil.convertToDTO(stock, StockDTO.class);

        return stockDTO;
    }

    /**
     * Creates a new stock with the provided information and saves it in the
     * database.
     *
     * @param userId    The unique ID of the user associated with the stock.
     * @param productId The unique ID of the product associated with the stock.
     * @param quantity  The quantity of the stock.
     * @return A StockDTO containing information about the created stock, or null if
     *         user or product is not found.
     */
    public StockDTO createStock(UUID userId, UUID productId, int quantity) {
        // Create a new Stock entity with the provided parameters.
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (user == null || product == null) {
            return null;
        }

        Stock stock = new Stock(user, product, quantity);

        // Save the newly created stock in the database.
        Stock createdStock = stockRepository.save(stock);

        // Map the created Stock entity to a StockDTO using a mapper.
        StockDTO stockDTO = mapperUtil.convertToDTO(createdStock, StockDTO.class);

        return stockDTO;
    }

    /**
     * Updates an existing stock with the provided information.
     *
     * @param id       The unique ID of the stock to update.
     * @param quantity The new quantity for the stock (or a value < 0 to keep the
     *                 current quantity).
     * @return A StockDTO containing information about the updated stock, or null if
     *         the stock is not found.
     */
    public StockDTO updateStock(UUID id, int quantity) {
        // Update an existing stock in the database.
        // If the quantity parameter is < 0, the quantity is not updated.

        // Retrieve the stock by its unique ID.
        Stock stock = stockRepository.findById(id).orElse(null);

        if (stock != null) {
            // Update stock quantity if a valid parameter is provided.
            if (quantity >= 0) {
                stock.setQuantity(quantity);
            }

            // Save the updated stock in the database.
            stockRepository.save(stock);

            // Map the updated Stock entity to a StockDTO using a mapper.
            StockDTO stockDTO = mapperUtil.convertToDTO(stock, StockDTO.class);

            return stockDTO;
        }

        return null;
    }

    /**
     * Deletes a stock by its unique ID from the database.
     *
     * @param id The unique ID of the stock to delete.
     * @return true if the stock is successfully deleted, false if the stock is not
     *         found.
     */
    public boolean deleteStock(UUID id) {
        // Delete a stock by its unique ID.
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
