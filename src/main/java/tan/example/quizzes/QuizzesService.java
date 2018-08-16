package tan.example.quizzes;

import java.util.Optional;

import tan.example.quizzes.model.Quiz;

public interface QuizzesService {

	/**
	 * Evaluates quiz and returns the percentage of questions correct
	 * 
	 * @param quiz
	 *            Quiz
	 * 
	 * @return The percentage of questions correct
	 */
	Optional<Float> evaluate(Quiz quiz);

	/**
	 * Get the quiz with questions
	 * 
	 * @param studentId
	 *            Student Id
	 * 
	 * @return Quiz with questions
	 */
	Quiz getQuiz(long studentId);
}
