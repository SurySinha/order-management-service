package com.retailer.ordermanagement.controller;

import com.retailer.ordermanagement.OrderManagementServiceApplication;
import com.retailer.ordermanagement.dto.OrderDTO;
import com.retailer.ordermanagement.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@Import(OrderManagementServiceApplication.class)
@Profile("test")
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;  // Mock the service layer

    @Test
    @WithMockUser(username = "user", roles = {"USER", "ADMIN"})
    void createOrder_ShouldReturnCreatedOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        when(orderService.createOrder(any(OrderDTO.class))).thenReturn(orderDTO);

        mockMvc.perform(post("/api/v1/orders")
                        .with(csrf())  // Ensure CSRF token is present
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"customerId\": 123, \"placementDate\": \"2024-09-08\", \"status\": \"NEW\" }"))  // Valid JSON
                .andExpect(status().is4xxClientError());
    }
}