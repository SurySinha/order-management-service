
package com.retailer.ordermanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailer.ordermanagement.dto.OrderDTO;
import com.retailer.ordermanagement.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void createOrder_ShouldReturnCreatedOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO(null, 123L, LocalDate.now(), "NEW");
        OrderDTO createdOrder = new OrderDTO(1L, 123L, LocalDate.now(), "NEW");

        when(orderService.createOrder(any(OrderDTO.class))).thenReturn(createdOrder);

        mockMvc.perform(post("/api/v1/orders")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.customerId").value(123))
                .andExpect(jsonPath("$.status").value("NEW"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateOrder_ShouldReturnUpdatedOrder() throws Exception {
        Long orderId = 1L;
        OrderDTO orderDTO = new OrderDTO(orderId, 123L, LocalDate.now(), "UPDATED");

        when(orderService.updateOrder(eq(orderId), any(OrderDTO.class))).thenReturn(orderDTO);

        mockMvc.perform(put("/api/v1/orders/{id}", orderId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderId))
                .andExpect(jsonPath("$.customerId").value(123))
                .andExpect(jsonPath("$.status").value("UPDATED"));
    }

    @Test
    @WithMockUser
    void getOrder_ShouldReturnOrder() throws Exception {
        Long orderId = 1L;
        OrderDTO orderDTO = new OrderDTO(orderId, 123L, LocalDate.now(), "PROCESSING");

        when(orderService.getOrder(orderId)).thenReturn(orderDTO);

        mockMvc.perform(get("/api/v1/orders/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderId))
                .andExpect(jsonPath("$.customerId").value(123))
                .andExpect(jsonPath("$.status").value("PROCESSING"));
    }

    @Test
    @WithMockUser
    void searchOrders_ShouldReturnListOfOrders() throws Exception {
        OrderDTO order1 = new OrderDTO(1L, 123L, LocalDate.now(), "NEW");
        OrderDTO order2 = new OrderDTO(2L, 123L, LocalDate.now(), "PROCESSING");
        List<OrderDTO> orders = Arrays.asList(order1, order2);

        when(orderService.searchOrders(123L, null)).thenReturn(orders);

        mockMvc.perform(get("/api/v1/orders")
                        .param("customerId", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[0].status").value("NEW"))
                .andExpect(jsonPath("$[1].status").value("PROCESSING"));
    }

}