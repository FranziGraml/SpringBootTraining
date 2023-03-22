package trainingSpringBoot.training.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import trainingSpringBoot.training.entity.ToDo;
import trainingSpringBoot.training.service.ToDoService;

import java.util.List;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor

public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping
    public ToDo createToDo(@RequestBody ToDo toDo){
        return toDoService.createToDo(toDo);
    }

    @PutMapping(value = "/{id}")
    public void updatedToDo(@PathVariable Long id, @RequestBody ToDo updatedToDo){
        toDoService.updatedToDo(updatedToDo);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id")Long id){
        toDoService.deleteTodo(id);
    }

    @GetMapping("/{id}")
    public ToDo getToDo(@PathVariable("id")Long id){
        return toDoService.getToDo(id);
    }

    @GetMapping
    public List<ToDo>getAllToDos(){
        List<ToDo> allToDos = toDoService.getAllTodos();
        return allToDos;
    }

    @GetMapping(value = "/completed")
    public List<ToDo>getAllCompletedToDo(){
        List<ToDo> allCompletedToDos = toDoService.getAllCompletedToDo();
        return allCompletedToDos;
    }

    @GetMapping(value = "/open")
    public List<ToDo>getAllOpenToDo(){
        List<ToDo> allOpenToDos = toDoService.getAllOpenToDo();
        return allOpenToDos;
    }

    @GetMapping(value = "/completed/count")
    public Long countAllCompletedToDos(){
        return toDoService.countAllCompletedToDos();

    }

    @GetMapping(value = "/open/count")
    public Long countAllOpenToDos(){
        return toDoService.countAllOpenToDos();

    }




}
