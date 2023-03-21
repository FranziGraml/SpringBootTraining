package trainingSpringBoot.training.database;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import trainingSpringBoot.training.entity.ToDo;
import trainingSpringBoot.training.repository.ToDoRepository;

@Component
@RequiredArgsConstructor
public class DatabasePopulator implements CommandLineRunner {

    //Field Base Dependency Injection: ermöglicht es ein Repository zu holen
    //@Autowired
    private final ToDoRepository toDoRepository;  //ist ein @Autowired Attribut

    @Override
    public void run(String... args) throws Exception {
        final ToDo toDo1 = new ToDo(null,"Haushalt","waschen");
        final ToDo toDo2 = new ToDo(null,"Privat","lernen");
        final ToDo toDo3 = new ToDo(null,"Arbeit","Mails");
        toDoRepository.save(toDo1);
        System.out.println("Anzahl ToDo's!" + toDoRepository.count());

    }
}
