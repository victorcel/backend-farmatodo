package com.farmatodo.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;

public class Episode {
    private long id;
    private String name;
    private String airDate;
    private String episode;
    private String[] characters;
    private String url;
    private OffsetDateTime created;

    @JsonProperty("id")
    public long getId() { return id; }
    @JsonProperty("id")
    public void setId(long value) { this.id = value; }

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("air_date")
    public String getAirDate() { return airDate; }
    @JsonProperty("air_date")
    public void setAirDate(String value) { this.airDate = value; }

    @JsonProperty("episode")
    public String getEpisode() { return episode; }
    @JsonProperty("episode")
    public void setEpisode(String value) { this.episode = value; }

    @JsonProperty("characters")
    public String[] getCharacters() { return characters; }
    @JsonProperty("characters")
    public void setCharacters(String[] value) { this.characters = value; }

    @JsonProperty("url")
    public String getUrl() { return url; }
    @JsonProperty("url")
    public void setUrl(String value) { this.url = value; }

    @JsonProperty("created")
    public OffsetDateTime getCreated() { return created; }
    @JsonProperty("created")
    public void setCreated(OffsetDateTime value) { this.created = value; }

    public Map<String, Object> toJson() {
        return Map.of(
                "id", this.getId(),
                "name", this.getName(),
                "air_date", this.getAirDate(),
                "episode", this.getEpisode(),
                "characters", this.getCharacters(),
                "url", this.getUrl(),
                "created", this.getCreated()
        );
    }
}