package com.nbu.sportsandrules.controller.body;

import javax.validation.ConstraintViolationException;

import com.nbu.sportsandrules.entity.SportCategory;

public class SportCategoryBody {
    private String name;

    private String description;

    private Integer typeId;

    public SportCategory initSportCategory() {
        if (name == null) {
            throw new ConstraintViolationException("Sport Category Name Cannot be null.", null);
        }

        SportCategory newCetegory = new SportCategory();
        newCetegory.setDescription(description);
        newCetegory.setName(name);
        return newCetegory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

}
