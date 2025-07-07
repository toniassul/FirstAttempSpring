package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Articulo;

public interface ArticuloService {
    List<Articulo> getAllArticulos();
    Articulo getArticuloById(Integer id);
    Articulo createArticulo(Articulo articulo);
    Articulo updateArticulo(Integer id, Articulo articuloDetails);
    void deleteArticulo(Integer id);
    List<Articulo> buscarPorNombre(String nombreArticulo);
}
