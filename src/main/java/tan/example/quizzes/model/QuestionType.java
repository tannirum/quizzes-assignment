package tan.example.quizzes.model;

public enum QuestionType {

	MULTIPLE_CHOICE(1);

	private final int type;

	QuestionType(int type) {
		this.type = type;
	}

	public int type() {
		return type;
	}
}
