package dk.das.controllers;

import dk.das.models.Component;
import dk.das.repositories.ComponentRepository;
import dk.das.utils.IllegalQrCodeFormatException;
import dk.das.utils.QrCodeUtils;
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
        if (uniqueCodes.length > 0) {
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

    @PostMapping("/components-range")
    public String saveRange(@RequestParam String startId, @RequestParam String endId) {
        // The QR code will have the following format XXYYYY, where XX are two uppercase letters and YYYY numbers in the range 0001-9999
        // The client said that the boundaries will never be something like this AA9000 - AB0100
        if (!QrCodeUtils.isValidQrCode(startId) || !QrCodeUtils.isValidQrCode(endId)) {
            throw new IllegalQrCodeFormatException();
        }

        final String startLetters = QrCodeUtils.getLettersFromQrCode(startId);
        final String endLetters = QrCodeUtils.getLettersFromQrCode(endId);

        // if letters are different, that means that the codes don't follow the business rules
        if (!startLetters.equals(endLetters)) {
            throw new IllegalArgumentException(String.format("Letters in start id \'%s\' are not the same as letters in end id \'%s\'", startId, endId));
        }

        int startValue = QrCodeUtils.getNumberFromQrCode(startId);
        int endValue = QrCodeUtils.getNumberFromQrCode(endId);

        if (startValue <= 0 || endValue <= 0) {
            throw new IllegalArgumentException("Boundaries must be greater than 0");
        }

        if (startValue > endValue) {
            final int temp = endValue;
            endValue = startValue;
            startValue = temp;
        }

        Collection<Component> components = new ArrayList<>(endValue - startValue + 1);
        for (int i = startValue; i <= endValue; i++) {
            Component component = new Component();
            component.setUniqueCode(startLetters + QrCodeUtils.convertIntToStringCode(i));
            components.add(component);
        }
        componentRepository.save(components);
        return "Saved";
    }
}
