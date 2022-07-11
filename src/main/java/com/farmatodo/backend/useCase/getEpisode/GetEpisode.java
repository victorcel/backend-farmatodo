package com.farmatodo.backend.useCase.getEpisode;

import com.farmatodo.backend.dto.CharacterDTO;
import com.farmatodo.backend.dto.EpisodeDTO;
import com.farmatodo.backend.dto.LocationDTO;
import com.farmatodo.backend.entity.Character;
import com.farmatodo.backend.entity.Episode;
import com.farmatodo.backend.entity.Location;
import com.farmatodo.backend.repository.CharacterRepository;
import com.farmatodo.backend.repository.EpisodeRepository;
import com.farmatodo.backend.repository.LocationRepository;
import com.farmatodo.backend.services.ApiRestExternalService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class GetEpisode implements GetEpisodeInterface{

    @Autowired
    private ApiRestExternalService apiRestExternalService;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public EpisodeDTO show(String id) throws Exception {
        EpisodeDTO response = new EpisodeDTO();

        Episode episodeResponse = apiRestExternalService.getEpisode(id);
        List<Character> charactersResponse = apiRestExternalService.getCharacters(this.getCharacters(episodeResponse));
        List<Location> locationsResponse = apiRestExternalService.getLocation(this.getLocations(charactersResponse));
        Map<Long, Location> locationMap = this.createMapLocation(locationsResponse);
        Set<Character> characters = new LinkedHashSet<>();

        response.setEpisode(id);
        response.setEpisodeName(episodeResponse.getName());
        response.setCharacters(new ArrayList<>());
        for (Character item : charactersResponse) {
            CharacterDTO character = new CharacterDTO();
            character.setName(item.getName());
            character.setSpecies(item.getSpecies());
            character.setGender(item.getGender());
            character.setImage(item.getImage());
            Location locationModel = null;
            if (item.getLocation() != null && item.getLocation().getUrl() != null) {
                String url = item.getLocation().getUrl();
                String idLocation = this.getPart(url, "https://rickandmortyapi.com/api/location/", 1);
                if (idLocation != null && locationMap.containsKey(Long.parseLong(idLocation))) {
                    Location location = locationMap.get(Long.parseLong(idLocation));
                    character.setLocation(new LocationDTO());
                    character.getLocation().setName(location.getName());
                    character.getLocation().setType(location.getType());
                    character.getLocation().setDimension(location.getDimension());
                    locationModel = this.saveLocation(location);
                }
            }
            response.getCharacters().add(character);
            if (locationModel != null) characters.add(this.saveCharacter(item, locationModel));
        }
        this.saveEpisode(episodeResponse, characters);

        return response;
    }

    private Location saveLocation(Location location) {
        Location locationModel = null;

        try {
            locationModel = locationRepository.findById(location.getId()).get();
        } catch (NoSuchElementException exc) { }

        if (locationModel == null) {
            locationModel = new Location();
            locationModel.setId(location.getId());
            locationModel.setName(location.getName());
            locationModel.setType(location.getType());
            locationModel.setDimension(location.getDimension());

            locationRepository.save(locationModel);
        }

        return locationModel;
    }

    private Character saveCharacter(Character character, Location locationModel) {
        Character characterModel = null;

        try {
            characterModel = characterRepository.findById(character.getId()).get();
        } catch (NoSuchElementException exc) {
            throw new RuntimeException(exc);
        }

        if (characterModel == null) {
            characterModel = new Character();
            characterModel.setId(character.getId());
            characterModel.setName(character.getName());
            characterModel.setSpecies(character.getSpecies());
            characterModel.setGender(character.getGender());
            characterModel.setImage(character.getImage());
        }

        characterModel.setLocation(locationModel);
        characterRepository.save(characterModel);

        return characterModel;
    }

    private void saveEpisode(Episode episode, Set<Character> characters) {
        Episode episodeModel = null;

        try {
            episodeModel = episodeRepository.findById(episode.getId()).get();
        } catch (NoSuchElementException exc) { }

        if (episodeModel == null) {
            episodeModel = new Episode();
            episodeModel.setId(episode.getId());
            episodeModel.setName(episode.getName());
            episodeModel.setCharacters(characters);

            episodeRepository.save(episodeModel);
        }
    }

    private Map<Long, Location> createMapLocation(List<Location> locationsResponse) {
        Map<Long, Location> response = new TreeMap<>();

        for (Location location : locationsResponse) {
            response.put(Long.parseLong(location.getId().toString()), location);
        }

        return response;
    }

    private String getCharacters(Episode episode) {
        Set<Long> characters = new LinkedHashSet<>();

        if (episode.getCharacters() != null && !episode.getCharacters().isEmpty()) {
            for (Character str : episode.getCharacters()) {
                    String id = this.getPart(str.getLocation().getUrl(), "https://rickandmortyapi.com/api/character/", 1);
                    if (id != null) characters.add(Long.parseLong(id));
            }
        }

        return characters.toString();
    }

    private String getLocations(List<Character> characters) {
        Set<Long> location = new LinkedHashSet<>();

        for (Character item : characters) {
            if (item.getLocation() != null && item.getLocation().getUrl() != null) {
                String url = item.getLocation().getUrl();
                if (url.contains("https://rickandmortyapi.com/api/location/")) {
                    String id = this.getPart(url, "https://rickandmortyapi.com/api/location/", 1);
                    if (id != null) location.add(Long.parseLong(id));
                }
            }
        }

        return location.toString();
    }

    private String getPart(String str, String regex, int index) {
        String[] parts = str.split(regex);
        if (parts.length > index) {
            return parts[1];
        }
        return null;
    }
}
