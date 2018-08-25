package tan.example.quizzes.resource;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tan.example.quizzes.QuizzesService;
import tan.example.quizzes.model.Quiz;

@RestController
public class QuizController {

	@Autowired
	protected QuizzesService quizzesService;
	protected final static Logger logger = Logger.getLogger("QuizController");

	/**
	 * API to get quiz
	 * 
	 * @param studentId
	 *            Student id
	 * 
	 * @return Quiz with questions
	 */
	@RequestMapping(value = "/api/quiz/studentid/{studentId:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	public Quiz quizGet(@PathVariable("studentId") long studentId) {

		logger.info("quizGet - Get a quiz for student with id=" + studentId);

		Quiz quiz = quizzesService.getQuiz(studentId);

		return quiz;
	}

	/**
	 * Submits the quiz to evaluate
	 * 
	 * @param quiz
	 *            Quiz
	 * 
	 * @return percentage of correct answers
	 */
	@RequestMapping(value = "/api/quiz/submit", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public String submitQuizPut(@RequestBody Quiz quiz) {
		logger.info("submitQuizPut quiz : " + quiz);

		Optional<Float> precentCorrect = quizzesService.evaluate(quiz);

		if (precentCorrect.isPresent()) {
			logger.info("submitQuizPut quiz : " + precentCorrect.get().floatValue());
			return precentCorrect.get().toString();
		}

		return "";
	}
}
