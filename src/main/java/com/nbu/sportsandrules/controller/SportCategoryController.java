package com.nbu.sportsandrules.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbu.sportsandrules.controller.body.SportBody;
import com.nbu.sportsandrules.controller.body.SportCategoryBody;
import com.nbu.sportsandrules.entity.Sport;
import com.nbu.sportsandrules.entity.SportCategory;
import com.nbu.sportsandrules.entity.SportType;
import com.nbu.sportsandrules.service.SportCategoryService;
import com.nbu.sportsandrules.service.SportService;
import com.nbu.sportsandrules.service.SportTypeService;

@Controller
@RequestMapping(path = "api/sport-categories")
public class SportCategoryController {
	@Autowired
	private SportCategoryService sportCategoryService;

	@Autowired
	private SportTypeService sportTypeService;

	@Autowired
	private SportService sportService;

	@GetMapping()
	public ResponseEntity<Iterable<SportCategory>> getAllSportSportCategories() {
		return new ResponseEntity<>(sportCategoryService.getAllSportSportCategories(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SportCategory> getSportCategoryById(@PathVariable("id") Integer id) {
		SportCategory sportCategory = sportCategoryService.getSportCategoryByid(id);
		if (sportCategory == null) {
			return new ResponseEntity<SportCategory>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SportCategory>(sportCategory, HttpStatus.OK);
	}

	@GetMapping("/{id}/sports")
	public ResponseEntity<List<SportBody>> getAllSportsByCategoryId(@PathVariable("id") Integer id) {
		List<Sport> allSports = sportService.getAllSportsByCategoryId(id);

		List<SportBody> allSportBodies = new ArrayList<>();
		for (Sport sport : allSports) {
			allSportBodies.add(sport.initSportBody());
		}
		return new ResponseEntity<>(allSportBodies, HttpStatus.OK);
	}

	/**
	 * add
	 */
	@PostMapping()
	public ResponseEntity<Void> addSportCategory(@RequestBody SportCategoryBody sportCategoryBody) {
		SportCategory newCategory;
		try {
			newCategory = sportCategoryBody.initSportCategory();
			SportType newSportType = sportTypeService.getSportTypeByid(sportCategoryBody.getTypeId());
			newCategory.setType(newSportType);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		boolean sportCategoryExists = sportCategoryService.addSportCategory(newCategory);
		if (sportCategoryExists) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	/**
	 * Update
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateSportCategoryById(@PathVariable("id") Integer id,
			@RequestBody SportCategoryBody sportCategoryBody) {
		SportCategory category = sportCategoryService.getSportCategoryByid(id);

		Integer typeId = sportCategoryBody.getTypeId();
		if (typeId != null) {
			SportType sportType = sportTypeService.getSportTypeByid(typeId);
			category.setType(sportType);
		}

		String description = sportCategoryBody.getDescription();
		if (description != null) {
			category.setDescription(description);
		}

		String name = sportCategoryBody.getName();
		if (name != null) {
			category.setName(name);
		}

		sportCategoryService.updateSportCategory(category);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	/**
	 * Delete
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSportCategory(@PathVariable("id") Integer id) {
		sportCategoryService.deleteSportCategory(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
