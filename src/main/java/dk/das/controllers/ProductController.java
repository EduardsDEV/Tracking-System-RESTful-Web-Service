package dk.das.controllers;

import dk.das.models.Component;
import dk.das.models.Product;
import dk.das.repositories.ComponentRepository;
import dk.das.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Chris on 15-Nov-17.
 */
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    private final ComponentRepository componentRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, ComponentRepository componentRepository) {
        this.productRepository = productRepository;
        this.componentRepository = componentRepository;
    }

    @PostMapping("/product")
    public Product save(@RequestParam String uniqueCode,
                        @RequestParam(name = "componentsUniqueCodes[]") String[] componentsUniqueCodes) {
        Product p = new Product();
        p.setUniqueCode(uniqueCode);
        Collection<Component> c = componentRepository.findAllByUniqueCodeIn(Arrays.asList(componentsUniqueCodes));
        if (c != null && c.size() >= 0) {
            p.setComponents(c);
            productRepository.save(p);
        } else {
            return null;
        }
        return p;
    }
}
