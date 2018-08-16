package tan.example.quizzes.model;

public class Question {

	protected String description;

	protected long id;

	protected String title;

	protected QuestionType type;

	public String getDescription() {
		return description;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public QuestionType getType() {
		return type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	protected void setId(long id) {
		this.id = id;
	}

	protected void setTitle(String title) {
		this.title = title;
	}

	protected void setType(QuestionType type) {
		this.type = type;
	}
}
