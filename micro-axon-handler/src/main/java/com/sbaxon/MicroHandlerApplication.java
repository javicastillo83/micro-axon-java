package com.sbaxon;

import org.axonframework.springboot.autoconfig.JpaAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {JpaAutoConfiguration.class, JpaEventStoreAutoConfiguration.class})
public class MicroHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroHandlerApplication.class, args);
    }

}
