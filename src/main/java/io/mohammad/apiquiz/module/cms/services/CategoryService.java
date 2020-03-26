package io.mohammad.apiquiz.module.cms.services;

import io.mohammad.apiquiz.module.cms.entities.Category;
import io.mohammad.apiquiz.module.cms.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Mohammad Nosairat on 3/24/2020.
 */

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> findOneById(Long id){
        return categoryRepository.findById(id);
    }

}
