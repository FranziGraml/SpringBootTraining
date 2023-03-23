package trainingSpringBoot.training.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trainingSpringBoot.training.dto.ToDoCreateDTO;
import trainingSpringBoot.training.dto.ToDoUpdateDTO;
import trainingSpringBoot.training.entity.ToDo;

import trainingSpringBoot.training.service.ToDoService;

import java.util.List;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor

public class ToDoController {

    private final ToDoService toDoService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ToDo createToDo(@Valid @RequestBody ToDoCreateDTO toDoCreateDTO){
        ToDo toDo = new ToDo();
        toDo.setDescription(toDoCreateDTO.getDescription());

        return this.toDoService.createToDo(modelMapper.map(toDoCreateDTO,ToDo.class));
    }


    @PutMapping
    public ToDo updatedToDo(@Valid @RequestBody ToDoUpdateDTO toDoUpdateDTO){
        ToDo toDo = new ToDo();
        toDo.setId(toDoUpdateDTO.getId());
        toDo.setTitle(toDoUpdateDTO.getTitle());
        toDo.setDescription(toDoUpdateDTO.getDescription());
        toDo.setStatus(toDoUpdateDTO.getStatus());
        modelMapper.map(toDoUpdateDTO, toDo);

        return this.toDoService.updatedToDo(toDo);
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
