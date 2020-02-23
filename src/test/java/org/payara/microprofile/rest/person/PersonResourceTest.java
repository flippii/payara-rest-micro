package org.payara.microprofile.rest.person;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.payara.microprofile.rest.person.model.PersonHal;
import org.testcontainers.junit.jupiter.Container;

import javax.ws.rs.core.UriInfo;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MicroShedTest
@ExtendWith(MockitoExtension.class)
public class PersonResourceTest {

    @Container
    public static ApplicationContainer app = new ApplicationContainer()
            .withAppContextRoot("/rest-micro")
            .withReadinessPath("/rest-micro/api/person");

    @RESTClient
    public static PersonResource personResource;

    @Mock
    private UriInfo uriInfo;

    @Test
    public void testGetAllPeople() {
        List<PersonHal> persons = personResource.getAllPeople(uriInfo);

        assertThat(persons).isNotNull().isEmpty();
    }

}
