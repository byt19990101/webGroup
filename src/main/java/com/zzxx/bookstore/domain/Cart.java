package com.zzxx.bookstore.domain;

import java.io.Serializable;

public class Cart implements Serializable {
    private User user;
    private String date;
    private Book book;
    private int num;

    private String is_pay;

    public Cart() {
    }

    public Cart(User user, String date, Book book, int num, String is_pay) {
        this.user = user;
        this.date = date;
        this.book = book;
        this.num = num;
        this.is_pay = is_pay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(String is_pay) {
        this.is_pay = is_pay;
    }
}
