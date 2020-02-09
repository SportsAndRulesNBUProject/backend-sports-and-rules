package com.nbu.sportsandrules.controller;

import com.nbu.sportsandrules.entity.Example;
import com.nbu.sportsandrules.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(path = "/example")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @GetMapping
    public ResponseEntity<Iterable<Example>> getAllExamples() {

        return new ResponseEntity<>(exampleService.getAllExamples(), HttpStatus.OK);
    }

    @PostMapping("/{example}")
    public ResponseEntity addExample(@PathVariable("example") String example) {
        exampleService.addExample(example);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
