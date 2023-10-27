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

import com.stock.tracker.dto.StockDTO;
import com.stock.tracker.request.CreateStockRequest;
import com.stock.tracker.request.UpdateStockRequest;
import com.stock.tracker.service.StockService;
import com.stock.tracker.util.ResponseHandler;

@RestController
@RequestMapping("/api/public/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllStocks() {
        List<StockDTO> stocks = stockService.getAllStocks();
        return ResponseHandler.successResponse(HttpStatus.OK, "Stocks retrieved successfully", stocks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStockById(@PathVariable UUID id) {
        StockDTO stock = stockService.getStockById(id);
        if (stock != null) {
            return ResponseHandler.successResponse(HttpStatus.OK, "Stock retrieved successfully", stock);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Stock not found");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createStock(@RequestBody CreateStockRequest createStockRequest) {
        UUID userId = createStockRequest.getUserId();
        UUID productId = createStockRequest.getProductId();
        int quantity = createStockRequest.getQuantity();

        if (userId == null || productId == null || quantity < 0) {
            return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST,
                    "User ID, product ID, and quantity are required");
        }

        StockDTO createdStock = stockService.createStock(userId, productId, quantity);
        if (createdStock != null) {
            return ResponseHandler.successResponse(HttpStatus.CREATED, "Stock created successfully", createdStock);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Stock creation failed");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateStock(@PathVariable UUID id,
            @RequestBody UpdateStockRequest updateStockRequest) {
        int quantity = updateStockRequest.getQuantity();

        if (quantity < 0) {
            return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, "Invalid quantity");
        }

        StockDTO updatedStock = stockService.updateStock(id, quantity);
        if (updatedStock != null) {
            return ResponseHandler.successResponse(HttpStatus.OK, "Stock updated successfully", updatedStock);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Stock not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteStock(@PathVariable UUID id) {
        boolean result = stockService.deleteStock(id);
        if (result) {
            return ResponseHandler.successResponse(HttpStatus.OK, "Stock deleted successfully", null);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Stock not found");
        }
    }
}
