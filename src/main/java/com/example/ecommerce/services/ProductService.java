package com.example.ecommerce.services;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.services.exceptions.DatabaseException;
import com.example.ecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Integer id){
        Optional<Product> opt = repository.findById(id);
        //return opt.get();
        return opt.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Product insert (Product product){
        return repository.save(product);
    }

    public void delete (Integer id){
        try{
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Product update (Integer id, Product product){
        try {
            Product entity = repository.getById(id);
            updateData(entity, product);
            return repository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Product entity, Product product) {
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setImgUrl(product.getImgUrl());
    }
}
