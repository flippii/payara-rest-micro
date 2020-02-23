package org.payara.microprofile.rest.resources.mapper;

import io.openapitools.jackson.dataformat.hal.HALLink;
import org.payara.microprofile.rest.entity.Person;
import org.payara.microprofile.rest.resources.PersonResource;
import org.payara.microprofile.rest.resources.model.PersonHal;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
public class PersonMapper {

    public PersonHal map(Person person, UriInfo uriInfo) {
        return PersonHal.of(
                person.getId(),
                person.getName(),
                person.getAddress(),
                selfLink(person, uriInfo));
    }

    private HALLink selfLink(Person person, UriInfo uriInfo) {
        return new HALLink.Builder(uriInfo.getBaseUriBuilder()
                .path(PersonResource.class)
                .path(PersonResource.class, "getPerson")
                .build(person.getId()))
                .build();
    }

}
