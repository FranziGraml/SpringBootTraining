package trainingSpringBoot.training.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import trainingSpringBoot.training.entity.ToDo;
import trainingSpringBoot.training.repository.ToDoRepository;
import trainingSpringBoot.training.service.ToDoService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@ExtendWith(MockitoExtension.class)
public class ToDoServiceImplTest {
    private ToDo toDoOne;
    private ToDo toDoTwo;
    private ToDo toDoThree;


    @Mock
    private ToDoRepository toDoRepository;
    @InjectMocks
     private ToDoService toDoService;

        @BeforeEach
        public void setUp() {


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
    void getToDo(){
            when(toDoRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(toDoOne));
            assertEquals(toDoOne,toDoService.getToDo(any(Long.class)));

    }
    @Test
    void getToDoExeption() {
        when(toDoRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class ,()->toDoService.getToDo(1L));
    }








}


