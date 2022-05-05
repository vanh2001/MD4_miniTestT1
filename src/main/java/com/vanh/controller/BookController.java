package com.vanh.controller;

import com.vanh.exception.BookNotFound;
import com.vanh.model.Book;
import com.vanh.model.BookForm;
import com.vanh.model.Category;
import com.vanh.service.book.IBookService;
import com.vanh.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@PropertySource("classpath:upload_file.properties")
public class BookController {
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IBookService bookService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

//    List Books
    @GetMapping("/books")
    public ModelAndView listBook() {
        Iterable<Book> books = bookService.findAll();
        ModelAndView modelAndView = new ModelAndView("book/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

//    Create Book
    @GetMapping("/createBook")
    public ModelAndView showCreateFormBook() {
        ModelAndView modelAndView = new ModelAndView("book/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/createBook")
    public ModelAndView createNewBook(@ModelAttribute("book") BookForm bookForm) {
        //lấy file ảnh:
        MultipartFile file = bookForm.getImage();
        //lấy tên file:
        String fileName = file.getOriginalFilename();
        //lấy thông tin của book:
        String name = bookForm.getName();
        String author = bookForm.getAuthor();
        int price = bookForm.getPrice();
        Category category = bookForm.getCategory();

        Book book = new Book(name, price, author, category, fileName);

        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("book/create");
        modelAndView.addObject("book", book);
        modelAndView.addObject("message", "New book created!");
        return modelAndView;
    }

//    Edit Book
    @GetMapping("/editBook/{id}")
    public ModelAndView showEditFormBook(@PathVariable Long id) throws BookNotFound {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("book/edit");
            modelAndView.addObject("book", book);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }
    }

    @PostMapping("/editBook")
    public ModelAndView editBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("book/edit");
        modelAndView.addObject("book", book);
        modelAndView.addObject("message", "Book updated!");
        return modelAndView;
    }

//    Delete Book
    @GetMapping("/deleteBook/{id}")
    public ModelAndView showFormDeleteBook(@PathVariable Long id) throws BookNotFound {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("book/delete");
            modelAndView.addObject("book", book.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@ModelAttribute("book") Book book) {
        bookService.remove(book.getId());
        return "redirect:books";
    }
}
