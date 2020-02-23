package org.payara.microprofile.rest.integration.hal;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import io.openapitools.jackson.dataformat.hal.HALMapper;

import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@Consumes({ "application/hal+json", MediaType.APPLICATION_JSON })
@Produces({ "application/hal+json", MediaType.APPLICATION_JSON })
public class HalJsonProvider extends JacksonJsonProvider {

    private static final Logger LOG = Logger.getLogger(HalJsonProvider.class.getName());

    public HalJsonProvider() {
        this(new HALMapper());
    }

    public HalJsonProvider(ObjectMapper mapper) {
        LOG.log(Level.INFO, "Register Hal Json Provider.");
        mapper.registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        setMapper(mapper);
    }
}
