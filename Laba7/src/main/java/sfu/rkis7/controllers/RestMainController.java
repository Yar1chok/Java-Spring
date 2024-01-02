package sfu.rkis7.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sfu.rkis7.model.Watch;
import sfu.rkis7.repositories.WatchRepository;

import java.util.List;
import java.util.Optional;

/**
 * REST controller
 */
@RestController
@RequestMapping("/rest")
public class RestMainController {

    private final WatchRepository watchRepository;

    @Autowired
    public RestMainController(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    @GetMapping("/watch")
    public ResponseEntity<List<Watch>> findElements(@RequestParam(name = "price", required = false)
                                                      Double price) {
        if (watchRepository.findAll().isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if (price != null) {
            return new ResponseEntity<>(watchRepository.findWatchByPriceGreaterThan(price), HttpStatus.OK);
        }
        return new ResponseEntity<>(watchRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/watch/{id}")
    public ResponseEntity<Watch> showElement(@PathVariable("id") Integer id) {
        if (watchRepository.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(watchRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/watch")
    public ResponseEntity<String> addElementPost(@Valid @RequestBody Watch watch) {
        watchRepository.save(watch);
        return ResponseEntity.ok("The watch has been added successfully");
    }

    @PutMapping("/watch/{id}")
    public ResponseEntity<String> editElementPut(@PathVariable Integer id, @Valid @RequestBody Watch watch) {
        Optional<Watch> existingWatch = watchRepository.findById(id);
        if (existingWatch.isPresent()) {
            watch.setId(id);
            watchRepository.save(watch);
            return ResponseEntity.ok("The watch has been successfully updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The watch was not found");
        }
    }

    @DeleteMapping("/watch/{id}")
    public ResponseEntity<String> deleteElementDelete(@PathVariable Integer id) {
        Optional<Watch> watch = watchRepository.findById(id);
        if (watch.isPresent()) {
            watchRepository.delete(watch.get());
            return ResponseEntity.ok("The watch has been successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The watch was not found");
        }
    }

}
