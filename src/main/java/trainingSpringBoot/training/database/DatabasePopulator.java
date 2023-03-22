package trainingSpringBoot.training.database;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import trainingSpringBoot.training.entity.ToDo;
import trainingSpringBoot.training.repository.ToDoRepository;
import trainingSpringBoot.training.service.ToDoService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabasePopulator implements CommandLineRunner {

    //Field Base Dependency Injection: ermöglicht es ein Repository zu holen
    //@Autowired
    private final ToDoRepository toDoRepository;  //ist ein @Autowired Attribut

    private final ToDoService toDoService;

    @Override
    public void run(String... args) throws Exception {
        final ToDo toDo1 = new ToDo(null,"Haushalt","waschen",true);
        final ToDo toDo2 = new ToDo(null,"Privat","lernen",false);
        final ToDo toDo3 = new ToDo(null,"Arbeit","Mails",true);
        final ToDo toDo4 = new ToDo(null,"Arbeit","Mails",false);
        final ToDo toDo5 = new ToDo(null,"Arbeit","Mails",true);
        //toDoRepository.save(toDo4);

        toDoRepository.saveAll(List.of(toDo1,toDo2,toDo3,toDo4,toDo5));
        System.out.println("Anzahl ToDo's!" + toDoRepository.count());

        //toDoService.createToDo(toDo1);
        //toDoService.createToDo(toDo2);
        //toDoService.createToDo(toDo3);

        //System.out.println("ToDo's " + toDoService.getAllTodos());
        //System.out.println("Alle erledigten ToDo's " + toDoService.getAllCompletedToDo().indexOf(true));
        //System.out.println("Alle nicht erledigten ToDo's " + toDoService.getAllOpenToDo());





       /*  toDoRepository.delete(toDo3);

        toDoRepository.countAllByStatusIsFalse();
        System.out.println(toDoRepository.countAllByStatusIsFalse());

        toDoRepository.countAllByStatusIsTrue();
        System.out.println(toDoRepository.countAllByStatusIsTrue());

        toDoRepository.findAllByStatusIsTrue();
        System.out.println(toDoRepository.findAllByStatusIsTrue());

        toDoRepository.findAllByStatusIsFalse();
        System.out.println(toDoRepository.findAllByStatusIsFalse());  */







    }
}
