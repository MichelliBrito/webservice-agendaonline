package agendaonline.com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	 @Bean
	    public Docket productApi() { //configurando a documentação do web service
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("agendaonline.com"))
	                .paths(regex("/agendaonline.*"))
	                .build()
	                .apiInfo(metaInfo());
	    }

	    private ApiInfo metaInfo() {

	        ApiInfo apiInfo = new ApiInfo(
	                "Agenda Online API Rest",
	                "Web service para cadastro de consultas, pacientes, prontuários, etc...",
	                "1.0",
	                "Terms of Service",
	                new Contact("Michelli Brito", "https://www.youtube.com/channel/UC2WbG8UgpPaLcFSNJYwtPow?view_as=subscriber",
	                        "michellidibrito@gmail.com"),
	                "Apache License Version 2.0",
	                "https://www.apache.org/licesen.html"
	        );

	        return apiInfo;
	    }
}
