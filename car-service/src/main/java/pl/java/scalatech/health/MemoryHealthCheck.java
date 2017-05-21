package pl.java.scalatech.health;

import static com.codahale.metrics.health.HealthCheck.Result.healthy;
import static com.codahale.metrics.health.HealthCheck.Result.unhealthy;
import static java.lang.Runtime.getRuntime;

import com.codahale.metrics.health.HealthCheck;

public class MemoryHealthCheck extends HealthCheck {

    private static final String LOW_MEMORY = "Low Memory";

    @Override
    protected Result check() throws Exception {
        // metrics/healthcheck
        final long pctMem = (getRuntime().totalMemory() - getRuntime().freeMemory()) * 100 / getRuntime().totalMemory();
        return pctMem < 80 ? healthy() : unhealthy(LOW_MEMORY);

    }

}