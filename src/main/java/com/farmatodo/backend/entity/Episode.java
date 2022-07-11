package com.farmatodo.backend.entity;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "episode", schema = "db-farmatodo")
public class Episode {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "episode_character", schema = "db", joinColumns = {
            @JoinColumn(name = "episode_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "character_id")
            }
    )
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

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

    public Map<String, Object> toJson() {
        return Map.of(
                "id", this.getId(),
                "name", this.getName(),
                "characters", this.getCharacters()
        );
    }
}
