package pharama.example.adminpanel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing; // Import this

@SpringBootApplication
@EnableJpaAuditing // <--- ADD THIS LINE HERE
public class AdminpanelApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminpanelApplication.class, args);
    }
}