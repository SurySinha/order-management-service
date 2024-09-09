package com.retailer.ordermanagement.harness;

import com.retailer.ordermanagement.dto.OrderDTO;

import java.time.LocalDate;
import java.util.HashMap;

public class MockOrderUtil {

    public static HashMap<Long, OrderDTO> populateOrderList(HashMap<Long, OrderDTO> orderDTOHashMap) {
        // Adding 5 dummy entries with IDs from 1 to 5
        orderDTOHashMap.put(1L, createDummyOrder(1L, 101L, "NEW"));
        orderDTOHashMap.put(2L, createDummyOrder(2L, 102L, "PROCESSING"));
        orderDTOHashMap.put(3L, createDummyOrder(3L, 103L, "SHIPPED"));
        orderDTOHashMap.put(4L, createDummyOrder(4L, 104L, "DELIVERED"));
        orderDTOHashMap.put(5L, createDummyOrder(5L, 105L, "CANCELLED"));
        return orderDTOHashMap;
    }

    // Helper method to create dummy OrderDTO
    private static OrderDTO createDummyOrder(Long id, Long customerId, String status) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(id);
        orderDTO.setCustomerId(customerId);
        orderDTO.setPlacementDate(LocalDate.now());  // Sets current date
        orderDTO.setStatus(status);
        return orderDTO;
    }

}
