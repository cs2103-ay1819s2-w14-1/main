package braintrain.logic.commands;

import static braintrain.logic.parser.CliSyntax.PREFIX_COUNT;
import static braintrain.logic.parser.CliSyntax.PREFIX_MODE;
import static braintrain.logic.parser.CliSyntax.PREFIX_NAME;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import braintrain.logic.CommandHistory;
import braintrain.logic.commands.exceptions.CommandException;
import braintrain.model.Model;
import braintrain.quiz.Quiz;
import braintrain.quiz.QuizCard;
import braintrain.quiz.QuizModel;

/**
 * Starts a quiz session.
 */
public class StartCommand extends Command {
    public static final String COMMAND_WORD = "start";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Starts a new quiz." + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_COUNT + "COUNT "
            + PREFIX_MODE + "MODE...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "02-03-LEARN"
            + PREFIX_COUNT + "15"
            + PREFIX_MODE + "LEARN";

    public static final String MESSAGE_SUCCESS = "Starting new quiz";
    public static final String MESSAGE_QUESTION_ANSWER = "Question: %1$s\nAnswer: %2$s";

    private List<QuizCard> toStart;

    public StartCommand(List<QuizCard> quizCards) {
        requireNonNull(quizCards);
        toStart = quizCards;
    }

    /**
     * Executes the command.
     * TODO change this ugly method if possible.
     */
    public CommandResult executeActual(QuizModel model, CommandHistory history) {
        // hardcoded values until session is ready
        // only have question and answer
        //QuizCard card1 = new QuizCard("Japan", "Tokyo");
        //QuizCard card2 = new QuizCard("Hungary", "Budapest");
        //QuizCard card3 = new QuizCard("Christmas Island", "The Settlement");
        //QuizCard card4 = new QuizCard("中国", "北京");
        List<QuizCard> cards = new ArrayList<>();
        for (int i = 0; i < toStart.size(); i++) {
            cards.add(toStart.get(i));
        }
        Quiz quiz = new Quiz(cards, Quiz.Mode.LEARN);

        model.init(quiz);
        QuizCard card = model.getNextCard();

        return new CommandResult(String.format(MESSAGE_QUESTION_ANSWER, card.getQuestion(), card.getAnswer()));
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
