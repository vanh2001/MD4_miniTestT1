package com.vanh.service.book;

import com.vanh.exception.BookNotFound;
import com.vanh.model.Book;
import com.vanh.repo.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) throws BookNotFound {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            return book;
        }
        throw new BookNotFound();
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }
}
