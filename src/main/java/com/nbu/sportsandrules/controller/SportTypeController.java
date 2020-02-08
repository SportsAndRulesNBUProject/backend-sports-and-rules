package com.nbu.sportsandrules.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbu.sportsandrules.entity.SportCategory;
import com.nbu.sportsandrules.entity.SportType;
import com.nbu.sportsandrules.service.SportCategoryService;
import com.nbu.sportsandrules.service.SportTypeService;

@Controller
@RequestMapping(path = "api/sport-types")
public class SportTypeController {
    @Autowired
    private SportTypeService sportTypeService;

    @Autowired
    private SportCategoryService sportCategoryService;

    @GetMapping()
    public ResponseEntity<List<SportType>> getAllSportTypes() {
        return new ResponseEntity<>(sportTypeService.getAllSportTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SportType> getSportTypeById(@PathVariable("id") Integer id) {
        SportType sportType = sportTypeService.getSportTypeByid(id);
        if (sportType == null) {
            return new ResponseEntity<SportType>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SportType>(sportType, HttpStatus.OK);
    }

    @GetMapping("{id}/sport-categories")
    public ResponseEntity<List<SportCategory>> getAllSportsByCategoryId(@PathVariable("id") Integer id) {
        List<SportCategory> sportCategories = sportCategoryService.getSportCategoriesByTypeId(id);
        return new ResponseEntity<List<SportCategory>>(sportCategories, HttpStatus.OK);
    }

    /**
     * add
     */
    @PostMapping()
    public ResponseEntity<Void> addSportType(@RequestBody SportType sportType) {
        boolean sportTypeExists = sportTypeService.addSportType(sportType);
        if (sportTypeExists) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * Delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSportType(@PathVariable("id") Integer id) {
        sportTypeService.deleteSportType(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
