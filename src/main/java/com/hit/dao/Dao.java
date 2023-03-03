package com.hit.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Dao<T> {
    T getOne(String id) throws IOException, ClassNotFoundException;
    List<T> getAll();
    void removeOne(String id);
    void createOne(T object);
    void updateOne(String id, T object);
    void removeAll();
}
