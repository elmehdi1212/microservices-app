package com.oubari.inventoryservice.util;

import com.oubari.inventoryservice.model.Inventory;
import com.oubari.inventoryservice.repository.InventoryRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;
    @Override
    public void run(String... args) throws Exception {

                Inventory inventory=new Inventory();
                inventory.setSkuCode("sumsung_s");
                inventory.setQuantity(3);

                Inventory inventory1=new Inventory();
                inventory1.setSkuCode("iphone_15");
                inventory1.setQuantity(0);

                inventoryRepository.save(inventory);
                inventoryRepository.save(inventory1);

    }
}
