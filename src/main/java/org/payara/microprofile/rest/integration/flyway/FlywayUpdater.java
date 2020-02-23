package org.payara.microprofile.rest.integration.flyway;

import org.flywaydb.core.Flyway;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class FlywayUpdater {

    private static final Logger LOG = Logger.getLogger(FlywayUpdater.class.getName());

    @Resource(lookup = "java:global/JPAExampleDataSource")
    private DataSource dataSource;

    @PostConstruct
    public void initFlyway() {
        LOG.log(Level.INFO, "Starting to migrate the database schema with Flyway");

        Flyway.configure()
                .dataSource(dataSource)
                .load()
                .migrate();

        LOG.log(Level.INFO, "Successfully applied latest schema changes");
    }

}
