package com.zzxx.bookstore.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlBuilder {

    private final StringBuilder queryBuilder = new StringBuilder();

    private final List<Object> argList = new ArrayList<>();

    public SqlBuilder() {
    }

    public SqlBuilder(String query) {
        queryBuilder.append(query);
    }

    public SqlBuilder(String query, Object... args) {
        queryBuilder.append(query);
        argList.addAll(Arrays.asList(args));
    }

    public SqlBuilder append(String expression) {
        queryBuilder.append(expression);
        return this;
    }

    public SqlBuilder append(char c) {
        queryBuilder.append(c);
        return this;
    }

    public SqlBuilder addArgs(Object... args) {
        argList.add(Arrays.asList(args));
        return this;
    }

    public SqlBuilder addArg(Object arg) {
        argList.add(arg);
        return this;
    }

    public SqlBuilder addCondition(String expression, Object arg) {
        if (arg != null) {
            queryBuilder.append(expression);
            argList.add(arg);
        }
        return this;
    }

    public String query() {
        return queryBuilder.toString();
    }

    public Object[] args() {
        return argList.toArray();
    }

}
