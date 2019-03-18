package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.exceptions.CommandException.MESSAGE_EXPECTED_MGT_MODEL;
import static seedu.address.logic.commands.management.ListLessonsCommand.MESSAGE_DELIMITER;
import static seedu.address.logic.commands.management.ListLessonsCommand.MESSAGE_NO_LESSONS;
import static seedu.address.logic.commands.management.ListLessonsCommand.MESSAGE_SUCCESS;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.management.ListLessonsCommand;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.modelmanager.management.ManagementModel;
import seedu.address.model.modelmanager.quiz.Quiz;
import seedu.address.model.modelmanager.quiz.QuizCard;
import seedu.address.model.modelmanager.quiz.QuizModel;
import seedu.address.model.session.Session;
import seedu.address.testutil.TypicalLessons;

/**
 * Contains tests for ListCommand.
 */
public class ListLessonsCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_listNoLessons() {
        ManagementModel modelStub = new ManagementModelStubWithNoLessons();

        assertCommandSuccess(new ListLessonsCommand(), modelStub, commandHistory,
                MESSAGE_SUCCESS + MESSAGE_DELIMITER + MESSAGE_NO_LESSONS, modelStub);
    }

    @Test
    public void execute_listLessons() {
        ManagementModel modelStub = new ManagementModelStubWithLessons();
        ListLessonsCommand listLessonsCommand = new ListLessonsCommand();
        String expectedOutput = listLessonsCommand.buildList(TypicalLessons.getTypicalLessons());

        assertCommandSuccess(new ListLessonsCommand(), modelStub, commandHistory,
                expectedOutput, modelStub);
    }

    @Test
    public void execute_incorrectModel_throwsCommandException() throws Exception {
        QuizModelStub modelStub = new QuizModelStub();
        ListLessonsCommand listLessonsCommand = new ListLessonsCommand();

        thrown.expect(CommandException.class);
        thrown.expectMessage(MESSAGE_EXPECTED_MGT_MODEL);
        listLessonsCommand.execute(modelStub, commandHistory);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ManagementModelStub implements ManagementModel {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Gets the lesson by index.
         */
        @Override
        public Lesson getLesson(int index) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Gets the entire list of lessons.
         */
        @Override
        public List<Lesson> getLessons() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Adds the lesson.
         */
        @Override
        public void addLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates the lesson at the given index.
         */
        @Override
        public void setLesson(int index, Lesson updatedLesson) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Removes the lesson at the given index from memory, and deletes its corresponding file.
         * @param index w
         */
        @Override
        public void deleteLesson(int index) {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ManagementModelStubWithNoLessons extends ManagementModelStub {
        @Override
        public List<Lesson> getLessons() {
            return new ArrayList<>();
        }
    }


    private class ManagementModelStubWithLessons extends ManagementModelStub {
        @Override
        public List<Lesson> getLessons() {
            return TypicalLessons.getTypicalLessons();
        }
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class QuizModelStub implements QuizModel {
        /**
         * This method should not be called.
         */
        public List<QuizCard> generateSession() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * This method should not be called.
         */
        public Quiz.Mode getMode() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * This method should not be called.
         */
        public int getCount() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * This method should not be called.
         */
        public String getName() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets the {@code Quiz} information.
         */
        public void init(Quiz quiz) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets the {@code Quiz} and {@code Session} information.
         */
        public void initWithSession(Quiz quiz, Session session) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns if there is still card left in {@code Quiz}.
         */
        public boolean hasCardLeft() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the next card in line for {@code Quiz}.
         */
        public QuizCard getNextCard() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the current QuizCard in {@code Quiz}.
         */
        public QuizCard getCurrentQuizCard() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Update the totalAttempts and streak of a specified card in the current session.
         * @param index of the current {@code QuizCard}
         * @param answer user input
         */
        public void updateTotalAttemptsAndStreak(int index, String answer) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns if User is done with {@code Quiz}.
         */
        public boolean isDone() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns data needed by {@code Session} when {@code Quiz} end.
         */
        public List<List<Integer>> end() {
            throw new AssertionError("This method should not be called.");
        }
    }
}