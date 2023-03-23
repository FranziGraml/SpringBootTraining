package trainingSpringBoot.training.exception;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.Logger;


@RestControllerAdvice
@Log
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

    //Logger logger = (Logger) LoggerFactory.getLogger(GlobalExceptionHandling.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(RuntimeException rex) {
        logger.warn("INTERNAL SERVER ERROR");
        return new ResponseEntity<>(ErrorResponse.create(rex, HttpStatus.INTERNAL_SERVER_ERROR, rex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> entityNotFoundException(RuntimeException exception, WebRequest request) {
        logger.warn("WARNING! ENTITY NOT FOUND");
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }





}
