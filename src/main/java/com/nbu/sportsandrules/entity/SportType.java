package com.nbu.sportsandrules.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nbu.sportsandrules.controller.body.SportTypeBody;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@Entity
public class SportType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @NotBlank
    private String name;

    private String description;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @JsonIgnore
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<SportCategory> sportCategories;

    public SportTypeBody buildBody() {
        SportTypeBody sportTypeBody = new SportTypeBody();
        sportTypeBody.setName(name);
        sportTypeBody.setDescription(description);
        sportTypeBody.setImage(Base64.encode(image));
        sportTypeBody.setId(id);
        return sportTypeBody;
    }


    public SportType() {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
