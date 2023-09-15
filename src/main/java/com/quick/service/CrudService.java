package com.quick.service;

import java.util.Set;

public interface CrudService<T,I> {
    T add(T t);
    Set<T> findAll();
    T findById(I id);
    void deleteById(I id);

}