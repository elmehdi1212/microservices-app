package com.oubari.inventoryservice.controller;

import com.oubari.inventoryservice.dto.InventoryResponse;
import com.oubari.inventoryservice.model.Inventory;
import com.oubari.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController
{
    private final InventoryService inventoryService;

    @GetMapping("sku-code")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> checkStock(@RequestParam List<String>  skuCodes){
        log.info("Received inventory check request for skucodes {} ", skuCodes);

        return inventoryService.isInStock(skuCodes);


    }
}
