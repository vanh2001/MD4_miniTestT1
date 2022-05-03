package com.vanh.model;

import org.springframework.web.multipart.MultipartFile;

public class BookForm {
    private Long id;
    private String name;
    private String author;
    private int price;
    private Category category;

    private MultipartFile image;

    public BookForm(Long id, String name, String author, int price, Category category, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public BookForm(String name, String author, int price, Category category, MultipartFile image) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public BookForm(String name, String author, int price, Category category) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
    }

    public BookForm(Long id, String name, String author, int price, Category category) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
    }

    public BookForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
