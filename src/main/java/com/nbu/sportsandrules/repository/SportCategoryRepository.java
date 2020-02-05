package com.nbu.sportsandrules.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nbu.sportsandrules.entity.SportCategory;

public interface SportCategoryRepository extends CrudRepository<SportCategory, Integer> {

	List<SportCategory> findByTypeName(String name);

	List<SportCategory> findByName(String name);

	Optional<SportCategory> findById(Integer id);

	List<SportCategory> findByTypeId(Integer id);

}
