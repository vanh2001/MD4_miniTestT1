package com.vanh.service.category;

import com.vanh.model.Book;
import com.vanh.model.Category;
import com.vanh.repo.ICategoryRepository;
import com.vanh.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void remove(Long id) {

    }
}
