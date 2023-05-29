package com.basis.RRM.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.email")
public class ApplicationProperties {
    public String enderecoRemetente;
    public String nomeRemetente;
}
