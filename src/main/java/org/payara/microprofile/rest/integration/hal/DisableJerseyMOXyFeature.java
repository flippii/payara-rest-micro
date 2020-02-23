package org.payara.microprofile.rest.integration.hal;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class DisableJerseyMOXyFeature implements Feature {

    private static final Logger LOG = Logger.getLogger(DisableJerseyMOXyFeature.class.getName());

    @Override
    public boolean configure(FeatureContext ctx) {
        LOG.log(Level.INFO, "Disable Moxy-Json Feature.");
        ctx.property("jersey.config.disableMoxyJson", "true");
        return true;
    }

}
