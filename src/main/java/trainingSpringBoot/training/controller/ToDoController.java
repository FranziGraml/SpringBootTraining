package trainingSpringBoot.training.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ToDo> createToDo(@Valid @RequestBody ToDoCreateDTO toDoCreateDTO){
        ToDo toDo = new ToDo();
        toDo.setDescription(toDoCreateDTO.getDescription());

        ToDo body = this.toDoService.createToDo(modelMapper.map(toDoCreateDTO,ToDo.class));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(201);
        ResponseEntity<ToDo> response = new ResponseEntity<>(body, headers,httpStatusCode);
        return response;
    }


    @PutMapping
    public ResponseEntity<ToDo> updatedToDo(@Valid @RequestBody ToDoUpdateDTO toDoUpdateDTO){
        ToDo toDo = new ToDo();
        toDo.setId(toDoUpdateDTO.getId());
        toDo.setTitle(toDoUpdateDTO.getTitle());
        toDo.setDescription(toDoUpdateDTO.getDescription());
        toDo.setStatus(toDoUpdateDTO.getStatus());
        modelMapper.map(toDoUpdateDTO, toDo);

        HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(200);
        ResponseEntity<ToDo> response = new ResponseEntity<>(httpStatusCode);

        return response;
       // return this.toDoService.updatedToDo(toDo);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        toDoService.deleteTodo(id);
        //return ResponseEntity.noContent(toDoService.deleteTodo(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getToDo(@PathVariable("id")Long id){
        return ResponseEntity.ok(toDoService.getToDo(id));
    }

    @GetMapping
    public ResponseEntity  <List<ToDo>>getAllToDos(){
        List<ToDo> allToDos = toDoService.getAllTodos();
        return ResponseEntity.ok(toDoService.getAllTodos());
    }

    @GetMapping(value = "/completed")
    public ResponseEntity <List<ToDo>>getAllCompletedToDo(){
        List<ToDo> allCompletedToDos = toDoService.getAllCompletedToDo();
        return ResponseEntity.ok(toDoService.getAllCompletedToDo());
        //return new ResponseEntity<>(toDoService.getAllCompletedToDo(),HttpStatus.OK);
    }

    @GetMapping(value = "/open")
    public ResponseEntity <List<ToDo>>getAllOpenToDo(){
        List<ToDo> allOpenToDos = toDoService.getAllOpenToDo();
        return  ResponseEntity.ok(toDoService.getAllOpenToDo());
        //return new ResponseEntity<>(toDoService.getAllOpenToDo(),HttpStatus.OK);
    }

    @GetMapping(value = "/completed/count")
    public ResponseEntity <Long> countAllCompletedToDos(){
        return ResponseEntity.ok(toDoService.countAllCompletedToDos());
        //return new ResponseEntity<> (toDoService.countAllCompletedToDos(),HttpStatus.OK);

    }

    @GetMapping(value = "/open/count")
    public ResponseEntity  <Long> countAllOpenToDos(){
        return ResponseEntity.ok(toDoService.countAllOpenToDos());
        //return new ResponseEntity<>(toDoService.countAllOpenToDos(),HttpStatus.OK) ;

    }




}
