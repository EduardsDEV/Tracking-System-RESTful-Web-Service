package dk.das.controllers;

import dk.das.models.Component;
import dk.das.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

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

    // TODO: 17-Nov-17 Change return type and value, maybe to some meaningful status codes, like http status 
    @PostMapping("/component")
    public String save(@RequestParam(name = "uniqueCodes") String[] uniqueCodes) {
        if(uniqueCodes.length > 0) {
            Collection<Component> components = new ArrayList<>(uniqueCodes.length);
            for (String uniqueCode : uniqueCodes) {
                Component c = new Component();
                c.setUniqueCode(uniqueCode);
                components.add(c);
            }
            componentRepository.save(components);
            return "Saved";
        }
        throw new IllegalArgumentException("No codes for components");
    }
}
