package tan.example.quizzes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MultipleChoiceQuestionChoice {

	@JsonIgnore
	protected boolean correct = false;

	protected long id;

	protected String text = null;

	public MultipleChoiceQuestionChoice(long id, String text, boolean correct) {
		this.id = id;
		this.text = text != null ? text.trim() : null;
		this.correct = correct;
	}

	public boolean getCorrect() {
		return this.correct;
	}

	public long getId() {
		return this.id;
	}

	public String getText() {
		return this.text;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public void setText(String text) {
		this.text = text != null ? text.trim() : null;
	}
}
