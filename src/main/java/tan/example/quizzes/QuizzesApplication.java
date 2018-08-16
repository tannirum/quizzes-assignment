package tan.example.quizzes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("applicationContext.xml")
public class QuizzesApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizzesApplication.class, args);
	}

}
