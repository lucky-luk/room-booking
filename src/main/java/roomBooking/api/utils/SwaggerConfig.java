package roomBooking.api.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("roomBooking.api"))
//                .paths(regex("/api.*"))
                .paths(any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "room-booking-app",
                "Room-booking application provides necessary elements for managing a booking service " +
                        "such as Property, Client and Host information as well as Booking and Logging features."+"\n" +
                        "It also contains a api.nbp connection option and queries needed for statistical purpose.",
                "v1",null, new Contact("Java14",null,null),null,
                null, Collections.emptyList());
    }
}
