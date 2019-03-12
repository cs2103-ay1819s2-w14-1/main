package braintrain.logic;

import static braintrain.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import braintrain.logic.commands.CommandResult;
import braintrain.logic.commands.HistoryCommand;
import braintrain.logic.commands.exceptions.CommandException;
import braintrain.logic.parser.exceptions.ParseException;
import braintrain.model.Lessons;
import braintrain.model.Model;
import braintrain.model.ModelManager;
import braintrain.model.UserPrefs;
import braintrain.quiz.Quiz;
import braintrain.quiz.QuizCard;
import braintrain.quiz.QuizModel;
import braintrain.quiz.QuizModelManager;
import braintrain.quiz.commands.AnswerCommand;
import braintrain.storage.CsvLessonImportExport;
import braintrain.storage.CsvLessonsStorage;
import braintrain.storage.JsonUserPrefsStorage;
import braintrain.storage.StorageManager;

public class LogicManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private Model model = new ModelManager();
    private QuizModel quizModel = new QuizModelManager();
    private Logic logic;

    @Before
    public void setUp() throws Exception {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.newFile().toPath());
        CsvLessonsStorage lessonsStorage = new CsvLessonsStorage(temporaryFolder.newFile().toPath());
        CsvLessonImportExport lessonImportExport = new CsvLessonImportExport(temporaryFolder.newFile().toPath());
        StorageManager storage = new StorageManager(userPrefsStorage, lessonsStorage, lessonImportExport);
        logic = new LogicManager(model, quizModel);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
        assertHistoryCorrect(invalidCommand);
    }

    @Test
    public void execute_startCommand_success() {
        // TODO change to session
        // this hardcoded values matched StartCommand
        // when session is implemented then this will change to session instead
        //        final QuizCard card1 = new QuizCard("Japan", "Tokyo");
        //        final QuizCard card2 = new QuizCard("Hungary", "Budapest");
        //        final QuizCard card3 = new QuizCard("Christmas Island", "The Settlement");
        //        final QuizCard card4 = new QuizCard("中国", "北京");
        //        final List<QuizCard> quizCards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4));
        //        final Quiz quiz = new Quiz(quizCards, Quiz.Mode.LEARN);
        //
        //        QuizModelManager expectedModel = new QuizModelManager();
        //        expectedModel.init(quiz);
        //        QuizCard expectedCard = expectedModel.getNextCard();
        //        CommandResult expected = new CommandResult(String.format(StartCommand.MESSAGE_QUESTION_ANSWER,
        //            expectedCard.getQuestion(), expectedCard.getAnswer()));
        //
        //        assertCommandSuccess(StartCommand.COMMAND_WORD, expected.getFeedbackToUser(), expectedModel);
    }

    @Test
    public void execute_quizCommand_success() throws Exception {
        final String answer = "Budapest";
        // TODO change to session
        // this hardcoded values matched StartCommand
        // when session is implemented then this will change to session instead
        final QuizCard card1 = new QuizCard("Japan", "Tokyo");
        final QuizCard card2 = new QuizCard("Hungary", "Budapest");
        final QuizCard card3 = new QuizCard("Christmas Island", "The Settlement");
        final QuizCard card4 = new QuizCard("中国", "北京");
        final List<QuizCard> quizCards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4));
        final Quiz quiz = new Quiz(quizCards, Quiz.Mode.LEARN);

        QuizModelManager expectedModel = new QuizModelManager();
        expectedModel.init(quiz);
        expectedModel.getNextCard();

        QuizCard expectedCard = expectedModel.getNextCard();
        CommandResult expected = new CommandResult(String.format(AnswerCommand.MESSAGE_QUESTION_ANSWER,
            expectedCard.getQuestion(), expectedCard.getAnswer()));

        quizModel.init(new Quiz(quizCards, Quiz.Mode.LEARN));
        quizModel.getNextCard();

        assertCommandSuccess(answer, expected.getFeedbackToUser(), expectedModel);
    }

    @Test
    public void getHistory() {
        CommandHistory empty = new CommandHistory();
        assertEquals(empty.getHistory(), logic.getHistory());
    }

    /**
     * Executes the command, confirms that no exceptions are thrown and that the result message is correct.
     * Also confirms that {@code expectedModel} is as specified.
     * @see #assertCommandBehavior(Class, String, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage, Model expectedModel) {
        assertCommandBehavior(null, inputCommand, expectedMessage, expectedModel);
    }

    /**
     * Executes the command, confirms that no exceptions are thrown and that the result message is correct.
     * Also confirms that {@code expectedModel} is as specified.
     * @see #assertCommandBehavior(Class, String, String, QuizModel)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage, QuizModel expectedModel) {
        assertCommandBehavior(null, inputCommand, expectedMessage, expectedModel);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandBehavior(Class, String, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandBehavior(Class, String, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandBehavior(Class, String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<?> expectedException, String expectedMessage) {
        Model expectedModel = new ModelManager(new UserPrefs(), new Lessons());
        assertCommandBehavior(expectedException, inputCommand, expectedMessage, expectedModel);
    }

    /**
     * Executes the command, confirms that the result message is correct and that the expected exception is thrown,
     * and also confirms that the following two parts of the LogicManager object's state are as expected:<br>
     *      - the internal model manager data are same as those in the {@code expectedModel} <br>
     *      - {@code expectedModel}'s address book was saved to the storage file.
     */
    private void assertCommandBehavior(Class<?> expectedException, String inputCommand,
                                           String expectedMessage, Model expectedModel) {

        try {
            CommandResult result = logic.execute(inputCommand);
            assertEquals(expectedException, null);
            assertEquals(expectedMessage, result.getFeedbackToUser());
        } catch (CommandException | ParseException e) {
            assertEquals(expectedException, e.getClass());
            assertEquals(expectedMessage, e.getMessage());
        }

        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that the result message is correct and that the expected exception is thrown,
     * and also confirms that the following two parts of the LogicManager object's state are as expected:<br>
     *      - the internal model manager data are same as those in the {@code expectedModel} <br>
     *      - {@code expectedModel}'s address book was saved to the storage file.
     */
    private void assertCommandBehavior(Class<?> expectedException, String inputCommand,
                                       String expectedMessage, QuizModel expectedModel) {

        try {
            CommandResult result = logic.execute(inputCommand);
            assertEquals(expectedException, null);
            assertEquals(expectedMessage, result.getFeedbackToUser());
        } catch (CommandException | ParseException e) {
            assertEquals(expectedException, e.getClass());
            assertEquals(expectedMessage, e.getMessage());
        }

        assertEquals(expectedModel, quizModel);
    }

    /**
     * Asserts that the result display shows all the {@code expectedCommands} upon the execution of
     * {@code HistoryCommand}.
     */
    private void assertHistoryCorrect(String... expectedCommands) {
        try {
            CommandResult result = logic.execute(HistoryCommand.COMMAND_WORD);
            String expectedMessage = String.format(
                    HistoryCommand.MESSAGE_SUCCESS, String.join("\n", expectedCommands));
            assertEquals(expectedMessage, result.getFeedbackToUser());
        } catch (ParseException | CommandException e) {
            throw new AssertionError("Parsing and execution of HistoryCommand.COMMAND_WORD should succeed.", e);
        }
    }

}
