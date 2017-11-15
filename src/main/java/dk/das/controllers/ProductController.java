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
                        @RequestParam String componentUniqueCode) {
        Product p = new Product();
        p.setUniqueCode(uniqueCode);
        Component c = componentRepository.findByUniqueCode(componentUniqueCode);
        if (c != null) {
            p.setComponents(Arrays.asList(c));

            productRepository.save(p);
        } else {
            return null;
        }
        return p;
    }
}
