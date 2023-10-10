package fr.ishtamar.chatop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    public static ModelMapper modelMapper=new ModelMapper();
}
