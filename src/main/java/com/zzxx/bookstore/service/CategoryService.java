package com.zzxx.bookstore.service;

import com.zzxx.bookstore.domain.Category;
import com.zzxx.bookstore.domain.Type;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    List<Type> findTname();

}
