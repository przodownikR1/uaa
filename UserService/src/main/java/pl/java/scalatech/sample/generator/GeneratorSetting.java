package pl.java.scalatech.sample.generator;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix="generator")
class GeneratorSetting {
    @NotNull  
    Integer count;
    
}
