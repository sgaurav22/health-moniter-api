package com.clipboard.health.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Clipboard API",
                version = "1.0",
                description = "Rest API for Clipboard",
                contact = @Contact(
                        name = "Gaurav Saxena",
                        email = "saxenagau@gmail.com"
                ),
                license = @License(
                        name = "licence",
                        url = "https://www.clipboardhealth.com"
                )
        )
)
public class SwaggerConfig {
}
