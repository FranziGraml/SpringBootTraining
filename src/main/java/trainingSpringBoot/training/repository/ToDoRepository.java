package trainingSpringBoot.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import trainingSpringBoot.training.entity.ToDo;

@Repository

public interface ToDoRepository extends CrudRepository<ToDo,Long> {
}
