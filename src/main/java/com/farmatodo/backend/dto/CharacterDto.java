package com.farmatodo.backend.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public record CharacterDto(Integer id, String name, String species,
                           String gender, String image,
                           Set<EpisodeDto> episodes,
                           LocationDto location) implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterDto entity = (CharacterDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.species, entity.species) &&
                Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.image, entity.image) &&
                Objects.equals(this.episodes, entity.episodes) &&
                Objects.equals(this.location, entity.location);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "species = " + species + ", " +
                "gender = " + gender + ", " +
                "image = " + image + ", " +
                "episodes = " + episodes + ", " +
                "location = " + location + ")";
    }
}
