package seedu.address.model.modelmanager.quiz;

import java.util.List;

import seedu.address.model.modelmanager.Model;
import seedu.address.model.session.Session;

/**
 * The API of the QuizModel component.
 */
public interface QuizModel extends Model {
    /**
     * Generate a list of quizCards that will be tested in quiz.
     */
    List<QuizCard> generateSession();

    /**
     * Return mode of {@code session}.
     */
    Quiz.Mode getMode();

    /**
     * Return card count of {@code session}.
     */
    int getCount();

    /**
     * Return name of {@code session}.
     */
    String getName();

    /**
     * Sets the {@code Quiz} information.
     */
    void init(Quiz quiz);

    /**
     * Sets the {@code Quiz} and {@code Session} information.
     */
    void initWithSession(Quiz quiz, Session session);

    /**
     * Returns if there is still card left in {@code Quiz}.
     */
    boolean hasCardLeft();

    /**
     * Returns the next card in line for {@code Quiz}.
     */
    QuizCard getNextCard();

    /**
     * Returns the user current progress in {@code Quiz}.
     */
    String getCurrentProgress();

    /**
     * Returns the current QuizCard in {@code Quiz}.
     */
    QuizCard getCurrentQuizCard();

    /**
     * Update the totalAttempts and streak of a specified card in the current session.
     * @param index of the current {@code QuizCard}
     * @param answer user input
     * @return true if correct
     */
    boolean updateTotalAttemptsAndStreak(int index, String answer);

    /**
     * Returns total attempts in this {@code Quiz}.
     */
    int getQuizTotalAttempts();

    /**
     * Returns the total correct questions attempted in this {code Quiz}.
     */
    int getQuizTotalCorrectQuestions();

    /**
     * Returns if User is done with {@code Quiz}.
     */
    boolean isQuizDone();

    /**
     * Returns data needed by {@code Session} when {@code Quiz} end.
     */
    List<List<Integer>> end();
}