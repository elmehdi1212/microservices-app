package com.oubari.inventoryservice.dto;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InventoryResponse {

    private String skuCode;
    private Boolean isInStock;
}
