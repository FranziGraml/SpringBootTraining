package trainingSpringBoot.training.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import trainingSpringBoot.training.Config.TestPasswordEncoderConfig;
import trainingSpringBoot.training.config.PasswordEncoderConifg;
import trainingSpringBoot.training.entity.ToDo;
import trainingSpringBoot.training.security.SecurityConfig;
import trainingSpringBoot.training.service.ToDoService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@Import(TestPasswordEncoderConfig.class)


//@ContextConfiguration(classes = {SecurityConfig.class})
@WithMockUser
@ExtendWith(MockitoExtension.class)
@WebMvcTest(ToDoController.class)
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ToDoService toDoService;
    @MockBean
    private ModelMapper modelMapper;

    private ToDo toDoOne;
    private ToDo toDoTwo;
    private ToDo toDoThree;
    private List<ToDo> toDoList = new ArrayList<>();

    @BeforeEach
    public void init() {
        toDoOne = new ToDo(1L, "Haushalt", "waschen", true);
        toDoTwo = new ToDo(2L, "Privat", "lernen", false);
        toDoThree = new ToDo(3L, "Arbeit", "Mails", true);
        this.toDoList.addAll(Arrays.asList(toDoOne, toDoTwo, toDoThree));
    }

    @Test
    @WithMockUser
    public void getAllToDos() throws Exception {
        when(this.toDoService.getAllTodos()).thenReturn(this.toDoList);

        mockMvc.perform(get("/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                                """
                                        [
                                            {
                                                "id": 1,
                                                "title": "Haushalt",
                                                "description": "waschen",
                                                "status": true
                                            },
                                            {
                                                "id": 2,
                                                "title": "Privat",
                                                "description": "lernen",
                                                "status": false
                                            },
                                            {
                                                "id": 3,
                                                "title": "Arbeit",
                                                "description": "Mails",
                                                "status": true
                                            }
                                            
                                        ]
                                                            
                                        """
                        )
                );
    }


    @Test
    @WithMockUser
    public void createdToDo() throws Exception {
        ToDo toDoSix = new ToDo(6L, "Sport", "laufen", true);
        when(this.toDoService.createToDo(any())).thenReturn(toDoSix);

        this.mockMvc.perform(
                post("/todo")
                        .content(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(
                                """
                                                       {
                                            "id": null,
                                            "title": "Sport",
                                            "description": "laufen",
                                            "status": true
                                        }
                                                       """
                        )
        );

    }

    @Test
    @WithMockUser
    public void getToDo() throws Exception {
        when(this.toDoService.getToDo(1L)).thenReturn((ToDo) this.toDoOne);

        mockMvc.perform(get("/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                                """
                                                                                
                                            {
                                                "id": 1,
                                                "title": "Haushalt",
                                                "description": "waschen",
                                                "status": true
                                            },
                                            {
                                                "id": 2,
                                                "title": "Privat",
                                                "description": "lernen",
                                                "status": false
                                            },
                                            {
                                                "id": 3,
                                                "title": "Arbeit",
                                                "description": "Mails",
                                                "status": true
                                            }
                                            
                                                                                
                                                                                
                                        """
                        )
                );
    }

    @Test
    @WithMockUser
    public void deleteToDo() throws Exception {
        this.mockMvc.perform(delete("/todo/3")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }


    @Test
    @WithMockUser
    public void updatedToDo() throws Exception {
        ToDo updatedToDo = new ToDo(2L, "Update", "lernen", false);
        when(this.toDoService.updatedToDo(any())).thenReturn(updatedToDo);

        mockMvc.perform(MockMvcRequestBuilders.put("/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {
                                                "id": 2,
                                                "title": "Arbeit",
                                                "description": "lernen",
                                                "status": false
                                            }
                                """));
    }


    /*@Test
    public void updatedToDo() throws Exception {
        ToDo toDoOld = new ToDo(2L, "Privat", "lernen", false);
        ToDo toDoNew = new ToDo(2L, "Privat", "lernen", true);
        when(this.toDoService.getToDo(any(Long.class))).thenReturn(toDoOld);
        when(this.toDoService.updatedToDo(any(ToDo.class))).thenReturn(toDoNew);
        when(this.modelMapper.map(any(), any())).thenReturn(toDoNew);


        mockMvc.perform(MockMvcRequestBuilders.put("/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {
                                                "id": 2,
                                                "title": "Arbeit",
                                                "description": "lernen",
                                                "status": true
                                            }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                {
                                                                                "id": 2,
                                                                                "title": "Arbeit",
                                                                                "description": "lernen",
                                                                                "status": true
                                                                            }
                                                                """));
    }*/


}