package com.example.Project.services;

public interface AbstractService<T> {


    T findById(Long id);

    T createOrUpdate(T obj);

    void delete(Long id);

}
