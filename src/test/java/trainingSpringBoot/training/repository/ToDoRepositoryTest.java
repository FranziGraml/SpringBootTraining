package trainingSpringBoot.training.repository;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import trainingSpringBoot.training.entity.ToDo;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest

public class ToDoRepositoryTest {

@Autowired
     ToDoRepository toDoRepository;

    private ToDo toDoOne;
    private ToDo toDoTwo;
    private ToDo toDoThree;

    @BeforeEach
    public void setup() {


        toDoOne = new ToDo();
        toDoOne.setTitle("Eins");
        toDoOne.setStatus(false);
        toDoOne.setDescription("erste ToDo.");


        toDoTwo = new ToDo();
        toDoTwo.setTitle("zwei");
        toDoTwo.setStatus(false);
        toDoTwo.setDescription("zweite ToDo.");


        toDoThree = new ToDo();
        toDoThree.setTitle("drei");
        toDoThree.setStatus(true);
        toDoThree.setDescription("dritte ToDo.");


        toDoRepository.save(toDoOne);
        toDoRepository.save(toDoTwo);
        toDoRepository.save(toDoThree);
    }



    @Test
    void findAllByStatus() {
        assertTrue(toDoRepository.findAllByStatus(true).contains(toDoThree));
        assertFalse(toDoRepository.findAllByStatus(true).contains(toDoOne));
        assertFalse(toDoRepository.findAllByStatus(true).contains(toDoTwo));
    }

    @Test
    void countAllByStatus() {
        assertEquals( 1, toDoRepository.countAllByStatus(true));
        assertEquals( 2, toDoRepository.countAllByStatus(false));
    }



}
