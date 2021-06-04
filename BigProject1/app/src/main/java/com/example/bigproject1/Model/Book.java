package com.example.bigproject1.Model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String title,author,type,date;
    private double price;

    public Book(int id, String title, String author, String type, String date, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.date = date;
        this.price = price;
    }

    public Book(String title, String author, String type, String date, double price) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.date = date;
        this.price = price;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
