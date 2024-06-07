package za.co.dsignweb.studentmanager.integration.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Student Manager API",
                description = "Student Manager Service",
                contact = @Contact(
                        url = "https://www.linkedin.com/in/wandi-tobosi-6b021ba9/"
                ),
                version = "1.0"
        )
)
public class SwaggerConfig {
}
