package ro.societateahermes.backendservice.entities.form;

public enum QuestionType {
    SHORT_ANSWER(1),
    LONG_ANSWER(2),
    SINGLE_CHOICE(3),
    MULTIPLE_CHOICE(4);

    private final int id;

    QuestionType(int id) {
        this.id = id;
    }

    public static QuestionType getType(Integer id) {
        if (id == null) {
            return null;
        }

        for (QuestionType questionType : QuestionType.values()) {
            if (id.equals(questionType.getId())) {
                return questionType;
            }
        }

        throw new IllegalArgumentException("No matching question type for id " + id);
    }

    public int getId() {
        return id;
    }
}
