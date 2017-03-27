package pl.java.scalatech.metrics;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;
@Configuration
@ConditionalOnProperty(name = {
        "metrics.graphite.host",
        "metrics.graphite.port",
        "metrics.graphite.prefix"}
)
@Slf4j
@EnableMetrics
public class GraphiteConfig extends MetricsConfigurerAdapter  {
    @Autowired
    private MetricRegistry metricRegistry;
    @Value("${metrics.graphite.host}")
    private String host;
    @Value("${metrics.graphite.port}")
    private int port;
    @Value("${metrics.graphite.prefix}")
    private String prefix;
    
    @PostConstruct
    public void startGraphiteReporter() throws UnknownHostException {
        log.info("+++ graphite metrics enabled ");
        String hostname = InetAddress.getLocalHost().getHostName();

        Graphite graphite = new Graphite(host,port);
        GraphiteReporter reporter = GraphiteReporter
                .forRegistry(metricRegistry)
                .prefixedWith(prefix + hostname)
                .build(graphite);
        reporter.start(10, TimeUnit.SECONDS);
    }
}
