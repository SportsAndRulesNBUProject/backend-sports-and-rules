package com.nbu.sportsandrules.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SportCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "type_id")
    private SportType type;

    private String description;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Sport> sports;

    public SportCategory() {
    }

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

    public SportType getType() {
        return type;
    }

    public void setType(SportType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Sport> getSports() {
        return sports;
    }

    public void setSports(Set<Sport> sports) {
        this.sports = sports;
    }

}
