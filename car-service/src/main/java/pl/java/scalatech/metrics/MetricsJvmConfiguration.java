package pl.java.scalatech.metrics;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.FileDescriptorRatioGauge;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableMetrics
@ConditionalOnProperty(name = "metrics.jvm.logger")
@Slf4j
class MetricsJvmConfiguration extends MetricsConfigurerAdapter {

    private static final String FILEDESCRIPTORS_RATIO = "filedescriptors.ratio";
    private static final String MEMORY = "memory";
    private static final String GC = "gc";

    @Override
    public void configureReporters(final MetricRegistry metricRegistry) {
        log.info("+++ jvm metrics enabled ");
        metricRegistry.register(GC, new GarbageCollectorMetricSet());
        metricRegistry.register(MEMORY, new MemoryUsageGaugeSet());
        metricRegistry.register(FILEDESCRIPTORS_RATIO, new FileDescriptorRatioGauge());
    }

}