package tan.example.quizzes.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import tan.example.quizzes.QuizzesService;
import tan.example.quizzes.model.MultipleChoiceQuestion;
import tan.example.quizzes.model.MultipleChoiceQuestionChoice;
import tan.example.quizzes.model.Quiz;

public class QuizzesServiceImpl implements QuizzesService {

	// TODO make it a bean later
	private final AtomicLong counter = new AtomicLong();

	protected List<MultipleChoiceQuestion> questionPool = new ArrayList<MultipleChoiceQuestion>();
	protected Map<Long, MultipleChoiceQuestion> questionPoolMap = new HashMap<Long, MultipleChoiceQuestion>();

	protected Logger logger = Logger.getLogger("QuizzesServiceLogger");

	public void init() {
		logger.info("In init.....");

		buildQuestionPool();
	}

	public void destroy() {
		logger.info("In destroy.....");
	}

	@Override
	public Optional<Float> evaluate(Quiz quiz) {

		if (quiz == null || quiz.getQuestions().isEmpty()) {
			return Optional.empty();
		}

		/*
		 * int numberCorrect = 0; for (MultipleChoiceQuestion question :
		 * quiz.getQuestions()) { if (question.getSelectedAnswer() > 0) {
		 * MultipleChoiceQuestion actualQuestion =
		 * getQuestionFromPool(question.getId());
		 * 
		 * logger.info("actualQuestion: " + actualQuestion); /* for
		 * (MultipleChoiceQuestionChoice choice : actualQuestion.getChoices()) { if
		 * (question.getSelectedAnswer() == choice.getId() && choice.getCorrect()) {
		 * numberCorrect++; break; } }
		 *
		 * Optional<MultipleChoiceQuestionChoice> matchedChoice =
		 * actualQuestion.getChoices().stream() .filter(c -> c.getId() ==
		 * question.getSelectedAnswer() && c.getCorrect()).findFirst();
		 * 
		 * if (matchedChoice.isPresent()) { numberCorrect++; }
		 * 
		 * } }
		 */
		// final Integer[] numberCorrect = { 0 };
		List<Integer> numberCorrect = new ArrayList<Integer>();
		numberCorrect.add(0);

		quiz.getQuestions().stream().forEach(question -> {
			if (question.getSelectedAnswer() > 0) {
				MultipleChoiceQuestion actualQuestion = getQuestionFromPool(question.getId());

				logger.info("actualQuestion: " + actualQuestion);
				/*
				 * for (MultipleChoiceQuestionChoice choice : actualQuestion.getChoices()) { if
				 * (question.getSelectedAnswer() == choice.getId() && choice.getCorrect()) {
				 * numberCorrect++; break; } }
				 */
				Optional<MultipleChoiceQuestionChoice> matchedChoice = actualQuestion.getChoices().stream()
						.filter(c -> c.getId() == question.getSelectedAnswer() && c.getCorrect()).findFirst();

				if (matchedChoice.isPresent()) {
					// numberCorrect[0]++;
					Integer num = numberCorrect.get(0);
					num++;
					numberCorrect.clear();
					numberCorrect.add(num);
					
				}
			}
		});

		// logger.info("QuizzesServiceImpl - evaluteQuiz - numberCorrect: " +
		// numberCorrect);
		// logger.info("QuizzesServiceImpl - evaluteQuiz - quiz.getQuestions().size(): "
		// + quiz.getQuestions().size());

		// int percentCorrect = (numberCorrect * 100 * 100) / quiz.getQuestions().size();
		//int percentCorrect = (numberCorrect[0] * 100 * 100) / quiz.getQuestions().size();
		int percentCorrect = (numberCorrect.get(0) * 100 * 100) / quiz.getQuestions().size();
		
		Optional<Float> correctPercent = Optional.ofNullable((float) percentCorrect / 100);

		// logger.info("QuizzesServiceImpl - evaluteQuiz - correctPercent: " +
		// correctPercent);
		return correctPercent;
	}

