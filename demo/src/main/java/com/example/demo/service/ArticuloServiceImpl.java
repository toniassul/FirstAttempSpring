package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Articulo;
import com.example.demo.repository.ArticuloRepository;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public List<Articulo> getAllArticulos() {
        return articuloRepository.findAll();
    }

    @Override
    public Articulo getArticuloById(Integer id) {
        return articuloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Articulo not found with id: " + id));
    }

    @Override
    public Articulo createArticulo(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    @Override
    public Articulo updateArticulo(Integer id, Articulo articuloDetails) {
        Articulo articulo = getArticuloById(id);
        
        articulo.setNombreArticulo(articuloDetails.getNombreArticulo());
        articulo.setMarca(articuloDetails.getMarca());
        articulo.setPrecio(articuloDetails.getPrecio());
        articulo.setStock(articuloDetails.getStock());
        
        return articuloRepository.save(articulo);
    }

    @Override
    public void deleteArticulo(Integer id) {
        Articulo articulo = getArticuloById(id);
        articuloRepository.delete(articulo);
    }

    @Override
    public List<Articulo> buscarPorNombre(String nombreArticulo) {
        return articuloRepository.findByNombreArticuloContainingIgnoreCase(nombreArticulo);
    }
}
