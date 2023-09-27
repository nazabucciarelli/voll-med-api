package med.voll.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().
                addSecuritySchemes("bearer-key",
                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))).info(
                        new Info().title("API Voll Med By Nazareno Bucciarelli").description("API Rest for a" +
                                " simulated Hospital made in order to learn about Spring Boot.").contact(new Contact()
                                .name("Equipo de Backend").email("tata@gmail.com"))
                );
    }

    @Bean
    public void message(){
        System.out.println("Bearer working");
    }

}
