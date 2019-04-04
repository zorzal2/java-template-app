package yucom.template.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yucom.template.services.spotify.ErrorService;

@RestController
@RequestMapping(value = "/some")
public class SomeController {

    private static Logger LOGGER = LoggerFactory.getLogger(SomeController.class.getSimpleName());

    private ErrorService errorService;

    @Autowired
    public SomeController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @GetMapping
    public String some(){
        LOGGER.error("SOme");
        return "HOLA";
    }

    @RequestMapping("/error")
    public String error(){
        if(true){
            throw new NullPointerException();
        }
        return "HOLA";
    }

    @RequestMapping("/spotify")
    public Object album(){
        return errorService.getAlbum();
    }
}
