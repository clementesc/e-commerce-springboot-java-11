package com.example.ecommerce.services;

import com.example.ecommerce.entities.Category;
import com.example.ecommerce.repositories.CategoryRepository;
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
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Integer id){
        Optional<Category> opt = repository.findById(id);
        //return opt.get();
        return opt.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Category insert (Category category){
        return repository.save(category);
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

    public Category update (Integer id, Category category){
        try {
            Category entity = repository.getById(id);
            updateData(entity, category);
            return repository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Category entity, Category category) {
        entity.setName(category.getName());
    }
}
