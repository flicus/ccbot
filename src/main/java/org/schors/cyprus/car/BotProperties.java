package org.schors.cyprus.car;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("cyprus.car.bot")
public class BotProperties {
    private String username;
    private String token;
}
