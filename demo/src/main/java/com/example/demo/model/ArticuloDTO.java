package com.example.demo.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ArticuloDTO {
    private Integer id_Articulo;
    private String nombreArticulo;
    private String marca;
    private BigDecimal precio;
    private Integer stock;
}
