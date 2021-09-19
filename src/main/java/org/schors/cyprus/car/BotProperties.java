package org.schors.cyprus.car;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@ConfigurationProperties("cyprus.car.bot")
public class BotProperties {
    private String greeting;
    private List<Map> questions;
    private String farewell;
    private List<String> admins;
    private String username;
    private String token;
    private String cancel;
}
