package yucom.template.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
public class HealthCheckController {

    private static Logger LOGGER = LoggerFactory.getLogger(HealthCheckController.class.getSimpleName());

    @RequestMapping("/health-check")
    public String status(Exception ex){
        return "OK";
    }
}
