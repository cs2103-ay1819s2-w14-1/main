package braintrain.logic.commands;

import static braintrain.logic.parser.CliSyntax.PREFIX_COUNT;
import static braintrain.logic.parser.CliSyntax.PREFIX_MODE;
import static braintrain.logic.parser.CliSyntax.PREFIX_NAME;
import static java.util.Objects.requireNonNull;

import java.util.List;

import braintrain.logic.CommandHistory;
import braintrain.logic.commands.exceptions.CommandException;
import braintrain.model.Model;
import braintrain.model.card.exceptions.MissingCoreException;
import braintrain.model.session.Session;
import braintrain.quiz.Quiz;
import braintrain.quiz.QuizCard;
import braintrain.quiz.QuizModel;

/**
 * Starts a quiz session.
 */
public class StartCommand extends Command {
    public static final String COMMAND_WORD = "start";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_COUNT + "COUNT] "
            + PREFIX_MODE + "MODE...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "02-03-LEARN "
            + PREFIX_COUNT + "15 "
            + PREFIX_MODE + "LEARN";

    public static final String MESSAGE_SUCCESS = "Starting new quiz";
    public static final String MESSAGE_QUESTION_ANSWER = "Question: %1$s\nAnswer: %2$s";

    private List<QuizCard> quizCards;
    private Session session;


    public StartCommand(Session session) {
        requireNonNull(session);
        this.session = session;
    }

    /**
     * Executes the command.
     * TODO change this ugly method if possible.
     */
    public CommandResult executeActual(QuizModel model, CommandHistory history) throws CommandException {
        // TODO remove comment when not needed
        // hardcoded values until session is ready
        // only have question and answer
        //QuizCard card1 = new QuizCard("Japan", "Tokyo");
        //QuizCard card2 = new QuizCard("Hungary", "Budapest");
        //QuizCard card3 = new QuizCard("Christmas Island", "The Settlement");
        //QuizCard card4 = new QuizCard("中国", "北京");

        try {
            this.quizCards = session.generateSession();
        } catch (MissingCoreException e) {
            // TODO remove this when Card is refactored, shouldnt have MissingCoreException at this point
            throw new CommandException("There are missing cores in this lesson");
        }

        Quiz quiz = new Quiz(quizCards, session.getMode());

        model.init(quiz);
        QuizCard card = model.getNextCard();

        return new CommandResult(String.format(MESSAGE_QUESTION_ANSWER, card.getQuestion(), card.getAnswer()));
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        // TODO: get lesson and card from model
        //TODO: generate current list of cards.
        //        Lesson currentLesson = null;
        //        List<CardData> currentCardData = null;
        //        SrsCardsManager manager = new SrsCardsManager(currentLesson, currentCardData);
        //        Session session = new Session(name, count, mode, manager.sort());


        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
