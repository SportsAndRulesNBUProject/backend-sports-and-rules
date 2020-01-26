package com.nbu.sportsandrules.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nbu.sportsandrules.entity.SportType;

public interface SportTypeRepository extends CrudRepository<SportType, Integer> {
	List<SportType> findByName(String name);

	Optional<SportType> findById(Integer id);

}
