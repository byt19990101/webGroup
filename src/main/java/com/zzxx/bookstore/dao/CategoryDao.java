package com.zzxx.bookstore.dao;

import com.zzxx.bookstore.domain.Type;

import java.util.List;

public interface CategoryDao extends BaseDao {
    List<Type> findTname();
}
