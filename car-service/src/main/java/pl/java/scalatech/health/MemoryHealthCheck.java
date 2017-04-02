package pl.java.scalatech.health;

import com.codahale.metrics.health.HealthCheck;

public class MemoryHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        //metrics/healthcheck
        final long pctMem = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) * 100 / Runtime.getRuntime().totalMemory();
        return pctMem < 80 ? Result.healthy() : Result.unhealthy("Low Memory");
        
    }

}