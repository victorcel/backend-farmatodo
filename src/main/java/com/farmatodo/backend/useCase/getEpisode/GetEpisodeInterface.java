package com.farmatodo.backend.useCase.getEpisode;

import com.farmatodo.backend.dto.EpisodeDTO;

public interface GetEpisodeInterface {
    EpisodeDTO show(String id) throws Exception;
}
