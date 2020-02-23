package org.payara.microprofile.rest.person.mapper;

import io.openapitools.jackson.dataformat.hal.HALLink;
import org.payara.microprofile.rest.person.entity.Person;
import org.payara.microprofile.rest.person.PersonResource;
import org.payara.microprofile.rest.person.model.PersonHal;

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
