package trainingSpringBoot.training.exception;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RequiredArgsConstructor
@RestControllerAdvice
@Log
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

private final MessageSource nSource;

   /* @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(RuntimeException rex) {
        logger.warn("INTERNAL SERVER ERROR");
        return new ResponseEntity<>(ErrorResponse.create(rex, HttpStatus.INTERNAL_SERVER_ERROR, rex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/


    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> entityNotFoundException(RuntimeException exception, WebRequest request) {
        logger.warn("WARNING! ENTITY NOT FOUND");
        String message = nSource.getMessage("exception.idNotFound",null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(exception, message, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }





}
