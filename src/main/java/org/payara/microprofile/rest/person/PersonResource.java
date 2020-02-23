package org.payara.microprofile.rest.person;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.payara.microprofile.rest.person.entity.Person;
import org.payara.microprofile.rest.person.mapper.PersonMapper;
import org.payara.microprofile.rest.person.model.PersonHal;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/person")
@ApplicationScoped
@Produces({"application/hal+json"})
@Consumes({"application/hal+json"})
public class PersonResource {

    private static final Logger LOG = Logger.getLogger(PersonResource.class.getName());

    @Inject
    private PersonService personService;

    @Inject
    private PersonMapper personMapper;

    @POST
    @Operation(summary = "Create a person.")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "The created person object.",
                    content = @Content(mediaType = "application/hal+json",
                            schema = @Schema(type = SchemaType.OBJECT, implementation = PersonHal.class))
            )
    })
    @Parameters({
            @Parameter(name = "newPerson", description = "Create person object.", required = true,
                    content = @Content(schema = @Schema(implementation = Person.class)))
    })
    public Response createPerson(@Context UriInfo uriInfo, @Valid Person newPerson) {
        LOG.log(Level.INFO, "REST request to save Person : {0}.", newPerson);
        if (newPerson.getId() != null) {
            throw new PersonException("A person need no id for update.");
        }
        personService.create(newPerson);
        return Response
                .ok(personMapper.map(newPerson, uriInfo))
                .build();
    }

    @PUT
    @Operation(summary = "Update a person.")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "The updated person object.",
                    content = @Content(mediaType = "application/hal+json",
                            schema = @Schema(type = SchemaType.OBJECT, implementation = PersonHal.class))
            )
    })
    @Parameters({
            @Parameter(name = "updatedPerson", description = "Update person object.", required = true,
                    content = @Content(schema = @Schema(implementation = Person.class)))
    })
    public Response updatePerson(@Context UriInfo uriInfo, @Valid Person updatedPerson) {
        LOG.log(Level.INFO, "REST request to update Person : {0}", updatedPerson);
        if (updatedPerson.getId() == null) {
            throw new PersonException("A person need an id for update.");
        }
        Person person = personService.edit(updatedPerson);
        return Response
                .ok(personMapper.map(person, uriInfo))
                .build();
    }

    @GET
    @Operation(summary = "Get a list of all People.")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "The list of all People.",
                    content = @Content(mediaType = "application/hal+json",
                            schema = @Schema(type = SchemaType.ARRAY, implementation = PersonHal.class))
            )
    })
    public List<PersonHal> getAllPeople(@Context UriInfo uriInfo) {
        LOG.log(Level.INFO, "REST request to get all People.");
        return personService.findAll()
                .stream()
                .map(person -> personMapper.map(person, uriInfo))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get the Person with id.")
            @APIResponses({
                    @APIResponse(responseCode = "200", description = "The Person with id.",
                            content = @Content(mediaType = "application/hal+json",
                                    schema = @Schema(type = SchemaType.OBJECT, implementation = PersonHal.class))
                    ),
                    @APIResponse(responseCode = "400", description = "Person not found.")
    })
    @Parameters({
            @Parameter(name = "id", description = "The person id. ", required = true)
    })
    public Response getPerson(@Context UriInfo uriInfo, @PathParam("id") @NotNull Long id) {
        LOG.log(Level.INFO, "REST request to get Person : {0}", id);
        return personService.find(id)
                .map(person -> Response.ok(personMapper.map(person, uriInfo)).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete the Person with id.")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Person with id is deleted.")
    })
    @Parameters({
            @Parameter(name = "id", description = "The person id. ", required = true)
    })
    public Response removePerson(@PathParam("id") @NotNull Long id) {
        LOG.log(Level.INFO, "REST request to delete Person : {0}", id);
        personService.find(id)
                .ifPresent(person -> personService.remove(person));
        return Response.ok().build();
    }

}
