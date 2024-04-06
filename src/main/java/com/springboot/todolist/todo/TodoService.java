package com.springboot.todolist.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos;
    private static int todosCount=0;
    static{
        todos=new ArrayList<>(List.of(
                new Todo(todosCount++, "kertaip", "Learn AWS 1", LocalDate.now().plusYears(1), false),
                new Todo(todosCount++, "kertaip", "Learn DevOps 1", LocalDate.now().plusYears(2), false),
                new Todo(todosCount++, "kertaip", "Learn Full stack development 1", LocalDate.now().plusYears(3), false)
        ));
    }




    public List<Todo> findByUsername(String username){
        List<Todo> userTodos = todos.stream().filter(todo-> todo.getUsername().equalsIgnoreCase(username)).toList();
        return userTodos;
    }

    public List<Todo> findPendingByUsername(String username){
        List<Todo> pendingTodos=findByUsername(username).stream().filter(todo -> todo.isDone()==false).toList();
        return  pendingTodos;
    }

    public List<Todo> findDoneByUsername(String username){
        List<Todo> doneTodos=findByUsername(username).stream().filter(todo -> todo.isDone()).toList();
        return doneTodos;
    }


    public void addTodo(String username, String description, LocalDate targetDate, boolean isDone){
        Todo newTodo=new Todo(++todosCount, username, description, targetDate, isDone);
        todos.add(newTodo);
    }

    public void deleteById(long id){
        todos.removeIf(todo -> todo.getId()==id);
    }

    public Todo findById(long id) {
        Todo todoToUpdate = todos.stream().filter(todo-> todo.getId()==id).findFirst().get();
        return todoToUpdate;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }


}
