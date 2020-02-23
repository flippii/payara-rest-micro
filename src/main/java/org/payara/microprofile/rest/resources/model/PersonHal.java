package org.payara.microprofile.rest.resources.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.openapitools.jackson.dataformat.hal.HALLink;
import io.openapitools.jackson.dataformat.hal.annotation.Link;
import io.openapitools.jackson.dataformat.hal.annotation.Resource;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Resource
@Schema(name="PersonHal", description="POJO that represents a person as Hal.")
public class PersonHal {

    @Schema(required = true, example = "100")
    private Long id;

    @Schema(required = true, example = "Hanz Mustermann")
    private String name;

    @Schema(required = true, example = "Musterstrasse 12")
    private String address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Schema(required = true, example = "2020-12-20")
    private LocalDateTime created;

    @Link
    @Schema(required = true, implementation = HALLink.class, example = "href: http://wwww.example.de/api/people/1")
    private HALLink self;

    private PersonHal(Long id, String name, String address, HALLink self) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.created = LocalDateTime.now();
        this.self = self;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public HALLink getSelf() {
        return self;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public static PersonHal of(Long id, String name, String address, HALLink self) {
        return new PersonHal(id, name, address, self);
    }

}
