/*
package trainingSpringBoot.training.exceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trainingSpringBoot.training.exception.GlobalExceptionHandling;


import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@ContextConfiguration(classes = GlobalExceptionHandling.class)
public class GlobalExceptionHandlingTest {

    private static final String GERMAN_ERROR = "german error";
    private static final String ENGLISH_ERROR = "english error";
    private MockMvc mockMvc;

    @MockBean
    private MessageSource messageSource;

    @RestController
    @RequestMapping("/tests")
    public static class RestControllerThrowingException{
        @GetMapping(value = "/exception")
        public void throwException(){
            throw new EntityNotFoundException();
        }
    }

    @BeforeEach
    public void setup(){
        when(messageSource.getMessage(any(), any(), eq(Locale.ENGLISH))).thenReturn(ENGLISH_ERROR);
        when(messageSource.getMessage(any(), any(), eq(Locale.GERMAN))).thenReturn(GERMAN_ERROR);

        this.mockMvc = MockMvcBuilders.standaloneSetup(new RestControllerThrowingException())
                .setControllerAdvice(new GlobalExceptionHandling(messageSource))
                .build();
    }

    @Test
    public void shouldReturnEnglishException() throws Exception{
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/tests/exception"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(ENGLISH_ERROR));
    }

    @Test
    public void shouldReturnGermanException() throws Exception{
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/tests/exception")
                                .header(HttpHeaders.ACCEPT_LANGUAGE, Locale.GERMAN))
                .andExpect(status().isNotFound())
                .andExpect(content().string(GERMAN_ERROR));
    }

}

 */
