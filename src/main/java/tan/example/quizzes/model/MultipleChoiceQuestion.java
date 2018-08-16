package tan.example.quizzes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MultipleChoiceQuestion extends Question {

	List<MultipleChoiceQuestionChoice> choices = new ArrayList<MultipleChoiceQuestionChoice>();
	long selectedAnswer;

	public MultipleChoiceQuestion(long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.type = QuestionType.MULTIPLE_CHOICE;
	}

	public List<MultipleChoiceQuestionChoice> getChoices() {
		return choices;
	}

	public long getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setChoices(List<MultipleChoiceQuestionChoice> choices) {
		// TODO check only one choice should be correct

		this.choices = choices;
	}

	public void setSelectedAnswer(long selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	Optional<MultipleChoiceQuestionChoice> getCorrectAnswer() {

		return choices.stream().findFirst().filter(c -> c.correct);
	}
}
