package org.example.crmdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderPaginationResponseDto {
    private Long total;
    private Integer perPage;
    private Integer nextPage;
    private Integer prevPage;
    private List<OrderDTO> data;
}
