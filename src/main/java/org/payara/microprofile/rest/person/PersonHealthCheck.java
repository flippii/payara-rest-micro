package org.payara.microprofile.rest.person;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

@Liveness
@Readiness
@ApplicationScoped
public class PersonHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        long memUsed = memBean.getHeapMemoryUsage().getUsed();
        long memMax = memBean.getHeapMemoryUsage().getMax();

        return HealthCheckResponse.named(PersonResource.class.getSimpleName() + "Health")
                .withData("memory used", memUsed)
                .withData("memory max", memMax)
                .state(memUsed < memMax * 0.9).build();
    }

}
