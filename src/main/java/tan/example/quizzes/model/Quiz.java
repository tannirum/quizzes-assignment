package tan.example.quizzes.model;

import java.util.ArrayList;
import java.util.List;

import tan.example.quizzes.QuizzesService;
import tan.example.quizzes.impl.QuizzesServiceImpl;

public class Quiz {
	protected long id;
	protected List<MultipleChoiceQuestion> questions = new ArrayList<MultipleChoiceQuestion>();
	// protected Float totalPoints;
	protected long studentId;

	protected String title;

	public Quiz(long id, String title, long studentId) {
		this.id = id;
		this.title = title;
		this.studentId = studentId;
	}

	public long getId() {
		return id;
	}

	public List<MultipleChoiceQuestion> getQuestions() {
		return questions;
	}

	public long getStudentId() {
		return studentId;
	}

	public String getTitle() {
		return title;
	}
}
