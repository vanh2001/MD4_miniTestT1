package com.vanh.service;

import com.vanh.exception.BookNotFound;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id) throws BookNotFound;

    void save(T t);

    void remove(Long id);
}
