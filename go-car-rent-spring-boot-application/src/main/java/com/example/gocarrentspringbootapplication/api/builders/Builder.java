package com.example.gocarrentspringbootapplication.api.builders;

public interface Builder<T> {
    void refresh();
    T build();
}
