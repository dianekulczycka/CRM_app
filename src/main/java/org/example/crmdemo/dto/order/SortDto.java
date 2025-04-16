package org.example.crmdemo.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortDto {
    private String order = "id";
    private String direction = "desc";
    private Integer page = 1;
}
