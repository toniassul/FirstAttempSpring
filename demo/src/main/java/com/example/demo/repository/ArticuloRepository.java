package com.example.demo.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {
    Articulo findByNombreArticulo(String nombreArticulo);
    List<Articulo> findByNombreArticuloContainingIgnoreCase(String nombreArticulo);
}
