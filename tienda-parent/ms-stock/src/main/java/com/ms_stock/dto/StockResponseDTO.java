package com.ms_stock.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponseDTO {

    private Long id;
    private Long productoId;
    private Integer cantidad;
}