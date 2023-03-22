package trainingSpringBoot.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import trainingSpringBoot.training.entity.ToDo;

import java.util.List;

@Repository

public interface ToDoRepository extends CrudRepository<ToDo,Long> {

    //derived methods

    List<ToDo>findAllByStatusIsTrue();
    List<ToDo>findAllByStatusIsFalse();

    List<ToDo>findAllByStatus(Boolean status);

    Long countAllByStatusIsTrue();
    Long countAllByStatusIsFalse();
    Long countAllByStatus(Boolean status);

}
