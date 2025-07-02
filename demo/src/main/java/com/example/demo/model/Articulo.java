package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Articulo")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Articulo;

    @Column(name = "Nombre_Articulo", length = 255)
    private String nombreArticulo;

    @Column(name = "Marca", length = 100)
    private String marca;

    @Column(name = "Precio", precision = 10, scale = 4)
    private BigDecimal precio;

    @Column(name = "Stock")
    private Integer stock;
}
