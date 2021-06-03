package com.example.gocarrentspringbootapplication.data.api;

public interface OperationsRepository {
    String[] OPERATIONS = {">=", "<=", "<", ">", "!=", "="};
    String GREATER_THAN_EQUALS = OPERATIONS[0];
    String LESS_THAN_EQUALS = OPERATIONS[1];
    String LESS_THAN = OPERATIONS[2];
    String GREATER_THAN = OPERATIONS[3];
    String NOT_EQUAL = OPERATIONS[4];
    String EQUALS = OPERATIONS[5];
}
