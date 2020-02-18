package com.nbu.sportsandrules.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nbu.sportsandrules.controller.body.SportCategoryBody;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

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

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    public SportCategory() {
    }

    public SportCategoryBody buildBody() {
        SportCategoryBody sportCategoryBody = new SportCategoryBody();
        sportCategoryBody.setName(name);
        sportCategoryBody.setDescription(description);
        sportCategoryBody.setImage(Base64.encode(image));
        sportCategoryBody.setId(id);
        return sportCategoryBody;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
