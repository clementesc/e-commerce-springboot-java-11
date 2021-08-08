package com.example.ecommerce.resources;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Integer id){
        Product product = service.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody @Valid Product product){
        product = service.insert(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(product.getId()).toUri();
        // Response code by created method is HTTP_CODE=201
        return ResponseEntity.created(uri).body(product);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        // Response code without content is HTTP_CODE=204
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update (@PathVariable("id") Integer id, @RequestBody @Valid Product product){
        product = service.update(id, product);
        return ResponseEntity.ok().body(product);
    }
}
