package com.springboot.betterreads_app.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

@ConfigurationProperties(prefix = "datastax.astra")
@Data
public class DatastaxAstraProperties {
    private File secureConnectBundle;
}
