package org.payara.microprofile.rest;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
@OpenAPIDefinition(info = @Info(
        title = "Microprofile REST application",
        version = "1.0.0",
        contact = @Contact(name = "Flippii", url = "https://github.com/")),
        servers = {
                @Server(url = "http://localhost:8080/rest-micro", description = "localhost")
        }
)
public class JaxrsApplication extends Application { }
