package braintrain.logic.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import braintrain.logic.CommandHistory;
import braintrain.model.Model;
import braintrain.model.ModelManager;
import braintrain.model.session.Session;
import braintrain.quiz.Quiz;

public class StartCommandTest {

    private static final CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_success() throws Exception {
        Model model = new ModelManager();
        // TODO change this to use SessionBuilder instead, look at PersonBuilder for reference
        Session session = new Session("lessonname", 5, Quiz.Mode.LEARN);
        CommandResult commandResult = new StartCommand(session).execute(model, commandHistory);

        assertEquals(String.format(StartCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
    }

    @Test
    public void executeActual_success() {
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
        //        expectedModel.getNextCard();
        //        CommandResult expectedCommandResult = new StartCommand().executeActual(expectedModel, commandHistory);
        //
        //        QuizModel actualModel = new QuizModelManager();
        //        StartCommand startCommand = new StartCommand();
        //
        //        CommandHistory expectedCommandHistory = new CommandHistory(commandHistory);
        //        CommandResult result = startCommand.executeActual(actualModel, commandHistory);
        //        assertEquals(expectedCommandResult, result);
        //        assertEquals(expectedModel, actualModel);
        //        assertEquals(expectedCommandHistory, commandHistory);
    }

}