	@Override
	public Quiz getQuiz(long studentId) {

		long quizId = counter.incrementAndGet();
		Quiz quiz = new Quiz(quizId, "Quiz " + quizId, studentId);
		quiz.getQuestions().addAll(getQuestionsFromPool(7));
		return quiz;
	}

	protected MultipleChoiceQuestion getQuestionFromPool(long questionId) {

		return questionPoolMap.get(questionId);
	}

	protected void buildQuestionPool() {
		int questionId = 1;
		int choiceId = 1;
		// Q 1
		MultipleChoiceQuestion mcq1 = new MultipleChoiceQuestion(questionId++, "What is HTML?", "Select the closest answer");
		MultipleChoiceQuestionChoice mc1 = new MultipleChoiceQuestionChoice(choiceId++, "Related to web.", false);
		mcq1.getChoices().add(mc1);
		MultipleChoiceQuestionChoice mc2 = new MultipleChoiceQuestionChoice(choiceId++, "Hyper Text Markup Language.", true);
		mcq1.getChoices().add(mc2);
		MultipleChoiceQuestionChoice mc3 = new MultipleChoiceQuestionChoice(choiceId++, "It's part of javascript.", false);
		mcq1.getChoices().add(mc3);
		MultipleChoiceQuestionChoice mc4 = new MultipleChoiceQuestionChoice(choiceId++, "It's a language.", false);
		mcq1.getChoices().add(mc4);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q 2
		mcq1 = new MultipleChoiceQuestion(questionId++, "The <input> Element is", "Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "It's an style element.", false);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "Not an element.", false);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "It's a form elemenmt.", true);
		mcq1.getChoices().add(mc3);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q 3
		mcq1 = new MultipleChoiceQuestion(questionId++, "<h1> to <h6> Tags are..", "Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "HTML headings", true);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "to size the HTML", false);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "not tags", false);
		mcq1.getChoices().add(mc3);
		mc4 = new MultipleChoiceQuestionChoice(choiceId++, "useful tags", false);
		mcq1.getChoices().add(mc4);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q 4
		mcq1 = new MultipleChoiceQuestion(questionId++, "The attribute of <form> tag", "Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "head", false);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "size", false);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "length", false);
		mcq1.getChoices().add(mc3);
		mc4 = new MultipleChoiceQuestionChoice(choiceId++, "method", true);
		mcq1.getChoices().add(mc4);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q 5
		mcq1 = new MultipleChoiceQuestion(questionId++, "Markup tags tell the web browser", "Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "How to organise the page", false);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "How to display the page", true);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "How to display message box on page", false);
		mcq1.getChoices().add(mc3);
		mc4 = new MultipleChoiceQuestionChoice(choiceId++, "None of these", false);
		mcq1.getChoices().add(mc4);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q 6
		mcq1 = new MultipleChoiceQuestion(questionId++, "Which of the following attributes of text box control allow to limit the maximum character?",
				"Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "size", false);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "len", false);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "maxlength", true);
		mcq1.getChoices().add(mc3);
		mc4 = new MultipleChoiceQuestionChoice(choiceId++, "all of these", false);
		mcq1.getChoices().add(mc4);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q 7
		mcq1 = new MultipleChoiceQuestion(questionId++, "Web pages starts with which of the following tag?", "Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "<body>", false);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "<title>", false);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "<html>", true);
		mcq1.getChoices().add(mc3);
		mc4 = new MultipleChoiceQuestionChoice(choiceId++, "<form>", false);
		mcq1.getChoices().add(mc4);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q 8
		mcq1 = new MultipleChoiceQuestion(questionId++, "<a> Tag is", "Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "defines a hyperlink, which is used to link from one page to another.", true);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "to show images", false);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "to add text", false);
		mcq1.getChoices().add(mc3);
		mc4 = new MultipleChoiceQuestionChoice(choiceId++, "all of these", false);
		mcq1.getChoices().add(mc4);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q 9
		mcq1 = new MultipleChoiceQuestion(questionId++, "CSS is ", "Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "Common style sheets", false);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "Cascading style sheets", true);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "Code style sheets", false);
		mcq1.getChoices().add(mc3);
		mc4 = new MultipleChoiceQuestionChoice(choiceId++, "all of these", false);
		mcq1.getChoices().add(mc4);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q 10
		mcq1 = new MultipleChoiceQuestion(questionId++, "The body tag usually used after ", "Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "Title tag", false);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "HEAD tag", true);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "Em tag", false);
		mcq1.getChoices().add(mc3);
		mc4 = new MultipleChoiceQuestionChoice(choiceId++, "Form tag", false);
		mcq1.getChoices().add(mc4);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q 11
		mcq1 = new MultipleChoiceQuestion(questionId++, "which of the following tag is used to mark a begining of paragraph ? ",
				"Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "<td>", false);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "<h1>", false);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "<p>", true);
		mcq1.getChoices().add(mc3);
		mc4 = new MultipleChoiceQuestionChoice(choiceId++, "<a>", false);
		mcq1.getChoices().add(mc4);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q 12
		mcq1 = new MultipleChoiceQuestion(questionId++, "What is the correct HTML element for inserting a line break?", "Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "<br>", true);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "<b>", false);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "<break>", false);
		mcq1.getChoices().add(mc3);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q 13
		mcq1 = new MultipleChoiceQuestion(questionId++, "What is the correct HTML for adding a background color?", "Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "<background>yellow</background>", false);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "<body style=\"background-color:yellow;\">", true);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "<body bg=\"yellow\">", false);
		mcq1.getChoices().add(mc3);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q14
		mcq1 = new MultipleChoiceQuestion(questionId++, "Choose the correct HTML element to define emphasized text", "Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "<i>", false);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "<italic>", false);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "<em>", true);
		mcq1.getChoices().add(mc3);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		// Q15
		mcq1 = new MultipleChoiceQuestion(questionId++, "Which of these elements are all <table> elements?", "Select the best answer");
		mc1 = new MultipleChoiceQuestionChoice(choiceId++, "<table><head><tfoot>", false);
		mcq1.getChoices().add(mc1);
		mc2 = new MultipleChoiceQuestionChoice(choiceId++, "<thead><body><tr>", false);
		mcq1.getChoices().add(mc2);
		mc3 = new MultipleChoiceQuestionChoice(choiceId++, "<table><tr><td>", true);
		mcq1.getChoices().add(mc3);
		mc4 = new MultipleChoiceQuestionChoice(choiceId++, "<table><tr><tt>", false);
		mcq1.getChoices().add(mc4);
		// add to pool
		questionPool.add(mcq1);
		questionPoolMap.put(mcq1.getId(), mcq1);

		logger.info("questionPoolMap: " + questionPoolMap.toString());
		logger.info("questionPoolMap: " + questionPoolMap.size());
	}

	// @Override
	protected List<MultipleChoiceQuestion> getQuestionsFromPool(int numberOfQuestions) {

		if (numberOfQuestions > 10) {
			numberOfQuestions = 10;
		}

		if (questionPool.isEmpty()) {
			buildQuestionPool();
		}

		// select questions randomly
		List<MultipleChoiceQuestion> questions = new ArrayList<MultipleChoiceQuestion>();
		List<Integer> numbers = new ArrayList<Integer>();
		MultipleChoiceQuestion question = null;
		Random random = new Random();
		do {
			int next = random.nextInt(questionPool.size());
			if (!numbers.contains(next)) {
				numbers.add(next);
				question = questionPool.get(next);
				questions.add(question);
			}
		} while (numbers.size() < numberOfQuestions);

		return questions.stream().collect(Collectors.toList());
	}

}
