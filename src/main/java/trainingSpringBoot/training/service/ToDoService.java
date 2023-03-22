package trainingSpringBoot.training.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trainingSpringBoot.training.entity.ToDo;
import trainingSpringBoot.training.repository.ToDoRepository;


import java.util.List;



@RequiredArgsConstructor
@Service        // leitet die Anfragen an das Repo weiter
public class ToDoService {

    private final ToDoRepository toDoRepository;

    /**
     * Fügt ein neues Element zur Datenbank hinzu
     * @param toDo
     *
     */
    public void createToDo(ToDo toDo) {
         toDoRepository.save(toDo);

    }

    /**
     * Überprüft alle vorhandenen todos.
     * wenn kein Element mit angegebener id gefunden wird, wird ein neues erstellt
     * @param toDo
     */
    public void updatedToDo(ToDo toDo) {
        ToDo updatedToDo = toDoRepository.findById(toDo.getId()).orElseThrow(
                () -> new EntityNotFoundException("ToDo wurde nicht gefunden"));
        updatedToDo.setTitle(toDo.getTitle());
        updatedToDo.setDescription(toDo.getDescription());
        this.toDoRepository.save(updatedToDo);
    }

    /**
     * Löscht ein Element mit vorhandener id
     * @param Id wird benötigt um zu löschen
     */
    public void deleteTodo(Long Id) {
        toDoRepository.deleteById(Id);
    }

    /**
     * gibt alle todos der Liste zurück die in der Datenbank gespeichert sind
     * @return lise mit allen todos
     */
    public List<ToDo> getAllTodos() {
        return (List<ToDo>) toDoRepository.findAll();
    }

    /**
     * gibt alle todos der Liste zurück die in der DB auf true stehen
     * @return todos die erledigt sind
     */
    public List<ToDo> getAllCompletedToDo() {
        return toDoRepository.findAllByStatusIsTrue();
    }
    /**
     * gibt alle todos der Liste zurück die in der DB auf false stehen
     * @return todos die offen sind
     */
    public List<ToDo> getAllOpenToDo() {
        return toDoRepository.findAllByStatusIsFalse();

    }

    /**
     * zahlt alle todos die erledigt sind
     * @return alle todos die auf true stehen
     */
    public Long countAllCompletedToDos() {
        return toDoRepository.countAllByStatusIsTrue();

    }

    /**
     * zahlt alle todos die noch offen sind
     * @return alle todos die auf false stehen
     */

    public Long countAllOpenToDos() {
        return toDoRepository.countAllByStatusIsFalse();
    }


}


