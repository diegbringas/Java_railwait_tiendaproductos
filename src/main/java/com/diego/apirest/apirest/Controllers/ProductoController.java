package com.diego.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.apirest.apirest.Repositories.ProductoRepositorio;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.diego.apirest.apirest.Entities.Productos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @GetMapping
    public List<Productos> getAllProductos(){

        return productoRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Productos obtereProductosPorId(@PathVariable Long id){
        return productoRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));
    }
    
    
    @PostMapping
    public Productos createProductos(@RequestBody Productos productos ){
        return productoRepositorio.save(productos);

    } 
    
    @PutMapping("/{id}")
    public Productos actualizarProductos(@PathVariable Long id, @RequestBody Productos detallesProducto){
        Productos productos = productoRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));


        productos.setNombre(detallesProducto.getNombre());
        productos.setPrecios(detallesProducto.getPrecios());

        return productoRepositorio.save(productos);

    }

    @DeleteMapping("/{id}")
    public String borrarProductos(@PathVariable Long id) {
        Productos productos = productoRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));

        productoRepositorio.delete(productos);
        return "El producto con id:" + id + "fue eliminado correctamente";

    }

    
    
    
}
