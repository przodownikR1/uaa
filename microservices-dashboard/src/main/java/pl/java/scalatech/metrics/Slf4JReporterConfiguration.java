package pl.java.scalatech.metrics;

import static com.codahale.metrics.Slf4jReporter.LoggingLevel.INFO;
import static java.util.concurrent.TimeUnit.MINUTES;
import static org.slf4j.LoggerFactory.getLogger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;
@Configuration
@EnableMetrics
@ConditionalOnProperty(name = "metrics.slf4j.logger")
@Slf4j
class Slf4JReporterConfiguration extends MetricsConfigurerAdapter {

    @Value("${metrics.slf4j.logger}")
    private String logger;
    @Value("${metrics.slf4j.period:5}")
    private long periodMinutes = 5;

    @Override
    public void configureReporters(final MetricRegistry metricRegistry) {
        log.info("+++ slf4j metrics enabled ");
        Slf4jReporter
                .forRegistry(metricRegistry)
                .outputTo(getLogger(logger))
                .withLoggingLevel(INFO)
                .build()
                .start(periodMinutes, MINUTES);
    }

}

 

