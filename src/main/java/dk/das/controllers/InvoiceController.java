package dk.das.controllers;

import dk.das.models.Customer;
import dk.das.models.Invoice;
import dk.das.models.Product;
import dk.das.repositories.CustomerRepository;
import dk.das.repositories.InvoiceRepository;
import dk.das.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Chris on 15-Nov-17.
 */
@RestController
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public InvoiceController(InvoiceRepository invoiceRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.invoiceRepository = invoiceRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/invoice")
    public Invoice saveInvoice(
            @RequestParam
                    String uniqueCode,
            @RequestParam
                    String firstName,
            @RequestParam
                    String lastName,
            @RequestParam
                    String email,
            @RequestParam
                    String address,
            @RequestParam
                    String phoneNumber,
            @RequestParam(value = "productsUniqueCodes[]") String[] productsUniqueCodes
    ) {
        Invoice invoice = new Invoice();
        invoice.setUniqueCode(uniqueCode);

        Customer customer = new Customer(); // TODO: 15-Nov-17 Reuse the same customer, that means, search in DB first
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);
        customerRepository.save(customer);
        invoice.setCustomer(customer);

        Collection<Product> productsByUniqueCode = productRepository.findAllByUniqueCodeIn(Arrays.asList(productsUniqueCodes));
        if (productsByUniqueCode.size() == productsUniqueCodes.length) {
            invoice.setProducts(productsByUniqueCode);
        } else {
            throw new RuntimeException("Products scanned were not found in database");
        }


        invoiceRepository.save(invoice);

        return invoice;
    }
}
