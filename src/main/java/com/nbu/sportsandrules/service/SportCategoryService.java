package com.nbu.sportsandrules.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.nbu.sportsandrules.controller.body.SportCategoryBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbu.sportsandrules.entity.SportCategory;
import com.nbu.sportsandrules.repository.SportCategoryRepository;

@Service
public class SportCategoryService {
    @Autowired
    private SportCategoryRepository sportCategoryRepository;

    /**
     * Duplicated names will not be allowed
     */
    public boolean addSportCategory(SportCategory sportCategory) {
        List<SportCategory> sportByName = sportCategoryRepository.findByName(sportCategory.getName());
        if (sportByName.size() > 0) {
            return true;
        }
        sportCategoryRepository.save(sportCategory);
        return false;
    }

    public void updateSportCategory(SportCategory sportCategory) {
        sportCategoryRepository.save(sportCategory);
    }

    public List<SportCategoryBody> getAllSportSportCategories() {
        List<SportCategoryBody> sportCategoryBodies = new ArrayList<>();
        sportCategoryRepository.findAll().forEach(sportCategory -> sportCategoryBodies.add(sportCategory.buildBody()));
        return sportCategoryBodies;
    }

    public SportCategory getSportCategoryByid(Integer id) {
        try {
            SportCategory sportCategory = sportCategoryRepository.findById(id).get();
            return sportCategory;
        } catch (NoSuchElementException nse) {
            return null;
        }
    }

    public void deleteSportCategory(Integer id) {
        sportCategoryRepository.deleteById(id);
    }

    public List<SportCategory> getSportCategoriesByTypeId(Integer id) {
        List<SportCategory> sportCategories = sportCategoryRepository.findByTypeId(id);
        return sportCategories;
    }
}
