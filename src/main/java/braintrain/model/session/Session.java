package braintrain.model.session;

import java.util.ArrayList;
import java.util.List;

import braintrain.model.card.SrsCard;
import braintrain.model.card.exceptions.MissingCoreException;
import braintrain.quiz.Quiz;
import braintrain.quiz.QuizCard;

/**
 * Represents a session that stores cards based on srs data.
 */
public class Session {
    public static final int CARD_COUNT_MINIMUM = 5;

    private String name;
    private Quiz.Mode mode;
    private int cardCount;
    private List<QuizCard> quizCards;
    private List<SrsCard> srsCards;

    public Session(String name, int cardCount, Quiz.Mode mode) {
        this.name = name;
        this.cardCount = cardCount;
        this.mode = mode;
    }

    public Session(String name, int cardCount, Quiz.Mode mode, List<SrsCard> srsCards) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (cardCount < CARD_COUNT_MINIMUM) {
            throw new IllegalArgumentException("CardCount should not be less than five in a single session");
        }
        if ((mode != Quiz.Mode.LEARN) & (mode != Quiz.Mode.REVIEW) & (mode != Quiz.Mode.PREVIEW)) {
            throw new IllegalArgumentException("Invalid mode");
        }

        quizCards = new ArrayList<>();

        this.name = name;
        this.cardCount = cardCount;
        this.mode = mode;
        this.srsCards = srsCards;
    }

    public Session(String name, Quiz.Mode mode, List<SrsCard> srsCards) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Invalid name");
        }
        if ((mode != Quiz.Mode.LEARN) & (mode != Quiz.Mode.REVIEW) & (mode != Quiz.Mode.PREVIEW)) {
            throw new IllegalArgumentException("Invalid mode");
        }

        quizCards = new ArrayList<>();

        this.name = name;
        this.cardCount = 10;
        this.mode = mode;
        this.srsCards = srsCards;
    }

    /**
     * Generate a list of quizCards that will pass to quiz system.
     */
    public List<QuizCard> generateSession() throws MissingCoreException {
        SrsCard currentCard;
        for (int i = 0; i < cardCount; i++) {
            currentCard = srsCards.get(i);
            quizCards.add(new QuizCard(currentCard.getQuestion(), currentCard.getAnswer()));
        }
        return quizCards;
    }

    public Quiz.Mode getMode() {
        return mode;
    }

    public String getName() {
        return name;
    }
}
