package br.com.firstdecision.user.registration.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoModelMapper {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
