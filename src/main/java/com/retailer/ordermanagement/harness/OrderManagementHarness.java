package com.retailer.ordermanagement.harness;


import com.retailer.ordermanagement.dto.OrderDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1/orders")
@Profile("harness")
public class OrderManagementHarness {

    private final Map<Long, OrderDTO> orderDTOHashMap = MockOrderUtil.populateOrderList(new HashMap<>());

    private Long nextId = 1L;

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementHarness.class, args);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrderDTO(@RequestBody OrderDTO OrderDTO) {
        OrderDTO.setId(nextId++);
        OrderDTO.setPlacementDate(LocalDate.now());
        orderDTOHashMap.put(OrderDTO.getId(), OrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrderDTO(@PathVariable Long id, @RequestBody OrderDTO OrderDTO) {
        if (!orderDTOHashMap.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        OrderDTO.setId(id);
        orderDTOHashMap.put(id, OrderDTO);
        return ResponseEntity.ok(OrderDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderDTO(@PathVariable Long id) {
        OrderDTO OrderDTO = orderDTOHashMap.get(id);
        if (OrderDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(OrderDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> searchOrderDTOs(
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) String status) {
        List<OrderDTO> result = new ArrayList<>();
        for (OrderDTO OrderDTO : orderDTOHashMap.values()) {
            if ((customerId == null || OrderDTO.getCustomerId().equals(customerId))
                    && (status == null || OrderDTO.getStatus().equals(status))) {
                result.add(OrderDTO);
            }
        }
        return ResponseEntity.ok(result);
    }
}