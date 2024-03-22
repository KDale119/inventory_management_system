package edu.mcc.codeschool.inventorySystem.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Kayla Dale",
                        email = "kdale@mail.mccneb.edu",
                        url = "https://github.com/KDale119"
                ),
                description = "Open API Spec Documentation for Inventory Management System",
                title = "Inventory Management System",
                version = "1.0"
        )
)

public class OpenAPIConfig {
}
