package com.nbu.sportsandrules.controller.body;

import com.nbu.sportsandrules.entity.SportType;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class SportTypeBody {
    private Integer id;
    private String name;
    private String description;
    private String image;

    public SportType build() {
        SportType sportType = new SportType();
        sportType.setName(name);
        sportType.setDescription(description);
        sportType.setImage(Base64.decode(image));

        return sportType;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
