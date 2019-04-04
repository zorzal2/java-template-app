package yucom.template.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ErrorControllerAdvice {

    private static Logger LOGGER = LoggerFactory.getLogger(ErrorControllerAdvice.class.getSimpleName());

    @ExceptionHandler
    public Exception error(Exception ex){
        LOGGER.error("Error");
        return ex;
    }
}
