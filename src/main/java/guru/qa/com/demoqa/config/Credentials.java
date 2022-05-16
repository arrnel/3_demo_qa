package guru.qa.com.demoqa.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:credentials.properties")
public interface Credentials extends Config {

    String login();

    String password();

}
