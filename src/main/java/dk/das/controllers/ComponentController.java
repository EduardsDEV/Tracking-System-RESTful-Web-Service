package dk.das.controllers;

import dk.das.models.Component;
import dk.das.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Chris on 15-Nov-17.
 */
@RestController
public class ComponentController {

    private final ComponentRepository componentRepository;

    @Autowired
    public ComponentController(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @PostMapping("/component")
    public Component save(@RequestParam String uniqueCode) {
        Component c = new Component();
        c.setUniqueCode(uniqueCode);
        componentRepository.save(c);
        return c;
    }
}
