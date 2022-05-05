package com.vanh.model;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private String author;
    private String image;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category category;



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Book(String name, int price, String author, Category category, String image) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.category = category;
        this.image = image;
    }

    public Book() {
    }

    public Book(Long id, String name, int price, String author, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.category = category;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
