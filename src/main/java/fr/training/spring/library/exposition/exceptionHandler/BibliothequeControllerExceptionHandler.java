package fr.training.spring.library.exposition.exceptionHandler;

import fr.training.spring.library.domain.exception.BibliothequeNotFoundException;
import fr.training.spring.library.domain.exception.ErrorMessage;
import fr.training.spring.library.domain.exception.LivreNotFoundException;
import fr.training.spring.library.exposition.LibraryController;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@ResponseBody
public class BibliothequeControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @ExceptionHandler(BibliothequeNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(BibliothequeNotFoundException ex, WebRequest request) {
        logger.error(ex.getMessage());
        ErrorMessage message = new ErrorMessage( HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(true));
        return message;
    }

    @ExceptionHandler(LivreNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(LivreNotFoundException ex, WebRequest request) {
        logger.error(ex.getMsg());
        ErrorMessage message = new ErrorMessage( HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(true));
        return message;
    }

}
