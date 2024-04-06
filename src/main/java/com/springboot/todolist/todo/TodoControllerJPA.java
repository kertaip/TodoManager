package com.springboot.todolist.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
  class TodoControllerJPA {

    private TodoRepository todoRepository;

    public TodoControllerJPA(TodoRepository todoRepository) {
        this.todoRepository=todoRepository;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model){
        List<Todo> todos = todoRepository.findByUsername(getLoggedInUsername(model));
        model.put("todos", todos);

        List<Todo> pendingTodos = todoRepository.findByUsernameAndDone(getLoggedInUsername(model), false);
        model.put("pendingTodos", pendingTodos);
        List<Todo> doneTodos = todoRepository.findByUsernameAndDone(getLoggedInUsername(model), true);
        model.put("doneTodos", doneTodos);

        return "listTodos";
    }


    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model){
        Todo todo= new Todo(0, getLoggedInUsername(model), "", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "todo";
    }
    @RequestMapping(value="add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        String username = getLoggedInUsername(model);
        todo.setUsername(username);

        todoRepository.save(todo);

        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam long id){
        todoRepository.deleteById(id);

        return "redirect:list-todos";

    }
    @RequestMapping(value="update-todo", method=RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam long id, ModelMap model){
        Todo todo=todoRepository.findById(id).get();
        model.put("todo", todo);

        return "todo";
    }

    @RequestMapping(value="update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        todo.setUsername(getLoggedInUsername(model));
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

    @RequestMapping(value="switch-todo-done", method=RequestMethod.GET)
    public String switchTodoDone(@RequestParam long id){
        Todo todo=todoRepository.findById(id).get();
        if(todo.isDone()){
            todo.setDone(false);
        } else {
            todo.setDone(true);
        }
        todoRepository.save(todo);
        return "redirect:list-todos";
    }


    private String getLoggedInUsername(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
