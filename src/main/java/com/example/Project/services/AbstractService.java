package com.example.Project.services;

public interface AbstractService<T> {

    T findById(Long id);
}
