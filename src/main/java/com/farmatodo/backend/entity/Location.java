package com.farmatodo.backend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="location", schema = "db-farmatodo")
public class Location {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="name", nullable = false, length = 250)
    private String name;

    @Column(name="type", nullable = false, length = 250)
    private String type;

    @Column(name="dimension", nullable = false, length = 250)
    private String dimension;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<Character> characters;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }
}
