package io.mohammad.apiquiz.module.cms.services;

import io.mohammad.apiquiz.module.cms.entities.Category;
import io.mohammad.apiquiz.module.cms.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Mohammad Nosairat on 3/24/2020.
 */
@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Optional<Category> findOneById(Long id){
        return categoryRepository.findById(id);
    }

}
