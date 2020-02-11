package com.nbu.sportsandrules.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nbu.sportsandrules.entity.Sport;

public interface SportRepository extends CrudRepository<Sport, Integer> {
    List<Sport> findByCategoryName(String name);

    List<Sport> findByCategoryNameIn(List<String> name);

    List<Sport> findByName(String name);

    List<Sport> findByCategoryId(Integer id);

}
