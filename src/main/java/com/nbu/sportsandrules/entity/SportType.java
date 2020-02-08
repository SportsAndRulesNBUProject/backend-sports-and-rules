package com.nbu.sportsandrules.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SportType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @NotBlank
    private String name;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<SportCategory> sportCategories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<SportCategory> getSportCategories() {
        return sportCategories;
    }

    public void setSportCategories(Set<SportCategory> sportCategories) {
        this.sportCategories = sportCategories;
    }
}
