package springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jca.context.SpringContextResourceAdapter;
import springBoot.web.model.User;
import springBoot.web.service.UserService;

@SpringBootApplication
public class ApplicationSping {
    public static void main(String[]args) {

        SpringApplication.run(ApplicationSping.class, args);
    }

}

