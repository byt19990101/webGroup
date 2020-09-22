package com.zzxx.bookstore.domain;

import java.io.Serializable;

public class Book implements Serializable {
    private int bid; // 书籍id，必输
    private String bookname; // 书籍名称，必输
    private double price; // 价格，必输
    private String author; // 作者
    private String publisher; // 出版社
    private int sales; // 销量
    private int cid; // 所属分类，必输
    private String image; // 封面

    private Category category; // 所属分类

    public Book() {
    }

    public Book(int bid, String bookname, double price, String author, String publisher, int sales, int cid, String image, Category category) {
        this.bid = bid;
        this.bookname = bookname;
        this.price = price;
        this.author = author;
        this.publisher = publisher;
        this.sales = sales;
        this.cid = cid;
        this.image = image;
        this.category = category;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
