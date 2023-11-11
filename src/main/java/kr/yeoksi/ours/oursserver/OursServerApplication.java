package kr.yeoksi.ours.oursserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class OursServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OursServerApplication.class, args);
    }

}
