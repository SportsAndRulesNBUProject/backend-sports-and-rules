package com.nbu.sportsandrules.service;

import com.nbu.sportsandrules.entity.Example;
import com.nbu.sportsandrules.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    public void addExample(String example) {
        Example entity = new Example();
        entity.setExampleProperty(example);
        exampleRepository.save(entity);
    }

    public Iterable<Example> getAllExamples() {
        return exampleRepository.findAll();
    }
}
