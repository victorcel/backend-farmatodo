package com.farmatodo.backend.configuration;


import com.farmatodo.backend.useCase.Summation.SummationInterface;
import com.farmatodo.backend.useCase.Summation.SummationUseCase;
import com.farmatodo.backend.useCase.getEpisode.GetEpisode;
import com.farmatodo.backend.useCase.getEpisode.GetEpisodeInterface;
import com.farmatodo.backend.useCase.happyNumber.HappyNumberInterface;
import com.farmatodo.backend.useCase.happyNumber.HappyNumberUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfiguration {

    @Bean
    public HappyNumberInterface happyNumber() {
        return new HappyNumberUseCase();
    }

    @Bean
    public SummationInterface summation() {
        return new SummationUseCase();
    }

    @Bean
    public GetEpisodeInterface getEpisode() {
        return new GetEpisode();
    }
}
