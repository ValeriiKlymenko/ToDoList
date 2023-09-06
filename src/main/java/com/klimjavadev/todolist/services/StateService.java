package com.klimjavadev.todolist.services;

import com.klimjavadev.todolist.models.entity.State;

import java.util.List;

public interface StateService {
    State create(State state);
    State readById(long id);
    State update(State state);
    void delete(long id);
    List<State> getAll();

    State getByName(String name);
}
