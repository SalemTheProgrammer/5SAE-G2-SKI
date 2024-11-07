package tn.esprit.spring.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**") // Autoriser toutes les requêtes
                    .allowedOrigins("http://localhost:4200") // Remplacez par l'URL de votre frontend
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
        }
    }


