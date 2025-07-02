package com.example.demo.controller;

import com.example.demo.model.Articulo;
import com.example.demo.model.ArticuloDTO;
import com.example.demo.service.ArticuloService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ArticuloDTO> getAllArticulos() {
        return articuloService.getAllArticulos().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloDTO> getArticuloById(@PathVariable Integer id) {
        Articulo articulo = articuloService.getArticuloById(id);
        return ResponseEntity.ok(convertToDto(articulo));
    }

    @PostMapping
    public ResponseEntity<ArticuloDTO> createArticulo(@RequestBody ArticuloDTO articuloDTO) {
        Articulo articulo = convertToEntity(articuloDTO);
        Articulo created = articuloService.createArticulo(articulo);
        return ResponseEntity.ok(convertToDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticuloDTO> updateArticulo(@PathVariable Integer id, @RequestBody ArticuloDTO articuloDTO) {
        Articulo articulo = convertToEntity(articuloDTO);
        Articulo updated = articuloService.updateArticulo(id, articulo);
        return ResponseEntity.ok(convertToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticulo(@PathVariable Integer id) {
        articuloService.deleteArticulo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ArticuloDTO>> buscarArticulos(@RequestParam String nombreArticulo) {
        List<Articulo> articulos = articuloService.getAllArticulos().stream()
                .filter(a -> a.getNombreArticulo().toLowerCase().contains(nombreArticulo.toLowerCase()))
                .collect(Collectors.toList());
        List<ArticuloDTO> articulosDTO = articulos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(articulosDTO);
    }

    private ArticuloDTO convertToDto(Articulo articulo) {
        return modelMapper.map(articulo, ArticuloDTO.class);
    }

    private Articulo convertToEntity(ArticuloDTO dto) {
        return modelMapper.map(dto, Articulo.class);
    }
}
