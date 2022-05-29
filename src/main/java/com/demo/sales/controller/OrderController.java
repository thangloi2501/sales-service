package com.demo.sales.controller;

import com.demo.sales.dto.ApiResponse;
import com.demo.sales.dto.order.OrderImportResult;
import com.demo.sales.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Loi Nguyen
 *
 */
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping(value = "/api/v1/orders/import")
    public ApiResponse<OrderImportResult> importOrders(@RequestParam MultipartFile file) throws IOException {
        return ApiResponse.<OrderImportResult>builder()
                          .data(orderService.importOrders(file))
                          .build();
    }
}
