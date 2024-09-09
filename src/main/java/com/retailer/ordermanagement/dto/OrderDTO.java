package com.retailer.ordermanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDTO {
    private Long id;

    @NotNull
    private Long customerId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")  // Add this to ensure correct date parsing
    private LocalDate placementDate;

    @NotNull
    private String status;


}