package de.neuefische.springexceptionhandlingtask;

import de.neuefische.springexceptionhandlingtask.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @GetMapping("{brand}")
    String getCarBrand(@PathVariable String brand) {
        if (!brand.equals("porsche")) {
            throw new IllegalArgumentException("Only 'porsche' allowed");
        }
        return brand;
    }

    @GetMapping
    String getAllCars() {
        throw new NoSuchElementException("No Cars found");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(IllegalArgumentException exception) {
        return new ErrorMessage(exception.getMessage());
    }
}
