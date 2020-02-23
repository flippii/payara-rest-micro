package org.payara.microprofile.rest.integration.errors;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOG = Logger.getLogger(GenericExceptionMapper.class.getName());

    @Override
    public Response toResponse(Throwable ex) {
        Response.StatusType type = getStatusType(ex);
        Error error = Error.of(type.getStatusCode(), type.getReasonPhrase(), ex.getLocalizedMessage());

        LOG.log(Level.WARNING, "Handle Error: {0}", error);

        return Response.status(error.getStatusCode())
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response.StatusType getStatusType(Throwable ex) {
        if (ex instanceof WebApplicationException) {
            return((WebApplicationException) ex).getResponse().getStatusInfo();
        } else {
            return Response.Status.INTERNAL_SERVER_ERROR;
        }
    }

}
